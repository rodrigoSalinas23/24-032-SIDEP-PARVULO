package cl.mineduc.sidep.parvuloapi.exceptions;

import cl.mineduc.sidep.parvuloapi.services.ProcesoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {

    @Mock
    private ProcesoService procesoService;

    @InjectMocks
    private ExceptionController exceptionController;

    private HttpServletRequest request;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test-uri");
    }

    @Test
    public void testHandleParvuloException() {
        String errorMsg = "Parvulo error";
        ParvuloException exception = new ParvuloException(errorMsg);

        ResponseEntity<Map<String, Object>> response = exceptionController.handleParvuloException(exception, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(errorMsg, body.get("error"));
        assertEquals("/test-uri", body.get("uri"));
        assertNotNull(body.get("timestamp"));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testHandleMissingServletRequestParameterException() {
        MissingServletRequestParameterException exception =
                new MissingServletRequestParameterException("param", "String");

        ResponseEntity<Map<String, Object>> response =
                exceptionController.handleMissingServletRequestParameterException(exception, request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals(exception.getMessage(), body.get("error"));
        assertEquals("/test-uri", body.get("uri"));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testHandleMethodArgumentNotValidException() throws Exception {
        Object target = new Object();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, "target");
        bindingResult.addError(new FieldError("target", "campo1", "campo1 es requerido"));

        Method m = ExceptionController.class.getDeclaredMethod("handleParvuloException", ParvuloException.class, HttpServletRequest.class);
        MethodParameter methodParameter = new MethodParameter(m, 0);

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<Map<String, Object>> response =
                exceptionController.handlerMethodArgumentNotValidException(exception, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        Object errorObj = body.get("error");
        assertTrue(errorObj instanceof Map);
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) errorObj;
        assertTrue(errors.containsKey("target"));
        assertEquals("campo1 es requerido", errors.get("target"));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testHandlerBindException() {
        Object target = new Object();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, "target");
        bindingResult.addError(new FieldError("target", "campo2", "campo2 es obligatorio"));
        BindException exception = new BindException(bindingResult);

        ResponseEntity<Map<String, Object>> response =
                exceptionController.handlerBindExcption(request, exception);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        Object errorObj = body.get("error");
        assertTrue(errorObj instanceof Map);
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) errorObj;
        assertTrue(errors.containsKey("campo2"));
        assertEquals("campo2 es obligatorio", errors.get("campo2"));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testHandleHttpMessageNotReadableException_InvalidFormat() {
        InvalidFormatException invalidFormatException = InvalidFormatException.from(null, "Error", "wrongValue", String.class);
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Invalid format", invalidFormatException);

        ResponseEntity<Map<String, Object>> response =
                exceptionController.handleHttpMessageNotReadableException(exception, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertTrue(((String) body.get("error")).contains("se recibió"));
        assertEquals("/test-uri", body.get("uri"));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testHandleHttpMessageNotReadableException_JsonParse() {
        JsonParseException jsonParseException = new JsonParseException(null, "Parse error");
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("JSON parse error", jsonParseException);

        ResponseEntity<Map<String, Object>> response =
                exceptionController.handleHttpMessageNotReadableException(exception, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertTrue(((String) body.get("error")).contains("Revise el formato"));
        assertEquals("/test-uri", body.get("uri"));
        verify(procesoService, times(1)).save(any());
    }
}

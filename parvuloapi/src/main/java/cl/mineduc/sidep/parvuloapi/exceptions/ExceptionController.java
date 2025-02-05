package cl.mineduc.sidep.parvuloapi.exceptions;

import cl.mineduc.sidep.parvuloapi.services.ProcesoService;
import cl.mineduc.sidep.parvuloapi.utils.ProcesoUtils;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ProcesoService procesoService;

    @ExceptionHandler(value = ParvuloException.class)
    protected ResponseEntity<Map<String, Object>> handleParvuloException(ParvuloException e, HttpServletRequest request) {
        return getMapResponseEntity(request, e);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    protected ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        return getMapResponseEntity(request, e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ResponseEntity<Map<String, Object>> response = getMapResponseEntity(request, e);
        Map<String, Object> map = response.getBody();

        if (map == null) {
            map = new HashMap<>();
        }

        Map<String, Object> finalMap = map;
        Map<String, String> errors = new HashMap<>();

        finalMap.remove("error");

        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = error.getObjectName();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        finalMap.put("error", errors);

        return ResponseEntity.badRequest().body(finalMap);
    }

    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<Map<String, Object>> handlerBindExcption(HttpServletRequest request, BindException e) {
        ResponseEntity<Map<String, Object>> response = getMapResponseEntity(request, e);
        Map<String, Object> map = response.getBody();

        if (map == null) {
            map = new HashMap<>();
        }

        Map<String, Object> finalMap = map;
        Map<String, String> errors = new HashMap<>();

        finalMap.remove("error");

        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        finalMap.put("error", errors);

        return ResponseEntity.badRequest().body(finalMap);

    }


    private ResponseEntity<Map<String, Object>> getMapResponseEntity(HttpServletRequest request, Exception e) {
        int statusCode = (e instanceof ParvuloException) ? HttpStatus.BAD_REQUEST.value() : HttpStatus.INTERNAL_SERVER_ERROR.value();

        this.procesoService.save(ProcesoUtils.getProcesoEntity(
                statusCode,
                ProcesoUtils.getOperacion(request.getMethod(), request.getRequestURI()),
                e.getMessage()
        ));

        Map<String, Object> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("uri", request.getRequestURI());

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        String detalleError;
        Throwable rootCause = e.getRootCause();

        if (rootCause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) rootCause;
            String campoProblematico = ife.getPath().isEmpty() ? null : ife.getPath().get(0).getFieldName();
            detalleError = String.format("Error de formato en el campo '%s': se recibió '%s' pero se esperaba un valor del tipo '%s'.",
                    campoProblematico, ife.getValue(), ife.getTargetType().getSimpleName());

        } else if (rootCause instanceof JsonMappingException) {
            JsonMappingException jme = (JsonMappingException) rootCause;
            String campoProblematico = jme.getPath().isEmpty() ? null : jme.getPath().get(0).getFieldName();
            detalleError = String.format("Error de mapeo JSON en el campo '%s'. Revise los datos enviados.",
                    campoProblematico);

        } else if (rootCause instanceof com.fasterxml.jackson.core.JsonParseException) {
            detalleError = "Error. Revise el formato de los datos enviados. Detalle: " + rootCause.getMessage();
        } else {
            detalleError = "Error al procesar la solicitud. Detalle: " + e.getMessage();
        }

        procesoService.save(ProcesoUtils.getProcesoEntity(
                HttpStatus.BAD_REQUEST.value(),
                ProcesoUtils.getOperacion(request.getMethod(), request.getRequestURI()),
                detalleError));

        Map<String, Object> response = new HashMap<>();
        response.put("error", detalleError);
        response.put("timestamp", LocalDateTime.now());
        response.put("uri", request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

}

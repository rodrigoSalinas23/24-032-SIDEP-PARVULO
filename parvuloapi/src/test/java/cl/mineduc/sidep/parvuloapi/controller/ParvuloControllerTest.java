package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import cl.mineduc.sidep.parvuloapi.services.ParvuloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ParvuloControllerTest {

    @Mock
    private ParvuloService parvuloService;

    @InjectMocks
    private ParvuloController parvuloController;

    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        this.request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("URI");
    }

    @Test
    public void findAll() {
        List<ParvuloModel> expected = Arrays.asList(new ParvuloModel());
        when(parvuloService.findAll()).thenReturn(expected);

        ResponseEntity<List<ParvuloModel>> response = parvuloController.findAll();
        assertNotNull(response);
        assertEquals(expected, response.getBody());
    }

    @Test
    public void findById() {
        ParvuloModel model = new ParvuloModel();
        model.setId(1L);
        when(parvuloService.findById(1L)).thenReturn(model);

        ResponseEntity<ParvuloModel> response = parvuloController.findById(1L);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void save() {
        ParvuloModel model = new ParvuloModel();
        model.setId(1L);
        when(parvuloService.save(any(ParvuloModel.class))).thenReturn(model);

        ResponseEntity<ParvuloModel> response = parvuloController.save(model);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void update() {
        ParvuloModel model = new ParvuloModel();
        model.setId(1L);
        when(parvuloService.update(eq(1L), any(ParvuloModel.class))).thenReturn(model);

        ResponseEntity<ParvuloModel> response = parvuloController.update(1L, model);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }
}

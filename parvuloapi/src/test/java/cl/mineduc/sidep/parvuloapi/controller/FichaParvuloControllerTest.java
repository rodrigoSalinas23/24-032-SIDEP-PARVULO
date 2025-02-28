package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import cl.mineduc.sidep.parvuloapi.services.FichaParvuloService;
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
public class FichaParvuloControllerTest {

    @Mock
    private FichaParvuloService fichaParvuloService;

    @InjectMocks
    private FichaParvuloController fichaParvuloController;

    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        this.request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("URI");
    }

    @Test
    public void findAll() {
        List<FichaParvuloModel> expectedList = Arrays.asList(new FichaParvuloModel());
        when(fichaParvuloService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<FichaParvuloModel>> response = fichaParvuloController.findAll();
        assertNotNull(response);
        assertEquals(expectedList, response.getBody());
    }

    @Test
    public void findById() {
        FichaParvuloModel model = new FichaParvuloModel();
        model.setId(1L);
        when(fichaParvuloService.findById(1L)).thenReturn(model);

        ResponseEntity<FichaParvuloModel> response = fichaParvuloController.findById(1L);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void save() {
        FichaParvuloModel model = new FichaParvuloModel();
        model.setId(1L);
        when(fichaParvuloService.save(any(FichaParvuloModel.class))).thenReturn(model);

        ResponseEntity<FichaParvuloModel> response = fichaParvuloController.save(model);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void update() {
        FichaParvuloModel model = new FichaParvuloModel();
        model.setId(1L);
        when(fichaParvuloService.update(eq(1L), any(FichaParvuloModel.class))).thenReturn(model);

        ResponseEntity<FichaParvuloModel> response = fichaParvuloController.update(model, 1L);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }
}

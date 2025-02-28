package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
import cl.mineduc.sidep.parvuloapi.services.GrupoFamiliarService;
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
public class GrupoFamiliarControllerTest {

    @Mock
    private GrupoFamiliarService grupoFamiliarService;

    @InjectMocks
    private GrupoFamiliarController grupoFamiliarController;

    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        this.request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("URI");
    }

    @Test
    public void findAll() {
        List<GrupoFamiliarModel> expected = Arrays.asList(new GrupoFamiliarModel());
        when(grupoFamiliarService.findAll()).thenReturn(expected);

        ResponseEntity<List<GrupoFamiliarModel>> response = grupoFamiliarController.findAll();
        assertNotNull(response);
        assertEquals(expected, response.getBody());
    }

    @Test
    public void findById() {
        GrupoFamiliarModel model = new GrupoFamiliarModel();
        model.setId(1L);
        when(grupoFamiliarService.findById(1L)).thenReturn(model);

        ResponseEntity<GrupoFamiliarModel> response = grupoFamiliarController.findById(1L);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void save() {
        GrupoFamiliarModel model = new GrupoFamiliarModel();
        model.setId(1L);
        when(grupoFamiliarService.save(any(GrupoFamiliarModel.class))).thenReturn(model);

        ResponseEntity<GrupoFamiliarModel> response = grupoFamiliarController.save(model);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }

    @Test
    public void update() {
        GrupoFamiliarModel model = new GrupoFamiliarModel();
        model.setId(1L);
        when(grupoFamiliarService.update(eq(1L), any(GrupoFamiliarModel.class))).thenReturn(model);

        ResponseEntity<GrupoFamiliarModel> response = grupoFamiliarController.update(model, 1L);
        assertNotNull(response);
        assertEquals(model, response.getBody());
    }
}

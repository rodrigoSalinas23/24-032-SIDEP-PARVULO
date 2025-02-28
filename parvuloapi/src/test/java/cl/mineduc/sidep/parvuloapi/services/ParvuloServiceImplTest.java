package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import cl.mineduc.sidep.parvuloapi.models.PersonaModel;
import cl.mineduc.sidep.parvuloapi.repositories.ParvuloRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ParvuloServiceImplTest {

    @InjectMocks
    private ParvuloServiceImpl parvuloService;

    @Mock
    private ParvuloRepository parvuloRepository;

    private static ParvuloModel loadParvulo() {
        ParvuloModel model = new ParvuloModel();
        model.setId(1L);
        model.setFechaCreacion(LocalDate.now());
        PersonaModel persona = new PersonaModel();
        persona.setId(1L);
        model.setPersona(persona);
        return model;
    }


    @Test
    public void testFindAll() {
        List<ParvuloModel> listaEsperada = Collections.singletonList(loadParvulo());
        when(parvuloRepository.findAll()).thenReturn(listaEsperada);

        List<ParvuloModel> resultado = parvuloService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(listaEsperada, resultado);
    }

    @Test
    public void testFindById() {
        ParvuloModel model = loadParvulo();
        when(parvuloRepository.findById(1L)).thenReturn(model);

        ParvuloModel resultado = parvuloService.findById(1L);
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
    }

    @Test
    public void save() {
        ParvuloModel model = loadParvulo();

        when(parvuloRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        doNothing().when(parvuloRepository).save(any(ParvuloEntity.class));

        parvuloService.save(model);

        verify(parvuloRepository, times(1)).save(any(ParvuloEntity.class));
    }

    @Test
    public void update() {
        ParvuloModel model = loadParvulo();

        when(parvuloRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        when(parvuloRepository.findById(anyLong())).thenReturn(model);
        doNothing().when(parvuloRepository).update(anyLong(), any(ParvuloEntity.class));

        parvuloService.update(1L, model);

        verify(parvuloRepository, times(1)).update(anyLong(), any(ParvuloEntity.class));
    }

    @Test
    public void testValidateForeignKeys_Valido() throws Exception {
        ParvuloModel model = loadParvulo();
        when(parvuloRepository.hasPersona(model.getPersona().getId())).thenReturn(true);

        Method metodo = ParvuloServiceImpl.class.getDeclaredMethod("validateForeignKeys", ParvuloModel.class);
        metodo.setAccessible(true);

        metodo.invoke(parvuloService, model);

        verify(parvuloRepository).hasPersona(model.getPersona().getId());
    }

    @Test(expected = ParvuloException.class)
    public void save_ForeignKeyInvalid() {
        ParvuloModel model = loadParvulo();
        model.getPersona().setId(99L);

        when(parvuloRepository.hasPersona(99L)).thenReturn(false);

        parvuloService.save(model);
    }

    @Test(expected = ParvuloException.class)
    public void update_ForeignKeyInvalid() {
        ParvuloModel model = loadParvulo();
        model.getPersona().setId(99L);

        when(parvuloRepository.hasPersona(99L)).thenReturn(false);

        parvuloService.update(1L, model);
    }

    @Test
    public void save_PersonaNull() {
        ParvuloModel model = loadParvulo();
        model.setPersona(null);

        parvuloService.save(model);

        verify(parvuloRepository, never()).hasPersona(anyLong());
    }

    @Test
    public void save_PersonaIdNull() {
        ParvuloModel model = loadParvulo();
        model.getPersona().setId(null);

        parvuloService.save(model);

        verify(parvuloRepository, never()).hasPersona(anyLong());
    }

}

package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
import cl.mineduc.sidep.parvuloapi.models.PersonaModel;
import cl.mineduc.sidep.parvuloapi.models.TipoRelacionGrupoFamiliarModel;
import cl.mineduc.sidep.parvuloapi.repositories.GrupoFamiliarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GrupoFamiliarServiceImplTest {

    @InjectMocks
    private GrupoFamiliarServiceImpl grupoFamiliarService;

    @Mock
    private GrupoFamiliarRepository grupoFamiliarRepository;

    @Mock
    private ProcesoService procesoService;

    private static GrupoFamiliarModel loadGrupoFamiliar() {
        GrupoFamiliarModel model = new GrupoFamiliarModel();
        model.setId(1L);
        model.setFechaCreacion(LocalDate.now());
        PersonaModel persona = new PersonaModel();
        persona.setId(1L);
        model.setPersona(persona);
        FichaParvuloModel ficha = new FichaParvuloModel();
        ficha.setId(1L);
        model.setFichaParvulo(ficha);
        TipoRelacionGrupoFamiliarModel tipoRelacion = new TipoRelacionGrupoFamiliarModel();
        tipoRelacion.setId(1L);
        model.setTipoRelacionGrupoFamiliar(tipoRelacion);
        return model;
    }

    @Test
    public void testFindAll() {
        List<GrupoFamiliarModel> listaEsperada = Collections.singletonList(loadGrupoFamiliar());
        when(grupoFamiliarRepository.findAll()).thenReturn(listaEsperada);

        List<GrupoFamiliarModel> resultado = grupoFamiliarService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(listaEsperada, resultado);
    }

    @Test
    public void testFindById() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        when(grupoFamiliarRepository.findById(1L)).thenReturn(model);

        GrupoFamiliarModel resultado = grupoFamiliarService.findById(1L);
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
    }

    @Test
    public void testSave() {
        GrupoFamiliarModel model = loadGrupoFamiliar();

        when(grupoFamiliarRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasFichaParvulo(model.getFichaParvulo().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasTipoRelacionGrupoFamiliar(model.getTipoRelacionGrupoFamiliar().getId())).thenReturn(true);

        doAnswer(invocation -> {
            GrupoFamiliarEntity entity = invocation.getArgument(0);
            entity.setId(100L);
            entity.setFechaCreacion(java.time.LocalDate.now());
            return null;
        }).when(grupoFamiliarRepository).save(any(GrupoFamiliarEntity.class));

        GrupoFamiliarModel modelGuardado = loadGrupoFamiliar();
        modelGuardado.setId(100L);
        when(grupoFamiliarRepository.findById(100L)).thenReturn(modelGuardado);

        grupoFamiliarService.save(model);
        verify(grupoFamiliarRepository, times(1)).save(any(GrupoFamiliarEntity.class));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testUpdate() {
        GrupoFamiliarModel model = loadGrupoFamiliar();

        when(grupoFamiliarRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasFichaParvulo(model.getFichaParvulo().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasTipoRelacionGrupoFamiliar(model.getTipoRelacionGrupoFamiliar().getId())).thenReturn(true);
        when(grupoFamiliarRepository.findById(anyLong())).thenReturn(model);

        doNothing().when(grupoFamiliarRepository).update(anyLong(), any(GrupoFamiliarEntity.class));

        grupoFamiliarService.update(1L, model);
        verify(grupoFamiliarRepository, times(1)).update(anyLong(), any(GrupoFamiliarEntity.class));
        verify(procesoService, times(1)).save(any());
    }

    @Test
    public void testValidateForeignKeys_Valido() throws Exception {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        when(grupoFamiliarRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasFichaParvulo(model.getFichaParvulo().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasTipoRelacionGrupoFamiliar(model.getTipoRelacionGrupoFamiliar().getId())).thenReturn(true);

        Method metodo = GrupoFamiliarServiceImpl.class.getDeclaredMethod("validateForeignKeys", GrupoFamiliarModel.class);
        metodo.setAccessible(true);
        metodo.invoke(grupoFamiliarService, model);

        verify(grupoFamiliarRepository).hasPersona(model.getPersona().getId());
        verify(grupoFamiliarRepository).hasFichaParvulo(model.getFichaParvulo().getId());
        verify(grupoFamiliarRepository).hasTipoRelacionGrupoFamiliar(model.getTipoRelacionGrupoFamiliar().getId());
    }

    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_Persona() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        model.getPersona().setId(99L);
        when(grupoFamiliarRepository.hasPersona(99L)).thenReturn(false);

        grupoFamiliarService.save(model);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_FichaParvulo() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        when(grupoFamiliarRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        model.getFichaParvulo().setId(99L);
        when(grupoFamiliarRepository.hasFichaParvulo(99L)).thenReturn(false);

        grupoFamiliarService.save(model);
    }


    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_TipoRelacion() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        when(grupoFamiliarRepository.hasPersona(model.getPersona().getId())).thenReturn(true);
        when(grupoFamiliarRepository.hasFichaParvulo(model.getFichaParvulo().getId())).thenReturn(true);
        model.getTipoRelacionGrupoFamiliar().setId(99L);
        when(grupoFamiliarRepository.hasTipoRelacionGrupoFamiliar(99L)).thenReturn(false);

        grupoFamiliarService.save(model);
    }

    @Test
    public void testSave_PersonaNull() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        model.setPersona(null);

        try {
            grupoFamiliarService.save(model);
        } catch (ParvuloException e) {
            verify(grupoFamiliarRepository, never()).hasPersona(anyLong());
        }
    }

    @Test
    public void testSave_PersonaIdNull() {
        GrupoFamiliarModel model = loadGrupoFamiliar();
        model.getPersona().setId(null);

        try {
            grupoFamiliarService.save(model);
        } catch (ParvuloException e) {
            verify(grupoFamiliarRepository, never()).hasPersona(anyLong());
        }
    }

}
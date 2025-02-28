package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.enums.NecesidadesEspecialesEnum;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.ComunaModel;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import cl.mineduc.sidep.parvuloapi.models.NecesidadesEspecialesModel;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import cl.mineduc.sidep.parvuloapi.repositories.FichaParvuloRepository;
import cl.mineduc.sidep.parvuloapi.repositories.ProcesoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FichaParvuloServiceImplTest {

    @InjectMocks
    private FichaParvuloServiceImpl fichaParvuloService;

    @Mock
    private FichaParvuloRepository fichaParvuloRepository;

    @Mock
    private ProcesoRepository procesoRepository;

    private static FichaParvuloModel loadFichaParvulo() {
        FichaParvuloModel model = new FichaParvuloModel();
        model.setId(1L);
        model.setFechaCreacion(LocalDate.now());

        ParvuloModel parvulo = new ParvuloModel();
        parvulo.setId(1L);
        model.setParvulo(parvulo);

        ComunaModel comuna = new ComunaModel();
        comuna.setId(1L);
        model.setComuna(comuna);

        model.setNecesidadesEspeciales(NecesidadesEspecialesEnum.CEGUERA);

        return model;
    }

    @Test
    public void testFindAll() {
        List<FichaParvuloModel> listaEsperada = Collections.singletonList(loadFichaParvulo());
        when(fichaParvuloRepository.findAll()).thenReturn(listaEsperada);

        List<FichaParvuloModel> resultado = fichaParvuloService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(listaEsperada, resultado);
    }

    @Test
    public void testFindById() {
        FichaParvuloModel model = loadFichaParvulo();
        when(fichaParvuloRepository.findById(1L)).thenReturn(model);

        FichaParvuloModel resultado = fichaParvuloService.findById(1L);
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
    }

    @Test
    public void testSave() {
        FichaParvuloModel model = loadFichaParvulo();

        when(fichaParvuloRepository.hasParvulo(model.getParvulo().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasComuna(model.getComuna().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasNecesidadesEspeciales(model.getNecesidadesEspeciales().getId())).thenReturn(true);

        doAnswer(invocation -> {
            FichaParvuloEntity entity = invocation.getArgument(0);
            entity.setId(100L);
            entity.setFechaCreacion(LocalDate.now());
            return null;
        }).when(fichaParvuloRepository).save(any(FichaParvuloEntity.class));

        FichaParvuloModel modelGuardado = loadFichaParvulo();
        modelGuardado.setId(100L);
        when(fichaParvuloRepository.findById(100L)).thenReturn(modelGuardado);

        FichaParvuloModel resultado = fichaParvuloService.save(model);
        assertNotNull(resultado);
        assertEquals(Long.valueOf(100L), resultado.getId());
        verify(fichaParvuloRepository, times(1)).save(any(FichaParvuloEntity.class));
        verify(procesoRepository, times(1)).save(any());
    }

    @Test
    public void testUpdate() {
        FichaParvuloModel model = loadFichaParvulo();

        when(fichaParvuloRepository.hasParvulo(model.getParvulo().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasComuna(model.getComuna().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasNecesidadesEspeciales(model.getNecesidadesEspeciales().getId())).thenReturn(true);
        when(fichaParvuloRepository.findById(anyLong())).thenReturn(model);

        doNothing().when(fichaParvuloRepository).update(anyLong(), any(FichaParvuloEntity.class));

        FichaParvuloModel resultado = fichaParvuloService.update(1L, model);
        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
        verify(fichaParvuloRepository, times(1)).update(anyLong(), any(FichaParvuloEntity.class));
        verify(procesoRepository, times(1)).save(any());
    }

    @Test
    public void testValidateForeignKeys_Valido() throws Exception {
        FichaParvuloModel model = loadFichaParvulo();
        when(fichaParvuloRepository.hasParvulo(model.getParvulo().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasComuna(model.getComuna().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasNecesidadesEspeciales(model.getNecesidadesEspeciales().getId())).thenReturn(true);

        Method metodo = FichaParvuloServiceImpl.class.getDeclaredMethod("validateForeignKeys", FichaParvuloModel.class);
        metodo.setAccessible(true);
        metodo.invoke(fichaParvuloService, model);

        verify(fichaParvuloRepository).hasParvulo(model.getParvulo().getId());
        verify(fichaParvuloRepository).hasComuna(model.getComuna().getId());
        verify(fichaParvuloRepository).hasNecesidadesEspeciales(model.getNecesidadesEspeciales().getId());
    }

    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_Parvulo() {
        FichaParvuloModel model = loadFichaParvulo();
        model.getParvulo().setId(99L);
        when(fichaParvuloRepository.hasParvulo(99L)).thenReturn(false);

        fichaParvuloService.save(model);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_Comuna() {
        FichaParvuloModel model = loadFichaParvulo();
        when(fichaParvuloRepository.hasParvulo(model.getParvulo().getId())).thenReturn(true);
        model.getComuna().setId(99L);
        when(fichaParvuloRepository.hasComuna(99L)).thenReturn(false);

        fichaParvuloService.save(model);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_ForeignKeyInvalid_Necesidades() {
        FichaParvuloModel model = loadFichaParvulo();
        when(fichaParvuloRepository.hasParvulo(model.getParvulo().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasComuna(model.getComuna().getId())).thenReturn(true);
        when(fichaParvuloRepository.hasNecesidadesEspeciales(model.getNecesidadesEspeciales().getId())).thenReturn(false);

        fichaParvuloService.save(model);
    }

    @Test
    public void testSave_ParvuloNull() {
        FichaParvuloModel model = loadFichaParvulo();
        model.setParvulo(null);
        try {
            fichaParvuloService.save(model);
        } catch (ParvuloException e) {
            verify(fichaParvuloRepository, never()).hasParvulo(anyLong());
        }
    }

    @Test
    public void testSave_ParvuloIdNull() {
        FichaParvuloModel model = loadFichaParvulo();
        model.getParvulo().setId(null);
        try {
            fichaParvuloService.save(model);
        } catch (ParvuloException e) {
            verify(fichaParvuloRepository, never()).hasParvulo(anyLong());
        }
    }
}

package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.FichaParvuloMapper;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FichaParvuloRepositoryImplTest {

    @InjectMocks
    private FichaParvuloRepositoryImpl fichaParvuloRepositoryImpl;

    @Mock
    private FichaParvuloMapper fichaParvuloMapper;

    @Test
    public void testFindAll_ReturnsList() {
        List<FichaParvuloModel> expectedList = Collections.singletonList(new FichaParvuloModel());
        when(fichaParvuloMapper.findAll()).thenReturn(expectedList);

        List<FichaParvuloModel> result = fichaParvuloRepositoryImpl.findAll();
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindAll_Exception_ReturnsEmptyList() {
        when(fichaParvuloMapper.findAll()).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        List<FichaParvuloModel> result = fichaParvuloRepositoryImpl.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindById_ReturnsModel() {
        FichaParvuloModel model = new FichaParvuloModel();
        when(fichaParvuloMapper.findById(1L)).thenReturn(model);

        FichaParvuloModel result = fichaParvuloRepositoryImpl.findById(1L);
        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    public void testFindById_Exception_ReturnsNull() {
        when(fichaParvuloMapper.findById(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        FichaParvuloModel result = fichaParvuloRepositoryImpl.findById(1L);
        assertNull(result);
    }

    @Test
    public void testSave_Success() {
        FichaParvuloEntity entity = new FichaParvuloEntity();
        doNothing().when(fichaParvuloMapper).insert(entity);

        fichaParvuloRepositoryImpl.save(entity);
        verify(fichaParvuloMapper, times(1)).insert(entity);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_Exception_ThrowsParvuloException() {
        FichaParvuloEntity entity = new FichaParvuloEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error"))).when(fichaParvuloMapper).insert(entity);

        fichaParvuloRepositoryImpl.save(entity);
    }

    @Test
    public void testUpdate_Success() {
        FichaParvuloEntity entity = new FichaParvuloEntity();
        doNothing().when(fichaParvuloMapper).update(1L, entity);

        fichaParvuloRepositoryImpl.update(1L, entity);
        verify(fichaParvuloMapper, times(1)).update(1L, entity);
    }

    @Test(expected = ParvuloException.class)
    public void testUpdate_Exception_ThrowsParvuloException() {
        FichaParvuloEntity entity = new FichaParvuloEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error"))).when(fichaParvuloMapper).update(1L, entity);

        fichaParvuloRepositoryImpl.update(1L, entity);
    }

    @Test
    public void testHasComuna_ReturnsTrue() {
        when(fichaParvuloMapper.hasComuna(1L)).thenReturn(true);
        Boolean result = fichaParvuloRepositoryImpl.hasComuna(1L);
        assertTrue(result);
    }

    @Test
    public void testHasComuna_Exception_ReturnsFalse() {
        when(fichaParvuloMapper.hasComuna(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = fichaParvuloRepositoryImpl.hasComuna(1L);
        assertFalse(result);
    }

    @Test
    public void testHasNecesidadesEspeciales_ReturnsTrue() {
        when(fichaParvuloMapper.hasNecesidadesEspeciales(1L)).thenReturn(true);
        Boolean result = fichaParvuloRepositoryImpl.hasNecesidadesEspeciales(1L);
        assertTrue(result);
    }

    @Test
    public void testHasNecesidadesEspeciales_Exception_ReturnsFalse() {
        when(fichaParvuloMapper.hasNecesidadesEspeciales(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = fichaParvuloRepositoryImpl.hasNecesidadesEspeciales(1L);
        assertFalse(result);
    }

    @Test
    public void testHasParvulo_ReturnsTrue() {
        when(fichaParvuloMapper.hasParvulo(1L)).thenReturn(true);
        Boolean result = fichaParvuloRepositoryImpl.hasParvulo(1L);
        assertTrue(result);
    }

    @Test
    public void testHasParvulo_Exception_ReturnsFalse() {
        when(fichaParvuloMapper.hasParvulo(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = fichaParvuloRepositoryImpl.hasParvulo(1L);
        assertFalse(result);
    }
}

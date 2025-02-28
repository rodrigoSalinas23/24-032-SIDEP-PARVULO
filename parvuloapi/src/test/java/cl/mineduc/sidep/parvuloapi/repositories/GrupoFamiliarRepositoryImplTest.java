package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.GrupoFamiliarMapper;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
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
public class GrupoFamiliarRepositoryImplTest {

    @InjectMocks
    private GrupoFamiliarRepositoryImpl grupoFamiliarRepositoryImpl;

    @Mock
    private GrupoFamiliarMapper grupoFamiliarMapper;

    @Test
    public void testFindAll_ReturnsList() {
        List<GrupoFamiliarModel> expectedList = Collections.singletonList(new GrupoFamiliarModel());
        when(grupoFamiliarMapper.findAll()).thenReturn(expectedList);

        List<GrupoFamiliarModel> result = grupoFamiliarRepositoryImpl.findAll();
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindAll_Exception_ReturnsEmptyList() {
        when(grupoFamiliarMapper.findAll()).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        List<GrupoFamiliarModel> result = grupoFamiliarRepositoryImpl.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindById_ReturnsModel() {
        GrupoFamiliarModel model = new GrupoFamiliarModel();
        when(grupoFamiliarMapper.findById(1L)).thenReturn(model);

        GrupoFamiliarModel result = grupoFamiliarRepositoryImpl.findById(1L);
        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    public void testFindById_Exception_ReturnsNull() {
        when(grupoFamiliarMapper.findById(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        GrupoFamiliarModel result = grupoFamiliarRepositoryImpl.findById(1L);
        assertNull(result);
    }

    @Test
    public void testSave_Success() {
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();
        doNothing().when(grupoFamiliarMapper).insert(entity);

        grupoFamiliarRepositoryImpl.save(entity);
        verify(grupoFamiliarMapper, times(1)).insert(entity);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_Exception_ThrowsParvuloException() {
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error")))
                .when(grupoFamiliarMapper).insert(entity);

        grupoFamiliarRepositoryImpl.save(entity);
    }

    @Test
    public void testUpdate_Success() {
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();
        doNothing().when(grupoFamiliarMapper).update(1L, entity);

        grupoFamiliarRepositoryImpl.update(1L, entity);
        verify(grupoFamiliarMapper, times(1)).update(1L, entity);
    }

    @Test(expected = ParvuloException.class)
    public void testUpdate_Exception_ThrowsParvuloException() {
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error")))
                .when(grupoFamiliarMapper).update(1L, entity);

        grupoFamiliarRepositoryImpl.update(1L, entity);
    }

    @Test
    public void testHasPersona_ReturnsTrue() {
        when(grupoFamiliarMapper.hasPersona(1L)).thenReturn(true);
        Boolean result = grupoFamiliarRepositoryImpl.hasPersona(1L);
        assertTrue(result);
    }

    @Test
    public void testHasPersona_Exception_ReturnsFalse() {
        when(grupoFamiliarMapper.hasPersona(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = grupoFamiliarRepositoryImpl.hasPersona(1L);
        assertFalse(result);
    }

    @Test
    public void testHasFichaParvulo_ReturnsTrue() {
        when(grupoFamiliarMapper.hasFichaParvulo(1L)).thenReturn(true);
        Boolean result = grupoFamiliarRepositoryImpl.hasFichaParvulo(1L);
        assertTrue(result);
    }

    @Test
    public void testHasFichaParvulo_Exception_ReturnsFalse() {
        when(grupoFamiliarMapper.hasFichaParvulo(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = grupoFamiliarRepositoryImpl.hasFichaParvulo(1L);
        assertFalse(result);
    }

    @Test
    public void testHasTipoRelacionGrupoFamiliar_ReturnsTrue() {
        when(grupoFamiliarMapper.hasTipoRelacionGrupoFamiliar(1L)).thenReturn(true);
        Boolean result = grupoFamiliarRepositoryImpl.hasTipoRelacionGrupoFamiliar(1L);
        assertTrue(result);
    }

    @Test
    public void testHasTipoRelacionGrupoFamiliar_Exception_ReturnsFalse() {
        when(grupoFamiliarMapper.hasTipoRelacionGrupoFamiliar(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = grupoFamiliarRepositoryImpl.hasTipoRelacionGrupoFamiliar(1L);
        assertFalse(result);
    }
}

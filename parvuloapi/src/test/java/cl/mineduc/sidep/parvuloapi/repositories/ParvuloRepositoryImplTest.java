package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.ParvuloMapper;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
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
public class ParvuloRepositoryImplTest {

    @InjectMocks
    private ParvuloRepositoryImpl parvuloRepositoryImpl;

    @Mock
    private ParvuloMapper parvuloMapper;

    @Test
    public void testFindAll_ReturnsList() {
        List<ParvuloModel> expectedList = Collections.singletonList(new ParvuloModel());
        when(parvuloMapper.findAll()).thenReturn(expectedList);

        List<ParvuloModel> result = parvuloRepositoryImpl.findAll();
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindAll_Exception_ReturnsEmptyList() {
        when(parvuloMapper.findAll()).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        List<ParvuloModel> result = parvuloRepositoryImpl.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindById_ReturnsModel() {
        ParvuloModel model = new ParvuloModel();
        when(parvuloMapper.findById(1L)).thenReturn(model);

        ParvuloModel result = parvuloRepositoryImpl.findById(1L);
        assertNotNull(result);
        assertEquals(model, result);
    }

    @Test
    public void testFindById_Exception_ReturnsNull() {
        when(parvuloMapper.findById(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        ParvuloModel result = parvuloRepositoryImpl.findById(1L);
        assertNull(result);
    }

    @Test
    public void testSave_Success() {
        ParvuloEntity entity = new ParvuloEntity();
        doNothing().when(parvuloMapper).insert(entity);

        parvuloRepositoryImpl.save(entity);
        verify(parvuloMapper, times(1)).insert(entity);
    }

    @Test(expected = ParvuloException.class)
    public void testSave_Exception_ThrowsParvuloException() {
        ParvuloEntity entity = new ParvuloEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error")))
                .when(parvuloMapper).insert(entity);

        parvuloRepositoryImpl.save(entity);
    }

    @Test
    public void testUpdate_Success() {
        ParvuloEntity entity = new ParvuloEntity();
        doNothing().when(parvuloMapper).update(1L, entity);

        parvuloRepositoryImpl.update(1L, entity);
        verify(parvuloMapper, times(1)).update(1L, entity);
    }

    @Test(expected = ParvuloException.class)
    public void testUpdate_Exception_ThrowsParvuloException() {
        ParvuloEntity entity = new ParvuloEntity();
        doThrow(new MyBatisSystemException(new PersistenceException("Error")))
                .when(parvuloMapper).update(1L, entity);

        parvuloRepositoryImpl.update(1L, entity);
    }

    @Test
    public void testHasPersona_ReturnsTrue() {
        when(parvuloMapper.hasPersona(1L)).thenReturn(true);
        Boolean result = parvuloRepositoryImpl.hasPersona(1L);
        assertTrue(result);
    }

    @Test
    public void testHasPersona_Exception_ReturnsFalse() {
        when(parvuloMapper.hasPersona(1L)).thenThrow(new MyBatisSystemException(new PersistenceException("Error")));
        Boolean result = parvuloRepositoryImpl.hasPersona(1L);
        assertFalse(result);
    }
}

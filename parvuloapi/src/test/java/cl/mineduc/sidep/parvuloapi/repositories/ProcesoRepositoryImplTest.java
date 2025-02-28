package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.ProcesoEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.SidepException;
import cl.mineduc.sidep.parvuloapi.mappers.ProcesoMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcesoRepositoryImplTest {

    @InjectMocks
    private ProcesoRepositoryImpl procesoRepositoryImpl;

    @Mock
    private ProcesoMapper procesoMapper;

    @Test
    public void testSave_Success() {
        ProcesoEntity entity = ProcesoEntity.builder()
                .operacion("POST - /ficha-parvulo")
                .status(200)
                .mensaje("OK")
                .build();

        doNothing().when(procesoMapper).insert(entity);
        procesoRepositoryImpl.save(entity);
        verify(procesoMapper, times(1)).insert(entity);
    }

    @Test(expected = SidepException.class)
    public void testSave_Exception_ThrowsSidepException() {
        ProcesoEntity entity = ProcesoEntity.builder()
                .operacion("POST - /ficha-parvulo")
                .status(500)
                .mensaje("Error")
                .build();

        doThrow(new MyBatisSystemException(new PersistenceException("Error")))
                .when(procesoMapper).insert(entity);

        procesoRepositoryImpl.save(entity);
    }
}

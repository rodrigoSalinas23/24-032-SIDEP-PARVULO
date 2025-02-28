package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.ProcesoEntity;
import cl.mineduc.sidep.parvuloapi.repositories.ProcesoRepository;
import cl.mineduc.sidep.parvuloapi.utils.ProcesoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class ProcesoServiceImplTest {

    @InjectMocks
    private ProcesoServiceImpl procesoServiceImpl;

    @Mock
    private ProcesoRepository procesoRepository;

    @Test
    public void save() {

        ProcesoEntity procesoEntity = ProcesoUtils
                .getProcesoEntity(200, ProcesoUtils.getOperacion("POST", "/ENDPOINT"), "mensaje");
        doNothing().when(procesoRepository).save(any());
        this.procesoServiceImpl.save(procesoEntity);

        verify(procesoRepository, times(1)).save(any());

    }

}

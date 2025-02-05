package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.ProcesoEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.SidepException;
import cl.mineduc.sidep.parvuloapi.mappers.ProcesoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProcesoRepositoryImpl implements ProcesoRepository {

    private final ProcesoMapper procesoMapper;

    @Override
    public void save(ProcesoEntity entity) {
        try {
            this.procesoMapper.insert(entity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new SidepException("Error al insertar proceso: " + entity + e);
        }
    }

}

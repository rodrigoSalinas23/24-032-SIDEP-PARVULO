package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.ParvuloMapper;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ParvuloRepositoryImpl implements ParvuloRepository {

    private final ParvuloMapper parvuloMapper;

    @Override
    public List<ParvuloModel> findAll() {
        try {
            return this.parvuloMapper.findAll();
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public ParvuloModel findById(Long id) {
        try {
            return this.parvuloMapper.findById(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(ParvuloEntity parvuloEntity) {
        try {
            this.parvuloMapper.insert(parvuloEntity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al guardar parvulo", e);
        }
    }

    @Override
    public void update(Long id, ParvuloEntity parvuloEntity) {
        try {
            this.parvuloMapper.update(id, parvuloEntity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al actualizar parvulo", e);
        }
    }

    @Override
    public Boolean hasPersona(Long id) {
        try {
            return this.parvuloMapper.hasPersona(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

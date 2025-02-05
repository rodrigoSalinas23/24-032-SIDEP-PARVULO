package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.FichaParvuloMapper;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
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
public class FichaParvuloRepositoryImpl implements FichaParvuloRepository {

    private final FichaParvuloMapper fichaParvuloMapper;

    @Override
    public List<FichaParvuloModel> findAll() {
        try {
            return fichaParvuloMapper.findAll();
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public FichaParvuloModel findById(Long id) {
        try {
            return fichaParvuloMapper.findById(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void save(FichaParvuloEntity entity) {
        try {
            fichaParvuloMapper.insert(entity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al guardar Ficha Parvulo", e);
        }
    }

    @Override
    public void update(Long id, FichaParvuloEntity entity) {
        try {
            fichaParvuloMapper.update(id, entity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al actualizar Ficha Parvulo", e);
        }
    }

    @Override
    public Boolean hasComuna(Long id) {
        try{
            return fichaParvuloMapper.hasComuna(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean hasNecesidadesEspeciales(Long id) {
        try {
            return fichaParvuloMapper.hasNecesidadesEspeciales(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean hasParvulo(Long id) {
        try {
            return fichaParvuloMapper.hasParvulo(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

}

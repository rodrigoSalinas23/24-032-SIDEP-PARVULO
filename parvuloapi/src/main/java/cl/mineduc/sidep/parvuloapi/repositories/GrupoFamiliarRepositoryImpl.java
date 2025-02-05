package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.mappers.GrupoFamiliarMapper;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
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
public class GrupoFamiliarRepositoryImpl implements GrupoFamiliarRepository {

    private final GrupoFamiliarMapper grupoFamiliarMapper;

    @Override
    public List<GrupoFamiliarModel> findAll() {
        try {
            return this.grupoFamiliarMapper.findAll();
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public GrupoFamiliarModel findById(Long id) {
        try {
            return this.grupoFamiliarMapper.findById(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void save(GrupoFamiliarEntity entity) {
        try {
            this.grupoFamiliarMapper.insert(entity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al guardar grupo familiar");
        }
    }

    @Override
    @Transactional
    public void update(Long id, GrupoFamiliarEntity entity) {
        try {
            this.grupoFamiliarMapper.update(id, entity);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            throw new ParvuloException("Error al actualizar grupo familiar");
        }
    }

    @Override
    public Boolean hasPersona(Long id) {
        try {
            return this.grupoFamiliarMapper.hasPersona(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean hasFichaParvulo(Long id) {
        try {
            return this.grupoFamiliarMapper.hasFichaParvulo(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean hasTipoRelacionGrupoFamiliar(Long id) {
        try {
            return this.grupoFamiliarMapper.hasTipoRelacionGrupoFamiliar(id);
        } catch (MyBatisSystemException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

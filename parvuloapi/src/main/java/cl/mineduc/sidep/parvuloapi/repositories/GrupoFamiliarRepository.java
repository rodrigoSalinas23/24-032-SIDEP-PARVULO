package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;

import java.util.List;

public interface GrupoFamiliarRepository {

    List<GrupoFamiliarModel> findAll();

    GrupoFamiliarModel findById(Long id);

    void save(GrupoFamiliarEntity e);

    void update(Long id, GrupoFamiliarEntity entity);

    Boolean hasPersona(Long id);

    Boolean hasFichaParvulo(Long id);

    Boolean hasTipoRelacionGrupoFamiliar(Long id);

}

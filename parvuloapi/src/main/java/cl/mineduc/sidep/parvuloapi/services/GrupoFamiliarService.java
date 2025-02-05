package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;

import java.util.List;

public interface GrupoFamiliarService {

    List<GrupoFamiliarModel> findAll();

    GrupoFamiliarModel findById(Long id);

    GrupoFamiliarModel save(GrupoFamiliarModel model);

    GrupoFamiliarModel update(Long id, GrupoFamiliarModel model);

    default GrupoFamiliarEntity toEntity(GrupoFamiliarModel model) {
        GrupoFamiliarEntity entity = new GrupoFamiliarEntity();

        entity.setPersona(model.getPersona() != null ? model.getPersona().getId() : null);
        entity.setFichaParvulo(model.getFichaParvulo() != null ? model.getFichaParvulo().getId() : null);
        entity.setTipoRelacionGrupoFamiliar(model.getTipoRelacionGrupoFamiliar() != null ? model.getTipoRelacionGrupoFamiliar().getId() : null);
        entity.setFechaCreacion(model.getFechaCreacion());
        entity.setFechaActualizacion(model.getFechaActualizacion());

        return entity;
    }

}

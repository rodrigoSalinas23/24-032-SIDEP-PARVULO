package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;

import java.util.List;

public interface ParvuloService {

    List<ParvuloModel> findAll();

    ParvuloModel findById(Long id);

    ParvuloModel save(ParvuloModel parvuloModel);

    ParvuloModel update(Long id, ParvuloModel parvuloModel);

    default ParvuloEntity toEntity(ParvuloModel model) {
        ParvuloEntity entity = new ParvuloEntity();

        entity.setPersona(model.getPersona().getId() != null ? model.getPersona().getId() : null);

        return entity;
    }

}

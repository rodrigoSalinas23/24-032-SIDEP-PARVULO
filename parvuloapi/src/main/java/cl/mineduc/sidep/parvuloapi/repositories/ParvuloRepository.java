package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;

import java.util.List;

public interface ParvuloRepository {

    List<ParvuloModel> findAll();

    ParvuloModel findById(Long id);

    void save(ParvuloEntity parvuloEntity);

    void update(Long id, ParvuloEntity parvuloEntity);

    Boolean hasPersona(Long id);

}

package cl.mineduc.sidep.parvuloapi.repositories;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;

import java.util.List;

public interface FichaParvuloRepository {

    List<FichaParvuloModel> findAll();

    FichaParvuloModel findById(Long id);

    void save(FichaParvuloEntity entity);

    void update(Long id, FichaParvuloEntity entity);

    Boolean hasComuna(Long id);

    Boolean hasNecesidadesEspeciales(Long id);

    Boolean hasParvulo(Long id);

}

package cl.mineduc.sidep.parvuloapi.mappers;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FichaParvuloMapper {

    List<FichaParvuloModel> findAll();

    FichaParvuloModel findById(Long id);

    void insert(FichaParvuloEntity entity);

    void update(@Param("id") Long id, @Param("fipa") FichaParvuloEntity entity);

    Boolean hasComuna(@Param("id") Long id);

    Boolean hasNecesidadesEspeciales(@Param("id") Long id);

    Boolean hasParvulo(@Param("id") Long id);

}

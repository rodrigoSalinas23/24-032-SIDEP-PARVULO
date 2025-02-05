package cl.mineduc.sidep.parvuloapi.mappers;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParvuloMapper {

    List<ParvuloModel> findAll();

    ParvuloModel findById(Long id);

    void insert(ParvuloEntity entity);

    void update(@Param("id") Long id, @Param("p") ParvuloEntity entity);

    Boolean hasPersona(Long id);

}

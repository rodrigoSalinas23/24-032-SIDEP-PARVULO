package cl.mineduc.sidep.parvuloapi.mappers;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrupoFamiliarMapper {

    List<GrupoFamiliarModel> findAll();

    GrupoFamiliarModel findById(Long id);

    void insert(GrupoFamiliarEntity e);

    void update(@Param("id") Long id, @Param("grfa") GrupoFamiliarEntity entity);

    Boolean hasPersona(Long id);

    Boolean hasFichaParvulo(Long id);

    Boolean hasTipoRelacionGrupoFamiliar(Long id);

}

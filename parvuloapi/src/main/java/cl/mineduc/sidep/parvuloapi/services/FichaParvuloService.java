package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;

import java.util.List;

public interface FichaParvuloService {

    List<FichaParvuloModel> findAll();

    FichaParvuloModel findById(Long id);

    FichaParvuloModel save(FichaParvuloModel model);

    FichaParvuloModel update(Long id, FichaParvuloModel model);

    default FichaParvuloEntity toEntity(FichaParvuloModel model) {
        FichaParvuloEntity entity = new FichaParvuloEntity();

        entity.setId(model.getId());
        entity.setAlumnoPrioritario(model.getAlumnoPrioritario());
        entity.setDireccion(model.getDireccion());
        entity.setDireccionNumero(model.getDireccionNumero());
        entity.setDireccionReferencia(model.getDireccionReferencia());
        entity.setCodigoPostal(model.getCodigoPostal());
        entity.setTelefono(model.getTelefono());
        entity.setCelular(model.getCelular());
        entity.setEmail(model.getEmail());
        entity.setFechaCreacion(model.getFechaCreacion());
        entity.setFechaActualizacion(model.getFechaActualizacion());

        entity.setComuna(model.getComuna() != null ? model.getComuna().getId() : null);
        entity.setNecesidadesEspeciales(model.getNecesidadesEspeciales().getId());
        entity.setParvulo(model.getParvulo() != null ? model.getParvulo().getId() : null);

        return entity;
    }

}

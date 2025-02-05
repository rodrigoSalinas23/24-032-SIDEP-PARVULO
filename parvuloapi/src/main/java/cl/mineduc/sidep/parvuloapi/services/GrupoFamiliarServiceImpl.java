package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.GrupoFamiliarEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
import cl.mineduc.sidep.parvuloapi.repositories.GrupoFamiliarRepository;
import cl.mineduc.sidep.parvuloapi.utils.ProcesoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrupoFamiliarServiceImpl implements GrupoFamiliarService {

    private final GrupoFamiliarRepository grupoFamiliarRepository;
    private final ProcesoService procesoService;

    @Override
    @Transactional
    public List<GrupoFamiliarModel> findAll() {
        return this.grupoFamiliarRepository.findAll();
    }

    @Override
    @Transactional
    public GrupoFamiliarModel findById(Long id) {
        return this.grupoFamiliarRepository.findById(id);
    }

    @Override
    public GrupoFamiliarModel save(GrupoFamiliarModel model) {
        this.validateForeignKeys(model);

        GrupoFamiliarEntity entity = this.toEntity(model);
        this.grupoFamiliarRepository.save(entity);

        model.setId(entity.getId());
        model.setFechaCreacion(entity.getFechaCreacion());

        this.procesoService.save(ProcesoUtils.getProcesoEntity(
                HttpStatus.OK.value(),
                "POST - /grupo-familiar",
                "OK"
        ));

        return this.grupoFamiliarRepository.findById(entity.getId());
    }

    @Override
    public GrupoFamiliarModel update(Long id, GrupoFamiliarModel model) {
        this.validateForeignKeys(model);

        GrupoFamiliarEntity entity = this.toEntity(model);
        this.grupoFamiliarRepository.update(id, entity);

        this.procesoService.save(ProcesoUtils.getProcesoEntity(
                HttpStatus.OK.value(),
                "PUT - /grupo-familiar/" + id,
                "OK"
        ));

        return this.grupoFamiliarRepository.findById(id);
    }

    private void validateForeignKeys(GrupoFamiliarModel model) {
        if (model.getPersona() == null || model.getPersona().getId() == null) {
            log.error("La persona no puede estar vacía");
            throw new ParvuloException("La persona no puede estar vacía");
        } else {
            Long personaId = model.getPersona().getId();
            Boolean existePersona = this.grupoFamiliarRepository.hasPersona(personaId);
            if (existePersona == null || !existePersona) {
                log.error("Persona con ID {} no encontrada", personaId);
                throw new ParvuloException("Persona con ID " + personaId + " no encontrada");
            }
        }

        if (model.getFichaParvulo() == null || model.getFichaParvulo().getId() == null) {
            log.error("La ficha parvulo no puede estar vacía");
            throw new ParvuloException("La ficha parvulo no puede estar vacía");
        } else {
            Long fichaId = model.getFichaParvulo().getId();
            Boolean existeFicha = this.grupoFamiliarRepository.hasFichaParvulo(fichaId);
            if (existeFicha == null || !existeFicha) {
                log.error("Ficha Parvulo con ID {} no encontrada", fichaId);
                throw new ParvuloException("Ficha Parvulo con ID " + fichaId + " no encontrada");
            }
        }

        if (model.getTipoRelacionGrupoFamiliar() == null || model.getTipoRelacionGrupoFamiliar().getId() == null) {
            log.error("El tipo de relación del grupo familiar no puede estar vacío");
            throw new ParvuloException("El tipo de relación del grupo familiar no puede estar vacío");
        } else {
            Long tipoRelacionId = model.getTipoRelacionGrupoFamiliar().getId();
            Boolean existeTipoRelacion = this.grupoFamiliarRepository.hasTipoRelacionGrupoFamiliar(tipoRelacionId);
            if (existeTipoRelacion == null || !existeTipoRelacion) {
                log.error("Tipo de relación del grupo familiar con ID {} no encontrada", tipoRelacionId);
                throw new ParvuloException("Tipo de relación del grupo familiar con ID " + tipoRelacionId + " no encontrada");
            }
        }
    }

}

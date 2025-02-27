package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.ParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import cl.mineduc.sidep.parvuloapi.repositories.ParvuloRepository;
import cl.mineduc.sidep.parvuloapi.repositories.ProcesoRepository;
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
public class ParvuloServiceImpl implements ParvuloService {

    private final ParvuloRepository parvuloRepository;
    private final ProcesoRepository procesoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ParvuloModel> findAll() {
        List<ParvuloModel> parvulos = this.parvuloRepository.findAll();
        log.debug("Parvulos encontrados: {}", parvulos);
        return parvulos;
    }

    @Override
    @Transactional(readOnly = true)
    public ParvuloModel findById(Long id) {
        return this.parvuloRepository.findById(id);
    }

    @Override
    @Transactional
    public ParvuloModel save(ParvuloModel parvuloModel) {
        this.validateForeignKeys(parvuloModel);

        ParvuloEntity entity = this.toEntity(parvuloModel);
        this.parvuloRepository.save(entity);

        parvuloModel.setId(entity.getId());
        parvuloModel.setFechaCreacion(entity.getFechaCreacion());

        return this.parvuloRepository.findById(entity.getId());
    }

    @Override
    @Transactional
    public ParvuloModel update(Long id, ParvuloModel parvuloModel) {
        this.validateForeignKeys(parvuloModel);

        ParvuloEntity entity = this.toEntity(parvuloModel);
        this.parvuloRepository.update(id, entity);

        return this.parvuloRepository.findById(id);
    }

    private void validateForeignKeys(ParvuloModel model) {
        if (model.getPersona() != null && model.getPersona().getId() != null) {
            Long personaId = model.getPersona().getId();
            Boolean existePersona = this.parvuloRepository.hasPersona(personaId);
            if (existePersona == null || !existePersona) {
                log.error("Persona con ID {} no encontrada", personaId);
                throw new ParvuloException("Persona con ID " + personaId + " no encontrada");
            }
        }
    }
}

package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.FichaParvuloEntity;
import cl.mineduc.sidep.parvuloapi.exceptions.ParvuloException;
import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import cl.mineduc.sidep.parvuloapi.repositories.FichaParvuloRepository;
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
public class FichaParvuloServiceImpl implements FichaParvuloService {

    private final FichaParvuloRepository fichaParvuloRepository;
    private final ProcesoRepository procesoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FichaParvuloModel> findAll() {
        return fichaParvuloRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public FichaParvuloModel findById(Long id) {
        return fichaParvuloRepository.findById(id);
    }

    @Override
    public FichaParvuloModel save(FichaParvuloModel model) {
        this.validateForeignKeys(model);

        FichaParvuloEntity entity = this.toEntity(model);
        fichaParvuloRepository.save(entity);

        model.setId(entity.getId());
        model.setFechaCreacion(entity.getFechaCreacion());

        this.procesoRepository.save(ProcesoUtils.getProcesoEntity(
                HttpStatus.OK.value(),
                "POST - /ficha-parvulo",
                "OK"
        ));

        return this.fichaParvuloRepository.findById(entity.getId());
    }

    @Override
    public FichaParvuloModel update(Long id, FichaParvuloModel model) {
        this.validateForeignKeys(model);

        FichaParvuloEntity entity = this.toEntity(model);
        fichaParvuloRepository.update(id, entity);

        model.setId(id);
        model.setFechaActualizacion(entity.getFechaActualizacion());

        this.procesoRepository.save(ProcesoUtils.getProcesoEntity(
                HttpStatus.OK.value(),
                "PUT - /ficha-parvulo/" + id,
                "OK"
        ));

        return this.fichaParvuloRepository.findById(id);
    }

    private void validateForeignKeys(FichaParvuloModel model) {
        if (model.getParvulo() == null || model.getParvulo().getId() == null) {
            log.error("El parvulo no puede estar vacío");
            throw new ParvuloException("El parvulo no puede estar vacío");
        } else {
            Long parvuloId = model.getParvulo().getId();
            Boolean existeParvulo = this.fichaParvuloRepository.hasParvulo(parvuloId);
            if (existeParvulo == null || !existeParvulo) {
                log.error("Parvulo con ID {} no encontrado", parvuloId);
                throw new ParvuloException("Parvulo con ID " + parvuloId + " no encontrado");
            }
        }

        if (model.getComuna() == null || model.getComuna().getId() == null) {
            log.error("La comuna no puede estar vacía");
            throw new ParvuloException("La comuna no puede estar vacía");
        } else {
            Long comunaId = model.getComuna().getId();
            Boolean existeComuna = this.fichaParvuloRepository.hasComuna(comunaId);
            if (existeComuna == null || !existeComuna) {
                log.error("Comuna con ID {} no encontrada", comunaId);
                throw new ParvuloException("Comuna con ID " + comunaId + " no encontrada");
            }
        }

        if (model.getNecesidadesEspeciales() == null) {
            log.error("Las necesidades especiales no pueden estar vacías");
            throw new ParvuloException("Las necesidades especiales no pueden estar vacías");
        } else {
            Long necesidadesId = model.getNecesidadesEspeciales().getId();
            Boolean existeNecesidades = this.fichaParvuloRepository.hasNecesidadesEspeciales(necesidadesId);
            if (existeNecesidades == null || !existeNecesidades) {
                log.error("Necesidades Especiales con ID {} no encontradas", necesidadesId);
                throw new ParvuloException("Necesidades Especiales con ID " + necesidadesId + " no encontradas");
            }
        }
    }

}

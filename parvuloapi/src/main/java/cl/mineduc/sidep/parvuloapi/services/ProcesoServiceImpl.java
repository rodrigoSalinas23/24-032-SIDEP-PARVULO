package cl.mineduc.sidep.parvuloapi.services;

import cl.mineduc.sidep.parvuloapi.entities.ProcesoEntity;
import cl.mineduc.sidep.parvuloapi.mappers.ProcesoMapper;
import cl.mineduc.sidep.parvuloapi.repositories.ProcesoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository procesoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(ProcesoEntity e) {
        this.procesoRepository.save(e);
    }
}

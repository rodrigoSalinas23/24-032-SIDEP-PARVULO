package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.GrupoFamiliarModel;
import cl.mineduc.sidep.parvuloapi.services.GrupoFamiliarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "Grupo Familiar Api", path = "/grupo-familiar")
@RequiredArgsConstructor
@Slf4j
public class GrupoFamiliarController {

    private final GrupoFamiliarService grupoFamiliarService;

    @GetMapping("")
    public ResponseEntity<List<GrupoFamiliarModel>> findAll() {
        return ResponseEntity.ok(this.grupoFamiliarService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoFamiliarModel> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.grupoFamiliarService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<GrupoFamiliarModel> save(@RequestBody GrupoFamiliarModel grupoFamiliarModel) {
        return ResponseEntity.ok(this.grupoFamiliarService.save(grupoFamiliarModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoFamiliarModel> update(@RequestBody GrupoFamiliarModel grupoFamiliarModel, @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.grupoFamiliarService.update(id, grupoFamiliarModel));
    }

}

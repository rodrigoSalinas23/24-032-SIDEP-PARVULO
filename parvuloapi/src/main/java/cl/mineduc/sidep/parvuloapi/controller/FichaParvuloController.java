package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.FichaParvuloModel;
import cl.mineduc.sidep.parvuloapi.services.FichaParvuloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(name = "Ficha Parvulo Api", path = "/ficha-parvulo")
@RequiredArgsConstructor
@Slf4j
public class FichaParvuloController {

    private final FichaParvuloService fichaParvuloService;

    @GetMapping("")
    public ResponseEntity<List<FichaParvuloModel>> findAll() {
        return ResponseEntity.ok(this.fichaParvuloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaParvuloModel> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.fichaParvuloService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<FichaParvuloModel> save(@RequestBody @Valid FichaParvuloModel fichaParvuloModel) {
        return ResponseEntity.ok(this.fichaParvuloService.save(fichaParvuloModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaParvuloModel> update(@RequestBody @Valid FichaParvuloModel fichaParvuloModel, @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.fichaParvuloService.update(id, fichaParvuloModel));
    }

}

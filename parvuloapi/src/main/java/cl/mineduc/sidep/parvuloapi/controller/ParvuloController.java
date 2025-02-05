package cl.mineduc.sidep.parvuloapi.controller;

import cl.mineduc.sidep.parvuloapi.models.ParvuloModel;
import cl.mineduc.sidep.parvuloapi.services.ParvuloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "Parvulo Api", path = "/parvulo")
@RequiredArgsConstructor
@Slf4j
public class ParvuloController {

    private final ParvuloService parvuloService;

    @GetMapping("")
    public ResponseEntity<List<ParvuloModel>> findAll() {
        return ResponseEntity.ok(this.parvuloService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParvuloModel> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.parvuloService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ParvuloModel> save(@RequestBody ParvuloModel model) {
        return ResponseEntity.ok(this.parvuloService.save(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParvuloModel> update(@PathVariable("id") Long id,
                                               @RequestBody ParvuloModel model) {
        return ResponseEntity.ok(this.parvuloService.update(id, model));
    }

}

package cl.mineduc.sidep.parvuloapi.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GrupoFamiliarEntity {

    private Long id;
    private Long persona;
    private Long fichaParvulo;
    private Long tipoRelacionGrupoFamiliar;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

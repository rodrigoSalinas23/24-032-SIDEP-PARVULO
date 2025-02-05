package cl.mineduc.sidep.parvuloapi.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParvuloEntity {

    private Long id;
    private Long persona;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

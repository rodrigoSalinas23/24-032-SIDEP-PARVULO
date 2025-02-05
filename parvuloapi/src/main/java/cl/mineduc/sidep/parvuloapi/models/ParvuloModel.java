package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParvuloModel {

    private Long id;
    private PersonaModel persona;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

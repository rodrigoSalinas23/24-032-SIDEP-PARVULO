package cl.mineduc.sidep.parvuloapi.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FichaParvuloEntity {

    private Long id;
    private Long comuna;
    private Long necesidadesEspeciales;
    private Long parvulo;
    private Integer alumnoPrioritario;
    private String direccion;
    private String direccionNumero;
    private String direccionReferencia;
    private String codigoPostal;
    private String telefono;
    private String celular;
    private String email;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

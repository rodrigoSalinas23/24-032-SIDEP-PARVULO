package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PersonaModel {

    private Long id;
    private EtniaModel etnia;
    private NacionalidadModel nacionalidad;
    private EstadoCivilModel estadoCivil;
    private SexoModel sexo;
    private Integer rut;
    private String dv;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalDate fechaDefuncion;

}

package cl.mineduc.sidep.parvuloapi.models;

import cl.mineduc.sidep.parvuloapi.enums.NecesidadesEspecialesEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class FichaParvuloModel {

    private Long id;

    @NotNull(message = "Comuna no puede estar vacio")
    @Valid
    private ComunaModel comuna;

    @NotNull(message = "Necesidades Especiales no puede estar vacio")
    private NecesidadesEspecialesEnum necesidadesEspeciales;

    @NotNull(message = "Parvulo no puede estar vacio")
    @Valid
    private ParvuloModel parvulo;
    private Integer alumnoPrioritario;

    @Size(max = 100, message = "Direccion no puede tener mas de 100 caracteres")
    private String direccion;

    @Size(max = 20, message = "Direccion Numero no puede tener mas de 20 caracteres")
    private String direccionNumero;

    @Size(max = 100, message = "Direccion Referencia no puede tener mas de 100 caracteres")
    private String direccionReferencia;
    private String codigoPostal;

    @Size(max = 20, message = "Telefono no puede tener mas de 20 caracteres")
    private String telefono;

    @Size(max = 20, message = "Celular no puede tener mas de 20 caracteres")
    private String celular;

    @Email(message = "Email no es valido")
    @Size(max = 100, message = "Email no puede tener mas de 100 caracteres")
    private String email;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

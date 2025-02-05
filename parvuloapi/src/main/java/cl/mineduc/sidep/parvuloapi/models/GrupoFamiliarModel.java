package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class GrupoFamiliarModel {

    private Long id;

    @NotNull
    @Valid
    private PersonaModel persona;

    @NotNull
    @Valid
    private FichaParvuloModel fichaParvulo;

    @NotNull
    @Valid
    private TipoRelacionGrupoFamiliarModel tipoRelacionGrupoFamiliar;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

}

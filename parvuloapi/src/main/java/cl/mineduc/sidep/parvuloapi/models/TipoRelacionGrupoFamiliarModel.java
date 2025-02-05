package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

@Data
public class TipoRelacionGrupoFamiliarModel {

    private Long id;
    private String nombre;
    private Boolean cosanguinidad;
    private String glosaRegistroCivil;

}

package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

@Data
public class ComunaModel {

    private Long id;
    private ProvinciaModel provincia;
    private String nombre;

}

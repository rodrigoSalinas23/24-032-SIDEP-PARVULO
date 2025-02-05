package cl.mineduc.sidep.parvuloapi.models;

import lombok.Data;

@Data
public class ProvinciaModel {

    private Long id;
    private RegionModel region;
    private String nombre;

}

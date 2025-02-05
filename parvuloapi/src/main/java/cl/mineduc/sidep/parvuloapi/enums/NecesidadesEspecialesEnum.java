package cl.mineduc.sidep.parvuloapi.enums;

public enum NecesidadesEspecialesEnum {

    DISCAPACIDADINTELECTUAL(1L),
    CEGUERA(2L),
    SORDERA(3L),
    DISCAPACIDADMOTORA(4L),
    OTRO(5L),
    NOREGISTRA(6L);

    private Long id;

    NecesidadesEspecialesEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

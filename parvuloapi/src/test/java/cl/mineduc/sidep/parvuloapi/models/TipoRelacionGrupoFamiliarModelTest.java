package cl.mineduc.sidep.parvuloapi.models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TipoRelacionGrupoFamiliarModelTest {

    @Test
    public void equalsTest() {
        TipoRelacionGrupoFamiliarModel model1 = new TipoRelacionGrupoFamiliarModel();
        TipoRelacionGrupoFamiliarModel model2 = new TipoRelacionGrupoFamiliarModel();
        assertEquals(model1, model2);
    }

    @Test
    public void notEqualsTest() {
        TipoRelacionGrupoFamiliarModel model1 = new TipoRelacionGrupoFamiliarModel();
        TipoRelacionGrupoFamiliarModel model2 = new TipoRelacionGrupoFamiliarModel();
        model1.setId(1L);
        model2.setId(2L);
        assertNotEquals(model1, model2);
    }

    @Test
    public void testHashCode() {
        TipoRelacionGrupoFamiliarModel model1 = new TipoRelacionGrupoFamiliarModel();
        TipoRelacionGrupoFamiliarModel model2 = new TipoRelacionGrupoFamiliarModel();
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    public void testToString() {
        TipoRelacionGrupoFamiliarModel model = new TipoRelacionGrupoFamiliarModel();
        String toStringValue = model.toString();
        assertNotNull(toStringValue);
        assertFalse(toStringValue.isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        TipoRelacionGrupoFamiliarModel model = new TipoRelacionGrupoFamiliarModel();
        model.setId(1L);
        model.setNombre("TEST");
        model.setCosanguinidad(true);
        model.setGlosaRegistroCivil("Glosa ejemplo");

        assertEquals(Long.valueOf(1L), model.getId());
        assertEquals("TEST", model.getNombre());
        assertTrue(model.getCosanguinidad());
        assertEquals("Glosa ejemplo", model.getGlosaRegistroCivil());
    }
}

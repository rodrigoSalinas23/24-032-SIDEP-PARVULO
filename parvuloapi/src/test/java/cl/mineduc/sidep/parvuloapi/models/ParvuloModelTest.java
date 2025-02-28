package cl.mineduc.sidep.parvuloapi.models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class ParvuloModelTest {

    @Test
    public void equalsTest() {
        ParvuloModel model1 = new ParvuloModel();
        ParvuloModel model2 = new ParvuloModel();
        assertEquals(model1, model2);
    }

    @Test
    public void notEqualsTest() {
        ParvuloModel model1 = new ParvuloModel();
        ParvuloModel model2 = new ParvuloModel();
        model1.setId(1L);
        model2.setId(2L);
        assertNotEquals(model1, model2);
    }

    @Test
    public void testHashCode() {
        ParvuloModel model1 = new ParvuloModel();
        ParvuloModel model2 = new ParvuloModel();
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    public void testToString() {
        ParvuloModel model = new ParvuloModel();
        String toStringValue = model.toString();
        assertNotNull(toStringValue);
        assertFalse(toStringValue.isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        ParvuloModel model = new ParvuloModel();
        model.setId(1L);
        LocalDate today = LocalDate.now();
        model.setFechaCreacion(today);
        model.setFechaActualizacion(today);
        PersonaModel persona = new PersonaModel();
        persona.setId(10L);
        model.setPersona(persona);

        assertEquals(Long.valueOf(1L), model.getId());
        assertEquals(today, model.getFechaCreacion());
        assertEquals(today, model.getFechaActualizacion());
        assertEquals(persona, model.getPersona());
    }
}

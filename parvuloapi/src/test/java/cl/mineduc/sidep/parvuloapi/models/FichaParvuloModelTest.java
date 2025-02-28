package cl.mineduc.sidep.parvuloapi.models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FichaParvuloModelTest {

    @Test
    public void equalsTest() {
        FichaParvuloModel model1 = new FichaParvuloModel();
        FichaParvuloModel model2 = new FichaParvuloModel();
        assertEquals(model1, model2);
    }

    @Test
    public void notEqualsTest() {
        FichaParvuloModel model1 = new FichaParvuloModel();
        FichaParvuloModel model2 = new FichaParvuloModel();
        model1.setId(1L);
        model2.setId(2L);
        assertNotEquals(model1, model2);
    }

    @Test
    public void testHashCode() {
        FichaParvuloModel model1 = new FichaParvuloModel();
        FichaParvuloModel model2 = new FichaParvuloModel();
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    public void testToString() {
        FichaParvuloModel model = new FichaParvuloModel();
        String toStringValue = model.toString();
        assertNotNull(toStringValue);
        assertFalse(toStringValue.isEmpty());
    }

}

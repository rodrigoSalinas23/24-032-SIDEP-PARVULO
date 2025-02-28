package cl.mineduc.sidep.parvuloapi.entities;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FichaParvuloEntityTest {

    @Test
    public void equalsTest() {
        FichaParvuloEntity e1 = new FichaParvuloEntity();
        FichaParvuloEntity e2 = new FichaParvuloEntity();
        assertEquals(e1, e2);
    }

    @Test
    public void notEqualsTest() {
        FichaParvuloEntity e1 = new FichaParvuloEntity();
        FichaParvuloEntity e2 = new FichaParvuloEntity();
        e1.setId(1L);
        e2.setId(2L);
        assertNotEquals(e1, e2);
    }

    @Test
    public void testHashCode() {
        FichaParvuloEntity e1 = new FichaParvuloEntity();
        FichaParvuloEntity e2 = new FichaParvuloEntity();
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    public void testToString() {
        FichaParvuloEntity e1 = new FichaParvuloEntity();
        String toStringValue = e1.toString();
        assertNotEquals("", toStringValue);
    }
}

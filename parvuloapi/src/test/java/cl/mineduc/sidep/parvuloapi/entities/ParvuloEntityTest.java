package cl.mineduc.sidep.parvuloapi.entities;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParvuloEntityTest {

    @Test
    public void equalsTest() {
        ParvuloEntity p1 = new ParvuloEntity();
        ParvuloEntity p2 = new ParvuloEntity();
        assertEquals(p1, p2);
    }

    @Test
    public void notEqualsTest() {
        ParvuloEntity p1 = new ParvuloEntity();
        ParvuloEntity p2 = new ParvuloEntity();
        p1.setId(1L);
        p2.setId(2L);
        assertNotEquals(p1, p2);
    }

    @Test
    public void testHashCode() {
        ParvuloEntity p1 = new ParvuloEntity();
        ParvuloEntity p2 = new ParvuloEntity();
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testToString() {
        ParvuloEntity p1 = new ParvuloEntity();
        String toStringValue = p1.toString();
        assertNotEquals("", toStringValue);
    }
}

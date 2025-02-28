package cl.mineduc.sidep.parvuloapi.entities;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GrupoFamiliarEntityTest {

    @Test
    public void equalsTest() {
        GrupoFamiliarEntity e1 = new GrupoFamiliarEntity();
        GrupoFamiliarEntity e2 = new GrupoFamiliarEntity();
        assertEquals(e1, e2);
    }

    @Test
    public void notEqualsTest() {
        GrupoFamiliarEntity e1 = new GrupoFamiliarEntity();
        GrupoFamiliarEntity e2 = new GrupoFamiliarEntity();
        e1.setId(1L);
        e2.setId(2L);
        assertNotEquals(e1, e2);
    }

    @Test
    public void testHashCode() {
        GrupoFamiliarEntity e1 = new GrupoFamiliarEntity();
        GrupoFamiliarEntity e2 = new GrupoFamiliarEntity();
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    public void testToString() {
        GrupoFamiliarEntity e1 = new GrupoFamiliarEntity();
        String toStringValue = e1.toString();
        assertNotEquals("", toStringValue);
    }
}

package cl.mineduc.sidep.parvuloapi.entities;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProcesoEntityTest {

    @Test
    public void equalsTest() {
        ProcesoEntity p1 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        ProcesoEntity p2 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        assertEquals(p1, p2);
    }

    @Test
    public void notEqualsTest() {
        ProcesoEntity p1 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        ProcesoEntity p2 = ProcesoEntity.builder()
                .operacion("PUT - /endpoint")
                .status(404)
                .mensaje("Not Found")
                .build();
        assertNotEquals(p1, p2);
    }

    @Test
    public void testHashCode() {
        ProcesoEntity p1 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        ProcesoEntity p2 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testToString() {
        ProcesoEntity p1 = ProcesoEntity.builder()
                .operacion("POST - /endpoint")
                .status(200)
                .mensaje("OK")
                .build();
        String toStringValue = p1.toString();
        assertNotEquals("", toStringValue);
    }
}

package ueb14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import de.nmarion.htw.ueb14.Uhrzeit;

public class UhrzeitTest {

    @Test
    public void testConstructor() {
        final Uhrzeit uhrzeit = new Uhrzeit(11, 30);
        assertEquals(11, uhrzeit.getStunde());
        assertEquals(30, uhrzeit.getMinute());
        assertEquals("11:30 Uhr", uhrzeit.toString());
    }

    @Test
    public void testConstructorException() {
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(25, 0);
        });
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(24, 0);
        });
        Assertions.assertDoesNotThrow(() -> {
            new Uhrzeit(23, 0);
        });
        Assertions.assertDoesNotThrow(() -> {
            new Uhrzeit(0, 0);
        });
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(-1, 0);
        });
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(1, 60);
        });
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(1, 61);
        });
        Assertions.assertThrows(Throwable.class, () -> {
            new Uhrzeit(1, -1);
        });
    }

    @Test
    public void testEquals(){
        final Uhrzeit start = new Uhrzeit(11, 30);
        final Uhrzeit end = new Uhrzeit(12, 30);
        final Uhrzeit test = new Uhrzeit(11, 30);
        assertEquals(start, test);
        assertNotEquals(start, end);
        assertNotEquals(end, test);
        assertEquals(start, start);
        assertNotEquals(start, "11:30 Uhr");
    }

}

package ueb14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import de.nmarion.htw.ueb14.Mitarbeiter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MitarbeiterTest {

  @Test
  public void testConstructor() {
    final Mitarbeiter mitarbeiter = new Mitarbeiter("Niklas", "Marion", "nmarion@htw-saarland.de");
    assertEquals("Niklas", mitarbeiter.getVorname());
    assertEquals("Marion", mitarbeiter.getNachname());
    assertEquals("Niklas Marion (nmarion@htw-saarland.de)", mitarbeiter.toString());
  }

  @Test
  public void testConstructorException() {
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Mitarbeiter("Niklas", "Marion", "");
        });
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Mitarbeiter("Niklas", "Marion", null);
        });
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Mitarbeiter("Niklas", "Marion", "abc@de");
        });
  }

  @Test
  public void testEquals() {
    final Mitarbeiter niklas = new Mitarbeiter("Niklas", "Marion", "nmarion@htw-saarland.de");
    final Mitarbeiter teacher = new Mitarbeiter("Peter", "Birkner", "pbirkner@htw-saarland.de");
    final Mitarbeiter niklasClon = new Mitarbeiter("Niklas", "Marion", "nmarion@htw-saarland.de");
    assertNotEquals(niklas, teacher);
    assertNotEquals(teacher, niklasClon);
    assertEquals(niklas, niklasClon);
    assertNotEquals(niklas, "Niklas Marion");
    assertEquals(niklas, niklas);
  }
}

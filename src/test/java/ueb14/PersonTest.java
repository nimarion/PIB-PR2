package ueb14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import de.nmarion.htw.ueb14.Person;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PersonTest {

  @Test
  public void testConstructor() {
    final Person person = new Person("Niklas", "Marion");
    assertEquals("Niklas", person.getVorname());
    assertEquals("Marion", person.getNachname());
    assertEquals("Niklas Marion", person.toString());
  }

  @Test
  public void testConstructorException() {
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Person("", "Marion");
        });
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Person(null, "Marion");
        });
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Person("Niklas", "");
        });
    Assertions.assertThrows(
        Throwable.class,
        () -> {
          new Person("Niklas", null);
        });
  }

  @Test
  public void testEquals() {
    final Person niklas = new Person("Niklas", "Marion");
    final Person teacher = new Person("Peter", "Birkner");
    final Person niklasClon = new Person("Niklas", "Marion");
    assertNotEquals(niklas, teacher);
    assertNotEquals(teacher, niklasClon);
    assertEquals(niklas, niklasClon);
    assertNotEquals(niklas, "Niklas Marion");
    assertEquals(niklas, niklas);
  }
}

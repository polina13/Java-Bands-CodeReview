import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenueNamesAretheSame() {
    Venue firstVenue = new Venue("venue1");
    Venue secondVenue = new Venue("venue1");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Venue myVenue = new Venue("venue1");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(savedVenue.equals(myVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue myVenue = new Venue("venue1");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }


}

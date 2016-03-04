import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.Arrays;

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

  @Test
  public void addBand_addsBandToVeue() {
    Band myBand = new Band("Band1");
    myBand.save();

    Venue myVenue = new Venue("venue1");
    myVenue.save();

    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_ArrayList() {
    Band myBand = new Band("Band1");
    myBand.save();

    Venue myVenue = new Venue("venue1");
    myVenue.save();

    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(savedBands.size(), 1);
  }

  @Test
  public void getBands_retrievesALlBandsFromDatabase_BandsList() {
    Band myBand = new Band("Band1");
    myBand.save();
    Venue firstVenue = new Venue("venue1");
    firstVenue.save();
    Venue secondVenue = new Venue("venue2");
    secondVenue.save();
    Venue[] venues = new Venue[] { firstVenue, secondVenue };
    assertFalse(myBand.getVenues().containsAll(Arrays.asList(venues)));
  }
}

import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import java.util.Arrays;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandsAreTheSame() {
    Band firstBand = new Band("Band1");
    Band secondBand = new Band("Band1");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesBandsIntoDatabase_true() {
    Band myBand = new Band("Band1");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band myBand = new Band("Band1");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void addVenue_addsVenueToBand() {
    Band myBand = new Band("Band1");
    myBand.save();

    Venue myVenue = new Venue("venue 1");
    myVenue.save();

    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_ArrayList() {
    Band myBand = new Band("Band1");
    myBand.save();

    Venue myVenue = new Venue("venue1");
    myVenue.save();

    myBand.addVenue(myVenue);
    List savedVenues = myBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }


  // @Test
  // public void getVenues_retrievesALlVenuesFromDatabase_VenuesList() {
  //   Band myBand = new Band("Band1");
  //   myBand.save();
  //   Venue firstVenue = new Venue("venue1", myBand.getId());
  //   firstVenue.save();
  //   Venue secondVenue = new Venue("venue2", myBand.getId());
  //   secondVenue.save();
  //   Venue[] venues = new Venue[] { firstVenue, secondVenue };
  //   assertTrue(myBand.getVenues().containsAll(Arrays.asList(venues)));
  // }
}

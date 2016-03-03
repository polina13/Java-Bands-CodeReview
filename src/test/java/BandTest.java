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

//   @Test
//   public void equals_returnsTrueIfNamesAretheSame() {
//     Band firstBand = new Band("Household chores");
//     Band secondBand = new Band("Household chores");
//     assertTrue(firstBand.equals(secondBand));
//   }
//
//   @Test
//   public void save_savesIntoDatabase_true() {
//     Band myBand = new Band("Household chores");
//     myBand.save();
//     assertTrue(Band.all().get(0).equals(myBand));
//   }
//
//   @Test
//   public void find_findBandInDatabase_true() {
//     Band myBand = new Band("Household chores");
//     myBand.save();
//     Band savedBand = Band.find(myBand.getId());
//     assertTrue(myBand.equals(savedBand));
//   }
//
//   @Test
//   public void addTask_addsTaskToBand() {
//     Band myBand = new Band("Household chores");
//     myBand.save();
//
//     Venue myVenue = new Venue("Mow the lawn", false);
//     myVenue.save();
//
//     myBand.addVenue(myVenue);
//     Venue savedVenue = myBand.getVenues().get(0);
//     assertTrue(myVenue.equals(savedVenue));
// }
//
//   @Test
//   public void getVenues_returnsAllVenues_ArrayList() {
//     Band myBand = new Band("Household chores");
//     myBand.save();
//
//     Venue myVenue = new Venue("Mow the lawn", false);
//     myVenue.save();
//
//     myBand.addVenue(myVenue);
//     List savedVenues = myBand.getVenues();
//     assertEquals(savedVenues.size(), 1);
// }
//
//   @Test
//   public void delete_deletesAllVenuesAndListsAssoicationes() {
//     Band myBand = new Band("Household chores");
//     myBand.save();
//
//     Venue myVenue = new Venue("Mow the lawn", false);
//     myVenue.save();
//
//     myBand.addVenue(myVenue);
//     myBand.delete();
//     assertEquals(myVenue.getCategories().size(), 0);
// }


  // @Test
  // public void getVenues_retrievesALlVenuesFromDatabase_VenuesList() {
  //   Band myBand = new Band("Household chores");
  //   myBand.save();
  //   Venue firstTask = new Task("Mow the lawn", myBand.getId());
  //   firstTask.save();
  //   Task secondTask = new Task("Do the dishes", myBand.getId());
  //   secondTask.save();
  //   Task[] tasks = new Task[] { firstTask, secondTask };
  //   assertTrue(myBand.getTasks().containsAll(Arrays.asList(tasks)));
  // }
}

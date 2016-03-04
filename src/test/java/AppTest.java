import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome To The Band Site");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    Band myBand = new Band("Band1");
    myBand.save();
    goTo("http://localhost:4567/bands");
    assertThat(pageSource()).contains("Band1");
  }

  @Test
  public void venueIsCreatedTest() {
    goTo("http://localhost:4567/");
    Venue myVenue = new Venue ("venue1");
    myVenue.save();
    goTo("http://localhost:4567/venues");
    assertThat(pageSource()).contains("venue1");
  }

  @Test
  public void displaysAllVenuesOnceAdded() {
    Band newBand = new Band("Band1");
    newBand.save();
    Venue firstVenue = new Venue("venue1");
    firstVenue.save();
    String bandPath = String.format("http://localhost:4567/band/%d", newBand.getId());
    goTo(bandPath);
    assertThat(pageSource()).contains("venue1");
  }

  @Test
  public void update_updateBandInDatabase() {
    Band newBand = new Band("Band1");
    newBand.save();
    newBand.update("Band2");
    String bandPath = String.format("http://localhost:4567/band/%d", newBand.getId());
    goTo(bandPath);
    assertThat(pageSource()).contains("Band2");
  }
}

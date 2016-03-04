import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String name;

  public Venue(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static List<Venue> all() {
      String sql = "SELECT * FROM venues";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Venue.class);
      }
    }

  public void save() {
    try(Connection con= DB.sql2o.open()) {
      String sql = "INSERT INTO venues(name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
  public boolean equals (Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) && this.getId() == newVenue.getId();
    }
  }

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO bands_played (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
      .addParameter("band_id", band.getId())
      .addParameter("venue_id", this.getId())
      .executeUpdate();
    }
  }

  public ArrayList<Band> getBands() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT band_id FROM bands_played WHERE venue_id = :venue_id";
    List<Integer> bandsIds = con.createQuery(sql)
     .addParameter("venue_id", this.getId())
     .executeAndFetch(Integer.class);

    ArrayList<Band> bands = new ArrayList<Band>();

    for (Integer bandId : bandIds) {
       String venueQuery = "Select * From bands WHERE id = :bandId";
       Band band = con.createQuery(venueQuery)
        .addParameter("bandId", bandId)
        .executeAndFetchFirst(Band.class);
        bands.add(band);
    }
    return bands;
    }
  }
}

import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Band {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Band(String name) {
    this.name = name;
  }

  public static List<Band> all() {
    String sql = "SELECT * FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName());
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO bands(name) VALUES (:name)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands where id=:id";
      Band band = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void addVenue(Venue venue) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO bands_played (band_id, venue_id) VALUES (:band_id, :venue_id)";
    con.createQuery(sql)
      .addParameter("band_id", this.getId())
      .addParameter("venue_id", venue.getId())
      .executeUpdate();
    }
  }


  public ArrayList<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT venue_id FROM bands_played WHERE band_id=:band_id";
      List<Integer>venueIds = con.createQuery(sql)
        .addParameter("band_id",this.getId())
        .executeAndFetch(Integer.class);

        ArrayList<Venue> venues = new ArrayList<Venue>();

        for(Integer venueId : venueIds) {
          String venueQuery = "SELECT * FROM venues WHERE id = :venueId";
          Venue venue = con.createQuery(venueQuery)
          .addParameter("venueId", venueId)
          .executeAndFetchFirst(Venue.class);
          venues.add(venue);
        }
      return venues;
      }
    }


//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       String deleteQuery = "DELETE FROM tasks WHERE id = :id;";
//         con.createQuery(deleteQuery)
//           .addParameter("id", id)
//           .executeUpdate();
//
//       String joinDeleteQuery = "DELETE FROM categories_tasks WHERE task_id = :taskId";
//         con.createQuery(joinDeleteQuery)
//           .addParameter("taskId", this.getId())
//           .executeUpdate();
//   }
// }


  // public void update(String description) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE tasks SET description = :description WHERE id = :id";
  //     con.createQuery(sql)
  //     .addParameter("description", description)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
}

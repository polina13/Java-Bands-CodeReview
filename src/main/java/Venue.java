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
}

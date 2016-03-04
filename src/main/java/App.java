import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;



public class App {
  public static void main(String [] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Band band = new Band(name);
      band.save();
      response.redirect("/bands");
      return null;
    });

    get("/band/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("allVenues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/deleteband/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      band.delete();
      response.redirect("/bands");
      return null;
    });

    post("/band/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      Band band = Band.find(id);
      Venue venue = Venue.find(venueId);
      band.addVenue(venue);
      response.redirect("/band/" + id);
      return null;
    });

    get("/edit/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("template", "templates/edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/:id/update-band-name", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      String updatedName = request.queryParams("updatedName");
      band.update(updatedName);
      response.redirect("/band/" + id);
      return null;
    });

    get("/venues", (request, response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Venue newVenue = new Venue(name);
      newVenue.save();
      response.redirect("/venues");
      return null;
    });

    get("/venue/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("venue", venue);
      model.put("allBands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venue/:id/add_bands", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      Band band = Band.find(bandId);
      Venue venue = Venue.find(id);
      venue.addBand(band);
      response.redirect("/venue/" + id);
      return null;
    });

  }
}

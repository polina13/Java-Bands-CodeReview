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
      List<Band> bands = Band.all();
      model.put("bands", bands);
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

    get("/bands/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("allVenues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues", (request, response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      List<Venue> venues = Venue.all();
      model.put("venues", venues);
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

    get("/venues/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("venue", venue);
      model.put("bands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/add_venues", (request, response) -> {
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      Band band = Band.find(bandId);
      Venue venue = Venue.find(venueId);
      band.addVenue(venue);
      response.redirect("/bands/" + bandId);
      return null;
    });

    post("/venues/:id/add_bands", (request, response) -> {
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      Band band = Band.find(bandId);
      Venue venue = Venue.find(venueId);
      venue.addBand(band);
      response.redirect("/venues/" + venueId);
      return null;
    });


    post("/bands/deleteband", (request, response) -> {
      Band band = Band.find(Integer.parseInt(request.queryParams("delete-band")));
      band.delete();
      response.redirect("/bands");
      return null;
    });
  }
}

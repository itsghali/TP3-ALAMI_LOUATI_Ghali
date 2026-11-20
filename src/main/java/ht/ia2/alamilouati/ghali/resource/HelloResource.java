package ht.ia2.alamilouati.ghali.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!";
    }
    @GET
    @Path("personnes/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@PathParam("nom") String nom, @QueryParam("erreur") boolean erreur) {
        if (erreur) {
            return Response.serverError().entity("Internal Server Error").build();
        }
        return Response.ok("Hello, " + nom).build();
    }
}

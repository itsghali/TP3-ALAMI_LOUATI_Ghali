package ht.ia2.alamilouati.ghali.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/guide")
public class GuideTouristiqueResource {

    /**
     * Retourne le lieu reçu dans un tableau JSON à un élément.
     *
     * @param lieu le nom de la ville ou du pays
     * @return le tableau contenant le nom reçu
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String lieu) {
        return new String[]{lieu};
    }
}

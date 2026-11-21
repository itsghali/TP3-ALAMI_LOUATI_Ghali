package ht.ia2.alamilouati.ghali.resource;

import dev.langchain4j.exception.LangChain4jException;
import dev.langchain4j.service.Result;
import ht.ia2.alamilouati.ghali.llm.InfosTouristiques;
import ht.ia2.alamilouati.ghali.llm.LlmClientForGuideTouristique;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.function.BiFunction;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiqueResource {

    @Inject
    private LlmClientForGuideTouristique client;

    /**
     * Donne les principaux endroits à visiter et le prix moyen d'un repas.
     *
     * @param lieu la ville ou le pays à visiter
     * @param nombre le nombre d'endroits à retourner, deux par défaut
     * @return les informations touristiques au format JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response endroitsDeVisite(@PathParam("ville_ou_pays") String lieu,
                                     @DefaultValue("2") @QueryParam("nb") String nombre) {
        return repondre(lieu, nombre, client::conseiller);
    }

    @GET
    @Path("lieu/{ville_ou_pays}/structure")
    public Response endroitsStructures(@PathParam("ville_ou_pays") String lieu,
                                       @DefaultValue("2") @QueryParam("nb") String nombre,
                                       @QueryParam("usage") boolean inclureUsage) {
        return repondre(lieu, nombre, (destination, nb) -> {
            Result<InfosTouristiques> resultat = client.conseillerStructure(destination, nb);
            if (inclureUsage) {
                return new InfosTouristiquesAvecUsage(resultat.content(), TokenUsageDto.of(resultat.tokenUsage()));
            }
            return resultat.content();
        });
    }

    private Response repondre(String lieu, String nombre, BiFunction<String, Integer, Object> infos) {
        int nb;
        try {
            nb = Integer.parseInt(nombre);
        } catch (NumberFormatException e) {
            return erreur(400, "Le paramètre nb doit être un entier positif.");
        }
        if (nb < 1) {
            return erreur(400, "Le paramètre nb doit être un entier positif.");
        }

        if (lieu.isBlank()) {
            return erreur(400, "Indiquez une ville ou un pays.");
        }
        if (!client.estConfigure()) {
            return erreur(503, "La variable GEMINI_KEY doit être configurée sur le serveur.");
        }

        try {
            return Response.ok(infos.apply(lieu.strip(), nb))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Cache-Control", "no-store")
                    .build();
        } catch (LangChain4jException e) {
            return erreur(502, "Gemini n'a pas pu générer les informations touristiques.");
        }
    }

    private Response erreur(int statut, String message) {
        return Response.status(statut)
                .header("Access-Control-Allow-Origin", "*")
                .header("Cache-Control", "no-store")
                .entity(Json.createObjectBuilder().add("erreur", message).build())
                .build();
    }
}

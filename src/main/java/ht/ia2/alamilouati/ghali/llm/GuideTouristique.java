package ht.ia2.alamilouati.ghali.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

    String ROLE = """
            Tu es un guide touristique. Donne exactement le nombre d'endroits demandé pour le lieu indiqué.
            Estime aussi le prix moyen d'un repas dans la devise locale.
            Réponds uniquement en JSON, sans Markdown, avec les trois champs suivants :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["premier endroit", "deuxième endroit"],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            La liste ci-dessus est un exemple pour deux endroits.
            """;

    String DEMANDE = "Ville ou pays : {{lieu}}. Nombre d'endroits à visiter : {{nb}}.";

    @SystemMessage(ROLE)
    @UserMessage(DEMANDE)
    String conseiller(@V("lieu") String lieu, @V("nb") int nb);
}

package ht.ia2.alamilouati.ghali.llm;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristiqueStructure {

    @SystemMessage(GuideTouristique.ROLE)
    @UserMessage(GuideTouristique.DEMANDE)
    Result<InfosTouristiques> conseiller(@V("lieu") String lieu, @V("nb") int nb);
}

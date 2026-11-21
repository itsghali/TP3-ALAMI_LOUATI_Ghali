package ht.ia2.alamilouati.ghali.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class LlmClientForGuideTouristique {

    private GuideTouristique guide;
    private GuideTouristiqueStructure guideStructure;

    @PostConstruct
    void initialiser() {
        String cle = System.getenv("GEMINI_KEY");
        if (cle == null || cle.isBlank()) {
            return;
        }

        String modele = System.getenv().getOrDefault("GEMINI_MODEL", "gemini-3.5-flash-lite");
        ChatModel chatModel = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName(modele)
                .timeout(Duration.ofSeconds(30))
                .maxRetries(1)
                .responseFormat(ResponseFormat.JSON)
                .build();

        guide = AiServices.create(GuideTouristique.class, chatModel);
        guideStructure = AiServices.create(GuideTouristiqueStructure.class, chatModel);
    }

    public boolean estConfigure() {
        return guide != null;
    }

    public String conseiller(String lieu, int nb) {
        return guide.conseiller(lieu, nb);
    }

    public Result<InfosTouristiques> conseillerStructure(String lieu, int nb) {
        return guideStructure.conseiller(lieu, nb);
    }
}

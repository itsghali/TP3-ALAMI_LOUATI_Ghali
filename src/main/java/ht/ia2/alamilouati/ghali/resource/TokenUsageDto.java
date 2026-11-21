package ht.ia2.alamilouati.ghali.resource;

import dev.langchain4j.model.output.TokenUsage;

public record TokenUsageDto(Integer tokens_entree, Integer tokens_sortie, Integer tokens_total) {

    public static TokenUsageDto of(TokenUsage usage) {
        return new TokenUsageDto(usage.inputTokenCount(), usage.outputTokenCount(), usage.totalTokenCount());
    }
}

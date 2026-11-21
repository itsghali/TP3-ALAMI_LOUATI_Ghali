package ht.ia2.alamilouati.ghali.resource;

import ht.ia2.alamilouati.ghali.llm.InfosTouristiques;

public record InfosTouristiquesAvecUsage(InfosTouristiques infos, TokenUsageDto usage) {
}

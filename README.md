# TP3 — Guide touristique REST

Service Jakarta REST qui utilise LangChain4j et Gemini pour proposer des lieux à visiter et estimer le prix moyen d’un repas dans la devise locale.

## Lancer le projet

1. Utiliser JDK 25, Maven et Payara Server 7.
2. Définir la variable d’environnement `GEMINI_KEY` avant de démarrer Payara. La variable facultative `GEMINI_MODEL` permet de choisir le modèle.
3. Exécuter `mvn clean package`, puis déployer `target/TP3-ALAMI_LOUATI_Ghali-1.0-SNAPSHOT.war` dans Payara.

## Tester

Pour tester le formulaire, ouvrir [test/guide.html](test/guide.html) depuis le dossier local pendant que Payara tourne.

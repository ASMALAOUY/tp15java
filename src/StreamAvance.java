import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Comparator;

public class StreamAvance {
    public static void main(String[] args) {

        List<Personne> personnes = Arrays.asList(
            new Personne("Jean", 25, "Paris"),
            new Personne("Marie", 30, "Lyon"),
            new Personne("Pierre", 20, "Paris"),
            new Personne("Sophie", 35, "Lyon"),
            new Personne("Paul", 40, "Marseille")
        );

        // Grouper par ville
        Map<String, List<Personne>> parVille =
                personnes.stream()
                         .collect(Collectors.groupingBy(Personne::getVille));

        System.out.println("Personnes par ville : " + parVille);

        // Âge moyen
        double ageMoyen =
                personnes.stream()
                         .mapToInt(Personne::getAge)
                         .average()
                         .orElse(0);

        System.out.println("Âge moyen : " + ageMoyen);

        // Personne la plus âgée
        Personne plusAgee =
                personnes.stream()
                         .max(Comparator.comparingInt(Personne::getAge))
                         .orElse(null);

        System.out.println("Personne la plus âgée : " + plusAgee);

        // Noms des Parisiens en majuscules
        List<String> parisiens =
                personnes.stream()
                         .filter(p -> p.getVille().equals("Paris"))
                         .map(Personne::getNom)
                         .map(String::toUpperCase)
                         .collect(Collectors.toList());

        System.out.println("Parisiens : " + parisiens);
    }
}

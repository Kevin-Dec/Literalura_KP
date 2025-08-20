import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.*;
import java.net.URI;
import java.util.*;

public class GutendexClient {
    private static final String BASE_URL = "https://gutendex.com/books/?search=";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Libro> buscarLibro(String titulo) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + titulo.replace(" ", "%20")))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode json = mapper.readTree(response.body());

            List<Libro> resultados = new ArrayList<>();

            for (JsonNode item : json.get("results")) {
                String tituloLibro = item.get("title").asText();
                String idioma = item.get("languages").get(0).asText();

                List<Autor> autores = new ArrayList<>();
                for (JsonNode autorNode : item.get("authors")) {
                    String nombre = autorNode.get("name").asText();
                    Integer nacimiento = autorNode.get("birth_year").isNull() ? null : autorNode.get("birth_year").asInt();
                    Integer muerte = autorNode.get("death_year").isNull() ? null : autorNode.get("death_year").asInt();
                    autores.add(new Autor(nombre, nacimiento, muerte));
                }

                resultados.add(new Libro(tituloLibro, idioma, autores));
            }

            return resultados;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

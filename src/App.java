import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception{

        // Creating a Http connection
        // String url (actually 404 error) = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://imdb-api.com/en/API/Top250Movies/k_4wzv07ki";

        URI address = URI.create(url);
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Extracting defined data
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> movieList = jsonParser.parse(body);
        //System.out.println(movieList.size());
        //System.out.println(body);

        // Show data collected
        var stickerGenerate = new StickerFactory();
        //for (Map<String, String> movie: movieList) {
        for(int i=0; i<10; i++){

            Map<String, String> movie = movieList.get(i);

            String imageUrl = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(imageUrl).openStream();

            String fileName = title + ".png";

            stickerGenerate.generate(inputStream, fileName);

            System.out.println(movie.get("title"));
            //System.out.println(movie.get("image"));
            //System.out.println(movie.get("imDbRating"));
        }
    }
}

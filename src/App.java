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

        String url = "https://api.nasa.gov/planetary/apod?api_key=2bYT4dOglaGUMXam2rT2Wq45fn0NjNTzbMH57PtQ&start_date=2022-04-01&end_date=2022-04-02";

        URI address = URI.create(url);
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Extracting defined data
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> pictureList = jsonParser.parse(body);

        // Show data collected
        var stickerGenerate = new StickerFactory();
        //for (Map<String, String> picture: pictureList) {
        for(int i=0; i<2; i++){

            Map<String, String> picture = pictureList.get(i);

            String imageUrl = picture.get("url");
            String title = picture.get("title");

            InputStream inputStream = new URL(imageUrl).openStream();

            String fileName = title + ".png";

            stickerGenerate.generate(inputStream, fileName);

            System.out.println(picture.get("title"));
        }
    }
}

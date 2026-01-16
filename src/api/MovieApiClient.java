/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;
  
<<<<<<< HEAD
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class MovieApiClient {

    private static final String TMDB_API_KEY = "bead89006ecf8e4af81d954f8dc8ecbd";

    private static final String TMDB_SEARCH_URL =
            "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s";

   
    private static final String TMDB_IMAGE_BASE_URL =
            "https://image.tmdb.org/t/p/w500";

    
    public String fetchMoviePosterUrl(String title) {
        try {
            String query = URLEncoder.encode(title.trim(), StandardCharsets.UTF_8.toString());
            String urlStr = String.format(TMDB_SEARCH_URL, TMDB_API_KEY, query);

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(8000);

            int code = conn.getResponseCode();
            InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();

            if (is == null) return null;

            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line);
            }

            String json = sb.toString();

            // Regex a first result poster_path mezőre
            Pattern p = Pattern.compile("\"poster_path\"\\s*:\\s*\"(.*?)\"");
            Matcher m = p.matcher(json);

            if (m.find()) {
                String posterPath = m.group(1);
                if (posterPath != null && !posterPath.equals("null")) {
                    return TMDB_IMAGE_BASE_URL + posterPath;
                }
            }

        } catch (IOException e) {
           
        }

        return null;
    }

    public boolean downloadImage(String imageUrl, String destPath) {
        if (imageUrl == null || destPath == null) return false;
        try (InputStream in = new URL(imageUrl).openStream()) {
            Path out = Paths.get(destPath);
            if (out.getParent() != null) Files.createDirectories(out.getParent());
            Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // ============ 3. Kombinált funkció ============
    public String fetchAndSaveMoviePoster(String title, String destFolder, String fileName) {
        String posterUrl = fetchMoviePosterUrl(title);
        if (posterUrl == null) return null;
        String destPath = Paths.get(destFolder, fileName).toString();
        boolean ok = downloadImage(posterUrl, destPath);
        return ok ? destPath : null;
    }

}
>>>>>>> 23c7c33b0c2c533135f2f80a14ab31c31481f56e

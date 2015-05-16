import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * PentaCrowl's specific actions defined
 * @author Daniel
 *
 */
public class Crawler {
	
	/**
     * HTTP GET method
     * @param url
     * @throws Exception
     */
    public void executeGet(String url) throws Exception {

        URL requestURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        
        // DEBUG: print request data
        System.out.println("Sending GET request to \"" + url + "\"");
        System.out.println("Response Code: " + responseCode);

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = inputStream.readLine()) != null) {
            response.append(inputLine);
        }
        inputStream.close();
        String result = response.toString();
        
        // DEBUG: print result
        System.out.println(result);

        // TODO: write in database
    }

}

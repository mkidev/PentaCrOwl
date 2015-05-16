
import java.io.IOException;

/**
 *
 * @author Arash, Daniel
 * PentaCrowl: A simple web crawler focused on streaming sites.
 *
 */
public class Main {

	// request URLs
	
	// basic URL for Twitch API: https://api.twitch.tv/kraken/
    private static final String TWITCH_TOP_GAMES_URL = "https://api.twitch.tv/kraken/games/top";
    // ...
    
    private static Crawler crawler;

    
    /**
     * Main method with dynamic crawling
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        crawler = new Crawler();
        
    	try {
            crawler.executeGet(TWITCH_TOP_GAMES_URL);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
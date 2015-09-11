package main.com.project.restServer;

import main.com.project.crawler.util.HibernateUtil;
import main.com.project.database.GameService;
import main.com.project.database.GameServiceImpl;
import main.com.project.model.Game;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 07.09.15.
 */
@RestController
public class GameController {
    GameService service = new GameServiceImpl(HibernateUtil.getSessionFactory());

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> getAll(ModelMap model) {
        return service.getAllGames();
    }

    @RequestMapping(value = "/games/search/{prefix}", method = RequestMethod.GET)
    public List<Game> getByPrefix(@PathVariable("prefix") String prefix, ModelMap model) {
        return service.getGamesByPrefix(prefix);
    }

    @RequestMapping(value = "/games/top", method = RequestMethod.GET)
    public List<Game> getTop(ModelMap model) {
        return service.getTopGames(10);
    }

    @RequestMapping(value = "/games/{gameName}", method = RequestMethod.GET)
    public Game get(@PathVariable("gameName") String gameName, ModelMap model) {
        return service.getGame(gameName);
    }
}

package main.com.project.restServer;

import main.com.project.crawler.util.HibernateUtil;
import main.com.project.database.StreamService;
import main.com.project.database.StreamServiceImpl;
import main.com.project.model.Stream;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
@RestController
public class StreamController {
    StreamService service = new StreamServiceImpl(HibernateUtil.getSessionFactory());


    @RequestMapping(value = "/streams", method = RequestMethod.GET)
    public List<Stream> getAll(ModelMap model) {
        return service.getAllStreams();
    }

    @RequestMapping(value = "/streams/search/{gameName}", method = RequestMethod.GET)
    public List<Stream> getByPrefix(@PathVariable("gameName") String gameName, ModelMap model) {
        return service.getStreamsByGame(gameName);
    }

    @RequestMapping(value = "/streams/newest", method = RequestMethod.GET)
    public List<Stream> getNewest(ModelMap model) {
        return service.getNewestStreams(10);
    }

    @RequestMapping(value = "/streams/top", method = RequestMethod.GET)
    public List<Stream> getTop(ModelMap model) {
        return service.getTopStreams(10);
    }

    @RequestMapping(value = "/streams/{channelName}", method = RequestMethod.GET)
    public Stream get(@PathVariable("channelName") String channelName, ModelMap model) {
        return service.getStreamByChannel(channelName);
    }

}

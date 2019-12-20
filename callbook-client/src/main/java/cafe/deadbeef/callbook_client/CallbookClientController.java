package cafe.deadbeef.callbook_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class CallbookClientController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${resource_server_url}")
    private String resourceServerUrl;

    @GetMapping("/lookup/{callsign}")
    public String getCallbookResource(@PathVariable String callsign, Model model) {
    	String resourceUrl = resourceServerUrl + "/callbook/" + callsign;
    	logger.info("Requesting resource: " + resourceUrl);
    	CallbookEntry callbookEntry = restTemplate.getForEntity(resourceUrl, CallbookEntry.class).getBody();
        model.addAttribute("callbookEntry", callbookEntry);
        return "lookup";
    }

}
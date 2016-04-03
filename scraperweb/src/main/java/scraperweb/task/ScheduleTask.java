package scraperweb.task;

import static com.ui4j.api.browser.BrowserFactory.getWebKit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ui4j.api.browser.Page;

import scraperweb.domain.Scrape;
import scraperweb.repository.ScraperRepository;
@Component
public class ScheduleTask {
	
	@Autowired
	ScraperRepository repo;
	
	
    @Scheduled(fixedRate = 10000)
    public void scrapeTask() {
    	List<Scrape> scrapes = repo.findByScrapeContent("");
    	
    	for (Scrape scrape : scrapes) {
    		System.setProperty("ui4j.headless", "true");
        	
    		StringBuffer buf = new StringBuffer();
        	try (Page page = getWebKit().navigate(scrape.getTargetUrl())) {
                page.getDocument()
                    .queryAll(scrape.getTargetCssPath())
                    .forEach(e -> {
                        System.out.println(e.getText().get());
                        buf.append(e.getText().get() + "\n");
                    });
            }
        	scrape.setScrapeContent(buf.toString());
        	repo.save(scrape);
    	}
    }
}
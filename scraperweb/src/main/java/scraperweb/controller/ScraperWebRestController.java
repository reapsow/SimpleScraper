package scraperweb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import scraperweb.domain.Scrape;
import scraperweb.dto.RequestResult;
import scraperweb.repository.ScraperRepository;

@RestController
public class ScraperWebRestController {
	
	@Autowired
	ScraperRepository repo;
	
	@RequestMapping(value="/requestSubmit", method=RequestMethod.GET)
	@ResponseBody
	public RequestResult requestSubmit(
			@RequestParam String targetUrl, 
			@RequestParam String targetCssPath) {
		repo.save(new Scrape(targetUrl, targetCssPath, ""));
		
		RequestResult result = new RequestResult();
		
		result.setResult("Ok");
		result.setMsg("Succeed!");
		
		return result;
	}
	
	@RequestMapping(value="/resultsList", method=RequestMethod.GET)
	@ResponseBody
	public List<Scrape> resultsList(@RequestParam Integer startId, @RequestParam Integer size) {
		
		System.out.println("resultlist");
		
		Page<Scrape> scrapes = repo.findAll(new PageRequest(startId, size));
		
		return scrapes.getContent();
	}
}

package scraperweb.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import scraperweb.domain.Scrape;

public interface ScraperRepository extends PagingAndSortingRepository<Scrape, Long> {
	List<Scrape> findByScrapeContent(String scrapeContent);
}

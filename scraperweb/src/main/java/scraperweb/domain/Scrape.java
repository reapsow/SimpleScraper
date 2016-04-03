package scraperweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scrape {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String targetUrl;
	private String targetCssPath;
	@Column(columnDefinition = "TEXT")
	private String scrapeContent;
	
	protected Scrape() {}
	
	public Scrape(String targetUrl, String targetCssPath, String scrapeContent) {
		this.targetCssPath = targetCssPath;
		this.targetUrl = targetUrl;
		this.scrapeContent = scrapeContent;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getTargetCssPath() {
		return targetCssPath;
	}
	public void setTargetCssPath(String targetCssPath) {
		this.targetCssPath = targetCssPath;
	}
	public String getScrapeContent() {
		return scrapeContent;
	}
	public void setScrapeContent(String scrapeContent) {
		this.scrapeContent = scrapeContent;
	}

	@Override
	public String toString() {
		return "Scrape [id=" + id + ", targetUrl=" + targetUrl + ", targetCssPath=" + targetCssPath + ", scrapeContent="
				+ scrapeContent + "]";
	}

	

}

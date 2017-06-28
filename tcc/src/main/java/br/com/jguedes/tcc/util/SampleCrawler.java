package br.com.jguedes.tcc.util;

import java.io.InputStreamReader;
import java.net.URLDecoder;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

public class SampleCrawler extends WebCrawler {

	private static RegexURLFilter crawlFilter = null;

	public SampleCrawler() {
		super();
		try {
			crawlFilter = new RegexURLFilter(
					new InputStreamReader(SampleCrawler.class.getResourceAsStream("regex-urlfilter.crawl.txt")));
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	@Override
	public boolean shouldVisit(Page page, WebURL url) {

		try {
			String href = URLDecoder.decode(url.getURL(), "UTF-8");
			boolean shouldVisit = crawlFilter.filter(href) != null;
			logger.trace((shouldVisit ? "Crawling" : "Not crawling") + " URL: " + href);
			return shouldVisit;
		} catch (Exception e) {
			logger.error("Error in 'shouldVisit()': ", e);
			return false;
		}

	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		// Some business logic I won't expose here
		// My boss won't be happy ;-)
	}
}
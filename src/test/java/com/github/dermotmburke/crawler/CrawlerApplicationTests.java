package com.github.dermotmburke.crawler;

import com.github.dermotmburke.crawler.model.Page;
import com.github.dermotmburke.crawler.parsing.LinkExtractor;
import com.github.dermotmburke.crawler.utilities.server.WebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.dermotmburke.crawler.utilities.path.PathFinder.path;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CrawlerApplicationTests {

	private WebServer server = new WebServer();

	@AfterEach
	public void before(){
		server.stop();
	}

	@Test
	@DisplayName("Should crawl simple site with one link")
	public void shouldCrawlSimpleSite() {
		String url = server.serve(path("SimpleSite"));
		Crawler crawler = new Crawler(url, new LinkExtractor());
		Page page = crawler.crawl();
		assertThat(page.getUrl()).isEqualTo(url);
		assertThat(page.getPages()).extracting("url").containsExactly(url + "child.html");
	}

	@Test
	@DisplayName("Should crawl simple site with multiple pages")
	public void shouldCrawlSimpleSiteWithMultiplePages() {
		String url = server.serve(path("SimpleSiteWithMultiplePages"));
		Crawler crawler = new Crawler(url, new LinkExtractor());
		Page page = crawler.crawl();
		assertThat(page.getUrl()).isEqualTo(url);
		assertThat(page.getPages()).extracting("url").containsExactly(url + "child.html");
		Page childPage = getFirstChildPage(page);
		assertThat(childPage.getPages()).extracting("url").containsExactly(url + "grandchild.html");
		Page grandChildPage = getFirstChildPage(childPage);
		assertThat(grandChildPage.getPages()).extracting("url").containsExactly(url + "greatgrandchild.html");
		Page greatGrandChildPage = getFirstChildPage(grandChildPage);
		assertThat(greatGrandChildPage.getPages()).extracting("url").containsExactly(url + "greatgreatgrandchild.html");
	}

	@Test
	@DisplayName("Should crawl site with recursive links")
	public void shouldCrawlSiteWithRecursiveLinks() {
		String url = server.serve(path("SiteWithRecursiveLinks"));
		Crawler crawler = new Crawler(url, new LinkExtractor());
		Page page = crawler.crawl();
		assertThat(page.getUrl()).isEqualTo(url);
		assertThat(page.getPages()).extracting("url").containsExactlyInAnyOrder(url + "child.html", url + "index.html");
		Page childPage = getFirstChildPage(page);
		assertThat(childPage.getPages()).extracting("url").containsExactlyInAnyOrder(url + "child.html", url + "index.html");
	}

	private Page getFirstChildPage(Page page){
		return page.getPages().iterator().next();
	}
}

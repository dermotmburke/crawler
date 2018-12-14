package com.github.dermotmburke.crawler;

import com.github.dermotmburke.crawler.parsing.LinkExtractor;
import com.github.dermotmburke.crawler.model.Page;
import com.github.dermotmburke.crawler.utilities.server.WebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.github.dermotmburke.crawler.utilities.path.PathFinder.path;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PageExtractorTest {

    private LinkExtractor linkExtractor = new LinkExtractor();
    private WebServer server = new WebServer();

    @AfterEach
    public void before(){
        server.stop();
    }

    @Test
    @DisplayName("Should extract single link")
    void shouldExtractLinks() {
        String url = server.serve(path("SimpleSiteWithMultiplePages"));
        Set<Page> results = linkExtractor.extractLinks(url);
        assertThat(results).extracting("url").containsExactly(url + "child.html");
    }

    @Test
    @DisplayName("Should extract multiple links")
    void shouldExtractMultipleLinks() {
        String url = server.serve(path("SiteWithRecursiveLinks"));
        Set<Page> results = linkExtractor.extractLinks(url);
        assertThat(results).extracting("url").containsExactlyInAnyOrder(url + "child.html", url + "index.html");
    }
}
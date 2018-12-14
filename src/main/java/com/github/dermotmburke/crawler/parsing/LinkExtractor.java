package com.github.dermotmburke.crawler.parsing;

import com.github.dermotmburke.crawler.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkExtractor {

    static final Logger LOG = LoggerFactory.getLogger(LinkExtractor.class);

    public Set<Page> extractLinks(String url) {
        LOG.debug("extracting links from url: {}", url);
        HashSet<Page> pages = new HashSet<>();
        try {
            Document document = Jsoup.connect(url).timeout(0).get();
            Elements linksOnPage = document.select("a[href]");
            for (Element page : linksOnPage) {
                pages.add(new Page(page.attr("abs:href")));
            }
            return pages;
        } catch (Exception e) {
            LOG.error("Error extracting links from url: {} Error: {}", url,  e.getMessage());
        }
        return pages;
    }
}

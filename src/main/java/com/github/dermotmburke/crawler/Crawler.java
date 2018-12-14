package com.github.dermotmburke.crawler;

import com.github.dermotmburke.crawler.model.Page;
import com.github.dermotmburke.crawler.parsing.LinkExtractor;
import com.github.dermotmburke.crawler.domain.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Crawler {

    static final Logger LOG = LoggerFactory.getLogger(LinkExtractor.class);

    private LinkExtractor linkExtractor;
    private final Queue<Page> queue = new LinkedList<>();
    private final HashSet<Page> queued = new HashSet<>();
    private Domain domain;
    private Page page;

    public Crawler(String root, LinkExtractor linkExtractor) {
        this.linkExtractor = linkExtractor;
        this.page = new Page(root);
        this.domain = new Domain(root);
        LOG.info("New crawler created for url {} with domain {}", root, domain);
    }

    public Page crawl() {
        LOG.debug("Starting crawl");
        processLink(page);
        processQueue();
        return page;
    }

    public void processQueue() {
        while (true) {
            Page page = queue.poll();
            if (page == null) {
                return;
            }
            processLink(page);
        }
    }

    private void processLink(Page page) {
        page.addAll(linkExtractor.extractLinks(page.getUrl()));
        page.getPages().stream().filter(this::shouldQueue).forEach(this::processChild);
    }

    private void processChild(Page page) {
        queue.add(page);
        queued.add(page);
    }

    protected boolean shouldQueue(Page page) {
        return (!queued.contains(page)) && (domain.isInDomain(page.getUrl()));
    }

    @Override
    public String toString() {
        return "Crawler{" +
                "domain=" + domain +
                '}';
    }
}

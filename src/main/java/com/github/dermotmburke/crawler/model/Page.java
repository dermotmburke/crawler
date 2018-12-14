package com.github.dermotmburke.crawler.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Page {

    private final String url;
    private final Set<Page> pages = new LinkedHashSet<>();

    public Page(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void addAll(Set<Page> pages){
        this.pages.addAll(pages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", pages=" + pages +
                '}';
    }
}

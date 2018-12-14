package com.github.dermotmburke.crawler.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DomainTest {

    @Test
    @DisplayName("Test url is in same domain")
    void shouldBeInSameDomain() {
        Domain domain = new Domain("http://www.bbc.co.uk");
        assertThat(domain.isInDomain("https://www.bbc.co.uk/news")).isTrue();
    }

    @Test
    @DisplayName("Test url is not in same domain")
    void shouldNotBeInSameDomain() {
        Domain domain = new Domain("http://www.bbc.co.uk");
        assertThat(domain.isInDomain("https://edition.cnn.com/")).isFalse();
    }

    @Test
    @DisplayName("Test relative url is in same domain")
    void relativeUrlShouldBeInSameDomain() {
        Domain domain = new Domain("http://www.bbc.co.uk");
        assertThat(domain.isInDomain("news")).isTrue();
    }

    @Test
    @DisplayName("Test relative url from root is in same domain")
    void relativeUrlFromRootShouldBeInSameDomain() {
        Domain domain = new Domain("http://www.bbc.co.uk");
        assertThat(domain.isInDomain("/news")).isTrue();
    }

    @Test
    @DisplayName("Test relative url from root is in same domain")
    void urlOnDifferentPortsShouldNotBeInSameDomain() {
        Domain domain = new Domain("http://localhost:8080");
        assertThat(domain.isInDomain("http://localhost:8090")).isFalse();
    }

    @Test
    @DisplayName("Test http and https sites are considered as the same domain")
    void shouldDisregardSecureSite() {
        Domain domain = new Domain("http://www.bbc.co.uk");
        assertThat(domain.isInDomain("https://www.bbc.co.uk")).isTrue();
    }
}
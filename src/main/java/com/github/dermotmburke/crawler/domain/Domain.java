package com.github.dermotmburke.crawler.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Domain {

    private static final Pattern DOMAIN_PATTERN = Pattern.compile("^(?:http://www.|https://www.|http://|www.|https://)([^/]+)");
    private String domain;

    public Domain(String url){
        this.domain = extractDomain(url);
    }

    public boolean isInDomain(String url){
        Matcher m = DOMAIN_PATTERN.matcher(url);
        if (m.find()) {
            return domain.equals(m.group(1));
        }
        return true;
    }

    private String extractDomain(String url){
        Matcher m = DOMAIN_PATTERN.matcher(url);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "domain='" + domain + '\'' +
                '}';
    }
}

package com.github.dermotmburke.crawler;

import com.github.dermotmburke.crawler.model.Page;
import com.github.dermotmburke.crawler.parsing.LinkExtractor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void main(String[] args) {
		String root = args[0];
		LinkExtractor linkExtractor = new LinkExtractor();
		Crawler crawler = new Crawler(root, linkExtractor);
		Page site = crawler.crawl();
		System.out.print(GSON.toJson(site));
	}
}

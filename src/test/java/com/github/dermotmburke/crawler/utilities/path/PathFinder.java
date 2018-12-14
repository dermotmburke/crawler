package com.github.dermotmburke.crawler.utilities.path;

import java.nio.file.Paths;

public class PathFinder {
    public static String path(String directory) {
        return Paths.get("src","test","resources", directory).toAbsolutePath().toString();
    }
}

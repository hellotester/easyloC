package org.luka.framework.core.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ResourceResolver {


    private final String packagePath;

    protected ResourceResolver(String packagePath) {
        this.packagePath = packagePath;
    }


    public <R> List<R> scan(Function<Resource, R> mapper) {
        String rootPath = packagePath.replaceAll("\\.", "/");
        ArrayList<R> rs = new ArrayList<>();
        try {
            scan(rootPath, rs, mapper);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }


    private <R> void scan(String packagePath, List<R> collector, Function<Resource, R> mapper) throws IOException, URISyntaxException {
        Enumeration<URL> resources = getContextCassLoad().getResources(packagePath);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            String fullPath = removePreSlash(url.getPath());
            String classPath = fullPath.substring(0, fullPath.length() - packagePath.length());
            if (url.getProtocol().equalsIgnoreCase("file")) {
                scanFile(classPath, fullPath, collector, mapper);
            } else if (url.getProtocol().equalsIgnoreCase("jar")) {
                scanJar(packagePath, url.toURI(), collector, mapper);
            }
        }
    }

    private <R> void scanFile(String classPath, String dirPath, List<R> collector, Function<Resource, R> mapper) throws IOException {
        try (Stream<Path> pathStream = Files.walk(Paths.get(dirPath))) {
            pathStream.filter(Files::isRegularFile)
                    .map(f -> new Resource(f.toString(), f.toString().substring(classPath.length())))
                    .forEach(resource -> collector.add(mapper.apply(resource)));
        }
    }

    private <R> void scanJar(String packagePath, URI jarPath, List<R> collector, Function<Resource, R> mapper) throws IOException {
        try (FileSystem fileSystem = FileSystems.newFileSystem(jarPath, Collections.emptyMap())) {
            try (Stream<Path> pathStream = Files.walk(fileSystem.getPath(packagePath))) {
                pathStream.filter(Files::isRegularFile)
                        .map(f -> {
                            String jarPathStr = jarPath.toString();
                            String classpath = jarPathStr.substring(0, jarPathStr.length() - packagePath.length());
                            return new Resource(removePostSlash(classpath), removePreSlash(f.toString()));
                        })
                        .forEach(resource -> collector.add(mapper.apply(resource)));
            }
        }


    }


    private String removePreSlash(String str) {
        if (str.startsWith("/") || str.startsWith("\\")) {
            return str.substring(1);
        }
        return str;
    }

    private String removePostSlash(String str) {
        if (str.endsWith("/") || str.endsWith("\\")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }


    private ClassLoader getContextCassLoad() {
        ClassLoader classLoader = Thread.class.getClassLoader();
        return classLoader == null ? getClass().getClassLoader() : classLoader;
    }
}

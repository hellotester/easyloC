package org.luka.framework.core.io;

public class Resource {

    public String getName() {
        return name;
    }

    public String getClasspath() {
        return classpath;
    }

    private final String name;
    private final String classpath;

    Resource(String classpath, String name) {
        this.name = name;
        this.classpath = classpath;
    }


}

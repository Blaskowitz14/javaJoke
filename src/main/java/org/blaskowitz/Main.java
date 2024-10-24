package org.blaskowitz;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    private final static String PATH = "/home/blaskowitz/joke";
    private final static String FILE_NAME = "joke.java";
    private final static String FILE_CONTENT = "class HelloWorld {\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.println(\"Hello World!\");\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) {
        //JokeService.createFilesRecursively(Paths.get(PATH), FILE_NAME, FILE_CONTENT);
        JokeService.createFilesWithWalk(Paths.get(PATH), FILE_NAME, FILE_CONTENT);
    }
}


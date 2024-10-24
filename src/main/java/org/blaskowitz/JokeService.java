package org.blaskowitz;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JokeService {
    public static void createFilesRecursively(Path path, String fileName, String fileContent) {
        if (!isValidPath(path)) {
            throw new RuntimeException("Invalid path");
        }
        Path filePath;
        if (Files.notExists(filePath = Paths.get(path.toString(), fileName))) {
            try {
                Files.createFile(filePath);
                writeContent(filePath, fileContent);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (Stream<Path> fileStream = Files.list(path)) {
            fileStream.forEach(x -> {
                if (Files.isDirectory(x)) {
                    createFilesRecursively(x, fileName, fileContent);
                }
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createFilesWithWalk(Path path, String fileName, String content) {
        SimpleFileCreator fileCreator = new SimpleFileCreator(fileName, content);
        try {
            Files.walkFileTree(path, fileCreator);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeContent(Path path, String content) throws IOException {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
            fileWriter.write(content);
        }
    }

    private static boolean isValidPath(Path path) {
        return path.isAbsolute() && Files.exists(path);
    }
}

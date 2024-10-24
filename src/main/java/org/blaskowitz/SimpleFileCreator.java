package org.blaskowitz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SimpleFileCreator extends SimpleFileVisitor<Path> {
    private final String fileName;
    private final String fileContent;

    public SimpleFileCreator(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        try {
            dir = Paths.get(dir.toString(), fileName);
            Files.createFile(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        if (!(file.getFileName().toString()).equals(fileName)) {
            return FileVisitResult.CONTINUE;
        }
        try (BufferedWriter fileWriter = Files.newBufferedWriter(file)) {
            fileWriter.write(fileContent);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileVisitResult.CONTINUE;
    }

}
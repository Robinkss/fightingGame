package org.acme.compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Compiler {
    public static void main(String[] args) {

        Path path = Paths.get("org/acme/compiler");

        // create a file named "Sum" in compiled directory
        Path compiledDir = path;
        try {
            Files.createDirectories(compiledDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path sumFile = compiledDir.resolve("Sum.java");

        // if file exists then delete the file
        if (Files.exists(sumFile)) {
            try {
                Files.delete(sumFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.createFile(sumFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the initial content in the file
        String initialContent = "package org.acme.compiler;\n\npublic class Sum {\n";
        try {
            Files.write(sumFile, initialContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // open all files in the directory and read the content
        Path toBeCompiledDir = path;
        List<Path> files = new ArrayList<>();
        try {
            Files.walk(toBeCompiledDir)
                    .filter(Files::isRegularFile)
                    .forEach(files::add);
            // remove all files that do not end with ".sum"
            files.removeIf(file -> !file.getFileName().toString().endsWith(".sum"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // create the router
        String routerContent = "  public static Integer call(String action) {\n    switch (action) {\n";
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(".sum", "");
            routerContent += String.format("      case \"%s\":\n        return %s();\n", fileName, fileName);
        }
        routerContent += "      default:\n        return 0;\n    }\n  }\n";
        try {
            Files.write(sumFile, routerContent.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // create methods for each file
        for (Path file : files) {
            String fileName = file.getFileName().toString().replace(".sum", "");
            System.out.println("Creating method for " + fileName);
            try {
                List<String> lines = Files.readAllLines(file);
                int sum = 0;
                for (String line : lines) {
                    sum += Integer.parseInt(line);
                }
                String methodContent = String.format("  public static Integer %s() {\n    return %d;\n  }\n", fileName, sum);
                Files.write(sumFile, methodContent.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // close the Sum class
        String finalContent = "}\n";
        try {
            Files.write(sumFile, finalContent.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

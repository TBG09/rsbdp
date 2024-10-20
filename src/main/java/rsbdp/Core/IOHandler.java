package rsbdp.Core;


import rsbdp.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IOHandler {

    public static class IO {
        File myObj;
        File myObj2;

        public void create(String objectName) {
            try {
                myObj = new File(objectName); // Initialize myObj properly
                if (myObj.createNewFile())
                    Logger.info("IOHandler", "File " + objectName + " Created Successfully! :D");
            } catch (IOException e) {
                Logger.fatal("IOHandler", "An error occurred while trying to create your file: " + e);
            }
        }

        public void CreateDirectory(String DirectoryPath) throws IOException {
            Files.createDirectories(Path.of(DirectoryPath));
        }

        public int LineCount(String filePath) {
            int lineCount = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                while (br.readLine() != null) {
                    lineCount++;
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }

            return lineCount;  // Return the total line count
        }

        public String LineStringGet(String file, Integer line) {
            String result = null;
            int currentLine = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String currentLineContent;
                while ((currentLineContent = br.readLine()) != null) {
                    currentLine++;
                    if (currentLine == line) {
                        result = currentLineContent;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result; // UwU (please ignore this lol)
        }

        public String readContents(String filePath) {
            StringBuilder content = new StringBuilder(); // Use StringBuilder to accumulate file contents
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append(System.lineSeparator()); // Append each line and a new line separator
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString(); // Return the accumulated content as a String
        }

        public void FileSize(String unit, String filePath) {
            File file = new File(filePath);
            if (!file.exists()) {
                Logger.error("IOHandler", "File does not exist: " + filePath);
                System.exit(1);
            }

            long fileSizeInBytes = file.length(); // Get file size in bytes

            double convertedSize = 0.0;
            switch (unit) {
                case "MB":
                    convertedSize = fileSizeInBytes / (1024.0 * 1024.0); // Convert bytes to megabytes
                    break;
                case "KB":
                    convertedSize = fileSizeInBytes / 1024.0; // Convert bytes to kilobytes
                    break;
                case "B":
                    convertedSize = fileSizeInBytes; // Already in bytes
                    break;
            }

            Logger.info("IOHandler", String.format("File size: %.2f %s", convertedSize, unit));
        }

        public boolean FileExists(String ObjectName) {
            try {
                        myObj = new File(ObjectName);
                        return myObj.exists();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void delete(String objectName) {
            try {
                myObj = new File(objectName);
                if (myObj.delete()) {
                    Logger.info("IOHandler", "File " + objectName + " deleted successfully!");
                } else {
                    Logger.warn("IOHandler", "Your file couldn't be deleted :(");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void renameFile(String FileName, String FileAfterName) {
            myObj2 = new File(FileName);
            myObj2.renameTo(new File(FileAfterName));
            if (myObj2.renameTo(new File(FileAfterName))) {
                Logger.error("IOHandler", "File failed to be renamed");
            } else {
                Logger.info("IOHandler", "File Renamed Successfully");
            }
        }

        public  boolean isFileEmpty(String filePath) {
            File file = new File(filePath);

            // Check if file exists
            if (!file.exists()) {
                return false;
            }

            // Check if file is empty
            return file.length() == 0;
        }

        public void ReadOnlyChange(String objectName,Boolean ReadOnlyToggle) {
            myObj = new File(objectName);
            boolean writable = myObj.setWritable(ReadOnlyToggle);
        }

        public void write(String objectName, String writeData) {
            try (FileWriter myWriter = new FileWriter(objectName)) {
                myWriter.write(writeData);
                Logger.info("IOHandler", "Successfully wrote to the file.");
            } catch (IOException e) {
                Logger.fatal("IOHandler", "An error occurred while trying to write to the file: " + e);
            }
        }

        public void GetContentsDir(String DirectoryPath) {
            ArrayList<String> contentsList = new ArrayList<>();


            if (Files.exists(Path.of(DirectoryPath)) && Files.isDirectory(Path.of(DirectoryPath))) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DirectoryPath))) {

                    for (Path entry : stream) {
                        contentsList.add(entry.getFileName().toString());
                    }
                    Logger.info("IOHandler", "Contents of the directory: " + contentsList);
                } catch (IOException e) {
                    Logger.error("IOHandler", "Error reading the directory: " + e.getMessage());
                }
            } else {
                Logger.warn("IOHandler", "The specified path is not a directory or doesn't exist.");
            }
        }
    }
}
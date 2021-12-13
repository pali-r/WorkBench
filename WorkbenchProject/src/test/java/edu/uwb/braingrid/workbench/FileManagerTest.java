package edu.uwb.braingrid.workbench;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerTest {
    @Test
    public void testCopyFile() throws IOException {

        new File("./testFile").createNewFile();

        Assertions.assertTrue(FileManager.copyFile(Paths.get("./testFile"), Paths.get("./file" )));
        Assertions.assertTrue(FileManager.copyFile(Paths.get("./testFile"), Paths.get("testDir", "file")));
        Assertions.assertTrue(FileManager.copyFile(Paths.get("./testFile"), Paths.get("testDir", "1", "2", "3", "file")));

        Assertions.assertTrue(Files.exists(Paths.get( "file")));
        Assertions.assertTrue(Files.exists(Paths.get("testDir", "file")));
        Assertions.assertTrue(Files.exists(Paths.get("testDir", "1", "2", "3", "file")));

        new File("./testFile").delete();
        new File("./file").delete();
        new File("./testDir/file").delete();
        new File("./testDir/1/2/3/file").delete();
        new File("./testDir/1/2/3").delete();
        new File("./testDir/1/2").delete();
        new File("./testDir/1").delete();
        new File("./testDir").delete();
    }

    @Test
    public void testCopyFilePathDoesntExist() {
        try {
            FileManager.copyFile(Paths.get("random", "name"), Paths.get("random" ,"location"));
            Assertions.fail("Not expected outcome. Should throw exception");
        } catch (IOException e) {
            Assertions.assertTrue(true);
        }
    }
}

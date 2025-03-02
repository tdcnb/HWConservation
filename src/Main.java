import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GameProgress gameProgress1 = new GameProgress(1, 1, 1, 1.1);
        GameProgress gameProgress2 = new GameProgress(2, 2, 2, 2.2);
        GameProgress gameProgress3 = new GameProgress(3, 3, 3, 3.3);

        saveGame("save.dat1", gameProgress1);
        saveGame("save.dat2", gameProgress2);
        saveGame("save.dat3", gameProgress3);
        List<String> inputFiles = new ArrayList<String>();
        inputFiles.add("save.dat1");
        inputFiles.add("save.dat2");
        inputFiles.add("save.dat3");
        zipFiles("D://Games//output.zip", inputFiles);
    }

    public static void saveGame(String fullPath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(fullPath); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> inputList) throws FileNotFoundException {
        for (String file : inputList){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D://Games//zip_output.zip"));
        FileInputStream fis = new FileInputStream(file);) {

            ZipEntry entry = new ZipEntry(file);
            zout.putNextEntry(entry);
            byte[] buffer = new byte [fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
                } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
}
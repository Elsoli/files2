package ru.netology;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static List<String> zipList = new ArrayList<>();

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(150, 10, 1, 300);
        GameProgress save2 = new GameProgress(120, 15, 2, 800);
        GameProgress save3 = new GameProgress(100, 20, 5, 1300);
        saveGame("d:/IdeaProjects/Games/Savegames/Save1.dat", save1);
        saveGame("d:/IdeaProjects/Games/Savegames/Save2.dat", save2);
        saveGame("d:/IdeaProjects/Games/Savegames/Save3.dat", save3);
        zipList.add("d:/IdeaProjects/Games/Savegames/Save1.dat");
        zipList.add("d:/IdeaProjects/Games/Savegames/Save2.dat");
        zipList.add("d:/IdeaProjects/Games/Savegames/Save3.dat");

        zipFiles("d:/IdeaProjects/Games/Savegames/Save.zip", zipList);
    }

    public static void saveGame(String path, GameProgress save) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String zipPath, List<String> zipList) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String s : zipList) {
                File file = new File(s);
                FileInputStream fis = new FileInputStream(file.getPath());
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zipOut.write(buffer);
                fis.close();
                zipOut.closeEntry();
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

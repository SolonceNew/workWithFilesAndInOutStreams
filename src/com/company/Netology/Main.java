package com.company.Netology;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    //сохранение сериализации
    private static void saveGame(String filePath, GameProgress instance) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(instance);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
   //архивация
    private static void zipFiles(String filePath, List<String> listObjectPath) {
        try (ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(filePath))) {
            int count = 1;
            for (String path : listObjectPath) {
                FileInputStream fis = new FileInputStream(path);
                ZipEntry entry = new ZipEntry("packed_save" + count++ + ".dat");
                zous.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zous.write(buffer);
                zous.closeEntry();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //удаление незапакованных файлов
    private static void deleteUnpackingFiles() {
        File files = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames");
        if(files.isDirectory()) {
            for(File file : files.listFiles()) {
                if(!file.getName().contains(".zip")) {
                    file.delete();
                }
            }
        }
    }
    //распаковка
    private static void zipUnpacking(String pathFrom, String pathTo) {
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(pathFrom))) {
            ZipEntry zipEntry;
            String name;
            while((zipEntry = zis.getNextEntry()) != null) {
                name = zipEntry.getName();
                FileOutputStream fos = new FileOutputStream(pathTo + "\\" + name);
                for(int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
    //десериализация
    private static GameProgress openProgress (String path) throws ClassNotFoundException {
        GameProgress gameProgress = null;
        try(FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return gameProgress;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();

        //создание поддиректории src в Games
        File folderSrc = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\src");
       if(folderSrc.mkdir()){
           sb.append("Поддиректория ")
                    .append(folderSrc.getName())
                    .append(" в 'Games' успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderSrc.getName())
                    .append("\n");
        }
        //создание поддиректории res в Games
        File folderRes = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\res");
        if(folderRes.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderRes.getName())
                    .append(" в 'Games' успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderRes.getName())
                    .append("\n");
        }
        //cоздание поддиректории savegames в Games
        File folderSaveGames = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames");
        if(folderSaveGames.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderSaveGames.getName())
                    .append(" в 'Games' успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderSaveGames.getName())
                    .append("\n");
        }
        //создание поддиректории temp в Games
        File folderTemp = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\temp");
        if(folderTemp.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderTemp.getName())
                    .append(" в 'Games' успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderTemp.getName())
                    .append("\n");
        }
        //создание директории main в src
        File folderMain = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\src\\main");
        if(folderMain.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderMain.getName())
                    .append(" в ").append(folderSrc.getName()).append(" успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderMain.getName())
                    .append("\n");
        }
        //содание поддиректории test в src
        File folderTest = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\src\\test");
        if(folderTest.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderTest.getName())
                    .append(" в ").append(folderSrc.getName()).append(" успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderTest.getName())
                    .append("\n");
        }
        //создание файла Main.java в поддиректории main
        File fileMain = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\src\\main\\Main.java");
        try {
            if(fileMain.createNewFile()) {
                sb.append("Файл ")
                        .append(fileMain.getName())
                        .append(" в ").append(folderMain.getName()).append(" успешно создан")
                        .append("\n");
            } else {
                sb.append("Не удалось создать файл ")
                        .append(fileMain.getName())
                        .append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //создание файла Utils.java в поддиректории main
        File fileUtils = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\src\\main\\Utils.java");
        try {
            if(fileUtils.createNewFile()) {
                sb.append("Файл ")
                        .append(fileUtils.getName())
                        .append(" в ").append(folderMain.getName()).append(" успешно создан")
                        .append("\n");
            } else{
                sb.append("Не удалось создать файл ")
                        .append(fileUtils.getName())
                        .append("\n");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //создание поддиректории drawables в res
        File folderDrawables = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\res\\drawables");
        if(folderDrawables.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderDrawables.getName())
                    .append(" в ").append(folderRes.getName()).append(" успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderDrawables.getName())
                    .append("\n");
        }
        //создание поддиректории vectors в res
        File folderVectors = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\res\\vectors");
        if(folderVectors.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderVectors.getName())
                    .append(" в ").append(folderRes.getName()).append(" успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderVectors.getName())
                    .append("\n");
        }
        //создание файла icons в поддиректории res
        File folderIcons = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\res\\icons");
        if(folderIcons.mkdir()) {
            sb.append("Поддиректория ")
                    .append(folderIcons.getName())
                    .append(" в ").append(folderRes.getName()).append(" успешно создана")
                    .append("\n");
        } else {
            sb.append("Не удалось создать поддиректорию ")
                    .append(folderIcons.getName())
                    .append("\n");
        }
       // создание файла temp в поддиректории temp
        File fileTemp = new File("C:\\Users\\fabri\\IdeaProjects\\Games\\temp\\temp.txt");
        try {
            if(fileTemp.createNewFile()) {
                sb.append("Файл ")
                        .append(fileTemp.getName())
                        .append(" в ").append(folderTemp.getName()).append(" успешно создан")
                        .append("\n");
            } else {
                sb.append("Не удалось создать поддиректорию ")
                        .append(folderVectors.getName())
                        .append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
     //   запись в файл temp.txt данных о создании поддиректорий и файлов
        try(FileWriter fw = new FileWriter("C:\\Users\\fabri\\IdeaProjects\\Games\\temp\\temp.txt")) {
           fw.write(sb.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
   //     вывод на консоль данных о создании поддиректорий и файлов
        System.out.println(sb.toString());

//        Задание 2
        GameProgress gameProgressInst1 = new GameProgress(100, 3, 5, 300.2);
        GameProgress gameProgressInst2 = new GameProgress(25, 2, 3, 120.2);
        GameProgress gameProgressInst3 = new GameProgress(45, 6, 7, 500.5);

        saveGame("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save1.dat", gameProgressInst1);
        saveGame("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save2.dat", gameProgressInst2);
        saveGame("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save3.dat", gameProgressInst3);

        List<String> paths = new ArrayList<>();
        paths.add("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save1.dat");
        paths.add("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save2.dat");
        paths.add("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save3.dat");

        zipFiles("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save.zip", paths);
        deleteUnpackingFiles();

        // Задание 3
        zipUnpacking("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\save.zip", "C:\\Users\\fabri\\IdeaProjects\\Games\\savegames");
        System.out.println(openProgress("C:\\Users\\fabri\\IdeaProjects\\Games\\savegames\\packed_save1.dat"));
     }
}

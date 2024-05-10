import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String rootDirectory = "C://Games";
        File gamesDirectory = new File(rootDirectory);
        if (!gamesDirectory.exists()) {
            if (!gamesDirectory.mkdir()) {
                System.out.println("Ой! Кажется не получилось создать Games директорий");
                return;
            }
        }

        String[] directoriesToCreate = {
                "src",
                "src/main",
                "src/test",
                "res",
                "res/drawables",
                "res/vectors",
                "res/icons",
                "savegames",
                "temp"
        };

        String[] filesToCreate = {
                "src/main/Main.java",
                "src/main/Utils.java",
                "res/temp.txt"
        };

        StringBuilder log = new StringBuilder();

        for (String dir : directoriesToCreate) {
            File directory = new File(rootDirectory, dir);
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    log.append("Не получилось создать директорий: ").append(dir).append("\n");
                } else {
                    log.append("Был создан директорий: ").append(dir).append("\n");
                }
            } else {
                log.append("Упс! Кажется такой директорий уже существует: ").append(dir).append("\n");
            }
        }

        for (String file : filesToCreate) {
            File newFile = new File(rootDirectory, file);
            if (!newFile.exists()) {
                try {
                    if (newFile.createNewFile()) {
                        log.append("Создан фойл: ").append(file).append("\n");
                    } else {
                        log.append("Ой! Не получилось создать файл: ").append(file).append("\n");
                    }
                } catch (IOException e) {
                    log.append("Ой! Кажется произошла ошибка создания файла: ").append(file).append("\n");
                    e.printStackTrace();
                }
            } else {
                log.append("Этот файл уже существует!: ").append(file).append("\n");
            }
        }

        try (FileWriter fileWriter = new FileWriter(new File(rootDirectory, "res/temp.txt"))) {
            fileWriter.write(log.toString());
            System.out.println("Сохранено в temp.txt");
        } catch (IOException e) {
            System.out.println("Ой! Не получилось сохранить в temp.txt");
            e.printStackTrace();
        }
    }
}
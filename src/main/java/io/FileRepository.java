package io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.State;
import model.Task;
import model.TaskData;

public final class FileRepository {

  private static final String DIR = "ressources";
  private static final String FILE = "tasks";

  private Path toDir;
  private Path toFile;


  public FileRepository(){
    toDir = Paths.get(DIR);
    toFile = toDir.resolve(FILE);
  }

  public void save2(List<Task> tasks) {

    createDirAndFile();
    StringBuilder builder = new StringBuilder();
    System.out.println("save tasks to file ...");
    try ( OutputStream os = new BufferedOutputStream(new FileOutputStream(toFile.toFile()))) {
      
      for (Task task : tasks) {
        builder.append(task.getId() + ",");
        builder.append(task.getTitle() + ",");
        builder.append(task.getDescription() + ",");
        builder.append(task.getState().toString() + ",");
        builder.append(task.getDate().toString() + "\n");
      }
      String encoded = Base64.getEncoder().encodeToString(builder.toString().getBytes());
      os.write(encoded.getBytes());
    } catch (final Exception e) {
      e.printStackTrace();
    }
    System.out.println("done!");
    load2();
  }

  public List<Task> load2() {
    System.out.println("loading 2 task from file ...");
    List<Task> list = new ArrayList<>();
    
    try (Stream<String> lines = Files.lines(toFile, StandardCharsets.UTF_8)) {
      list = lines.map(e -> Base64.getDecoder().decode(e))
                  .map(String::new)
                  .map(e -> e.split("\n"))
                  .flatMap(Arrays::stream)
                  .map(e -> {
                    String[] s = e.split(",");
                    return new Task(Integer.parseInt(s[0]), new TaskData(s[1], s[2], State.valueOf(s[3]), LocalDate.parse(s[4])));
                  })
                  .collect(Collectors.toList());

    } catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("done 2: Num of Tasks: " + list.size());
    return list;
  }
  
  private void createDirAndFile() {
    try {
      if (!Files.exists(toDir)) {
        Files.createDirectory(toDir);
        System.out.println("create directory...");
      } else {
        System.out.println("directory allready exists");
      }
      if (!Files.exists(toFile)) {
        Files.createFile(toFile);
        System.out.println("create file...");
      } else {
        System.out.println("file allready exists");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

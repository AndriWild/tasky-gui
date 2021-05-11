import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentation.TaskyPM;

public class TaskyPMTest {

  TaskyPM tasky;

  @BeforeEach
  public void setup(){
    tasky = TaskyPM.getInstance();
  }

  @Test
  public void singletonTest(){
    assertNotNull(TaskyPM.getInstance());
  }

  @Test
  public void createTaskTest(){
    // given
    int numberOfTask = 0;
    
    // when
    tasky.createTask();

    // then
    assertEquals(numberOfTask + 1, tasky.getTaskList().size() );
  }
  
  @Test
  public void deleteTaskTest(){
    // given
    int numberOfTasks = tasky.getTaskList().size();
    tasky.createTask();

    // when
    tasky.deleteTask();

    // then
    assertEquals(numberOfTasks, tasky.getTaskList().size());
  }
  
}

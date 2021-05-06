import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import presentation.TaskyPM;

public class TaskyPMTest {

  @Test
  public void singletonTest(){
    assertNotNull(TaskyPM.getInstance());
  }

  
  
}

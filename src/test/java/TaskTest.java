import org.junit.Test;
import ua.sumdu.j2se.julia.tasks.ArrayTaskList;
import ua.sumdu.j2se.julia.tasks.Task;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    ArrayTaskList arrayTaskList=new ArrayTaskList(4);


    @Test
    public void testGetTask() throws Exception
    {
        arrayTaskList.add(new Task("зарядка",3));
        arrayTaskList.add(new Task("подъем",3));
        arrayTaskList.add(new Task("обед",5));
        Task task=new Task("подъем",3);
        Task task1= arrayTaskList.getTask(0);
        task.equals(task1);


    }
    @Test
    public void testSize() throws Exception
    {
        arrayTaskList.add(new Task("зарядка",3));
        assertEquals(1,arrayTaskList.size());
    }
}

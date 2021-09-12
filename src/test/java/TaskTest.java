
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
        Task task1= arrayTaskList.getTask(1);
        assertEquals(task,task1);


    }
    @Test
    public void testSize() throws Exception
    {
        arrayTaskList.add(new Task("зарядка",3));
        assertEquals(1,arrayTaskList.size());
    }

    @Test
    public void testIncoming()
    {
        Task task1= new Task("Встреча с девушкой", 112);

        Task task2=new Task("Прием лекарств",12,204,12);
        Task task3=new Task("Встреча с друзьми", 306);
        Task task4=new Task("Утренняя пробежка",0,326,24);
        ArrayTaskList expected=new ArrayTaskList();
        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);
        expected.add(task2);
        expected.add(task4);
        ArrayTaskList arrayTaskList=new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        arrayTaskList.add(task4);
        ArrayTaskList actual=arrayTaskList.incoming(128,152);
        assertEquals(expected,actual);


    }

    @Test
    public void newTask()
    {
        new Task("Встреча с девушкой", -5);
    }

    @Test
    public void newTaskInterval()
    {
        new Task("Встреча с девушкой", 30,25,0);
    }

    @Test
    public void addTask()
    {
        ArrayTaskList arrayTaskList=new ArrayTaskList();
        arrayTaskList.add(null);
    }

    @Test
    public void removeTask()
    {
        ArrayTaskList expected= new ArrayTaskList();
        expected.add(new Task("Встреча с девушкой", 112));
        expected.add(new Task("Встреча с друзьми", 306));

        ArrayTaskList actual= new ArrayTaskList();

        actual.add(new Task("Встреча с девушкой", 112));
        actual.add(new Task("Прием лекарств",12,204,12));
        actual.add(new Task("Встреча с друзьми", 306));

        actual.remove(actual.getTask(1));

        assertEquals(expected,actual);
    }
}

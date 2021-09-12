import org.junit.Test;

import ua.sumdu.j2se.julia.tasks.ArrayTaskList;
import ua.sumdu.j2se.julia.tasks.LinkedTaskList;
import ua.sumdu.j2se.julia.tasks.Task;

import static org.junit.Assert.assertEquals;

public class LinkedTaskTest {

    @Test
    public void addTest()
    {
        LinkedTaskList linkedTaskTest=new LinkedTaskList();
        linkedTaskTest.add(new Task("Прием лекарств",12,204,12));
        System.out.println(linkedTaskTest.toString());
    }

    @Test
    public void sizeTest()
    {
        LinkedTaskList linkedTaskTest=new LinkedTaskList();
        linkedTaskTest.add(new Task("Прием лекарств",12,204,12));
        linkedTaskTest.add(new Task("Встреча с друзьми", 306));
        assertEquals (2, linkedTaskTest.size());

    }

    @Test
    public void testIncoming()
    {
        Task task1= new Task("Встреча с девушкой", 112);
        Task task2=new Task("Прием лекарств",12,204,12);
        Task task3=new Task("Встреча с друзьми", 306);
        Task task4=new Task("Утренняя пробежка",0,326,24);
        LinkedTaskList expected=new LinkedTaskList();
        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);
        expected.add(task2);
        expected.add(task4);
        LinkedTaskList linkedTaskList=new LinkedTaskList();
        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);
        LinkedTaskList actual=linkedTaskList.incoming(128,152);
        assertEquals(expected,actual);

    }

    @Test
    public void removeTest()
    {
       LinkedTaskList expected= new LinkedTaskList();
        expected.add(new Task("Встреча с девушкой", 112));
        expected.add(new Task("Встреча с друзьми", 306));

        LinkedTaskList actual= new LinkedTaskList();

        actual.add(new Task("Встреча с девушкой", 112));
        actual.add(new Task("Прием лекарств",12,204,12));
        actual.add(new Task("Встреча с друзьми", 306));

        actual.remove(new Task("Прием лекарств",12,204,12));

        assertEquals(expected,actual);
    }


}

package ua.sumdu.j2se.julia.tasks;


import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>{

    protected int size;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public  int size()
    {
        return size;
    }

    public final AbstractTaskList incoming(int from, int to,ListTypes listTypes)
    {
        if (from<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (to<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (from>to)
            throw new IllegalArgumentException("Начальное время не может быть больше конечного");
        AbstractTaskList array=TaskListFactory.createTaskList(listTypes);
//        for (int i=0;i<size();i++)
//        {
//            if (getTask(i).nextTimeAfter(from)!=-1 && getTask(i).nextTimeAfter(from)<=to)
//            {
//                Task task=getTask(i);
//                array.add(task);
//            }
//
//        }
//        return array;
        getStream()
                .filter(task -> task.nextTimeAfter(from)!=-1 && task.nextTimeAfter(from)<=to)
                .forEach(task -> array.add(task));
        return array;
    }

    public abstract Task getTask(int index);

    @Override
    public String toString() {
        String write=new String();
        for (int i = 0; i < size(); i++) {
            write += getTask(i).toString() + "\n";
        }
        return write;
    }

    public abstract Stream<Task> getStream();

}

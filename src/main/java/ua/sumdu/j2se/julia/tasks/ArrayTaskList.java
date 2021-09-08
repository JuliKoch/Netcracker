package ua.sumdu.j2se.julia.tasks;

public class ArrayTaskList {

    private Task[] tasks;

    private int size=0;

    private int capacity;

    private int CAPACITY=10;

    public ArrayTaskList()
    {
        capacity=CAPACITY;
        tasks=new Task[capacity];
    }

    public ArrayTaskList(int capacity)
    {
        this.capacity=capacity;
        tasks=new Task[capacity];
    }

    public void increaseSize()
    {
        capacity=capacity*2;
        Task[] newTasks=new Task[capacity];
        for (int i=0;i<size;i++)
        {
            newTasks[i]=tasks[i];
        }
        tasks=newTasks;
    }
    public void add (Task task)
    {
        if (size>=capacity)
            increaseSize();
        tasks[size++]=task;
    }

    public boolean remove(Task task)
    {
        if (size==0)
            return false;
        for (int i=0;i<size;i++)
        {
            if(tasks[i].equals(task))
            {
                for (int j=i;j<size;j++)

                    tasks[j]=tasks[j+1];

            }
            return true;
        }
        return false;
    }

    public int size()
    {

        return size;
    }
    public Task getTask (int index)
    {
        return tasks[index];
    }
}

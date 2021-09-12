package ua.sumdu.j2se.julia.tasks;



import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

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
        if (capacity<0)
            throw new NegativeArraySizeException("Размер не может быть отрицательным");
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
        if (task==null)
            throw new IllegalArgumentException("Нельзя добавлять пустые ссылки");
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
                for (int j=i;j<size;j++) {
                    tasks[j] = tasks[j + 1];
                }
                size-=1;
                return true;
            }

        }
        return false;
    }

    public int size()
    {
        return size;
    }
    public Task getTask (int index)
    {
        if ( index>size)
            throw new IndexOutOfBoundsException("Индекс превышает допустимые пределы");
        return tasks[index];
    }

    public ArrayTaskList incoming(int from, int to)
    {
        if (from<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (to<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (from>to)
            throw new IllegalArgumentException("Начальное время не может быть больше конечного");
        ArrayTaskList arrayTaskList=new ArrayTaskList();
        for (int i=0;i<size;i++)
        {
            if (tasks[i].nextTimeAfter(from)!=-1 && tasks[i].nextTimeAfter(from)<=to)
                arrayTaskList.add(tasks[i]);
        }
        return arrayTaskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return size == that.size &&
                capacity == that.capacity &&
                CAPACITY == that.CAPACITY &&
                Arrays.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity, CAPACITY);
        result = 31 * result + Arrays.hashCode(tasks);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                ", size=" + size +
                ", capacity=" + capacity +
                ", CAPACITY=" + CAPACITY +
                '}';
    }
}

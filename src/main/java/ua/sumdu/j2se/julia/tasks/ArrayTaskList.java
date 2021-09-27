package ua.sumdu.j2se.julia.tasks;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {

    private Task[] tasks;

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

    @Override
    public int size()
    {
        return size;
    }
    @Override
    public void add (Task task)
    {
        if (task==null)
            throw new IllegalArgumentException("Нельзя добавлять пустые ссылки");
        if (size>=capacity)
            increaseSize();
        tasks[size++]=task;
    }

    @Override
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
    @Override
    public Task getTask (int index)
    {
        if ( index>size)
            throw new IndexOutOfBoundsException("Индекс превышает допустимые пределы");
        return tasks[index];
    }

    @Override
    public Stream<Task> getStream() {

        Stream.Builder<Task> taskStream=Stream.builder();
        for(int i=0;i<size();i++)
        {
            taskStream.accept(tasks[i]);
        }
        return taskStream.build();
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
    public Iterator<Task> iterator() {
        Iterator<Task> iterator = new Iterator<Task>() {
            private int last = -1;
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size() && tasks[current].equals(null);
            }

            @Override
            public Task next() {
                last = current;
                return tasks[current++];
            }

            @Override
            public void remove() {
                if (current > 0) {
                    ArrayTaskList.this.remove(tasks[last]);
                    current--;
                }else {
                    throw new IllegalStateException();
                }
            }
        };
        return iterator;
    }
}

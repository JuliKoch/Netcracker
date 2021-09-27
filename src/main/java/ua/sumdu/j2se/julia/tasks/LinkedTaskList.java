package ua.sumdu.j2se.julia.tasks;


import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {


    private Node head;
    private Node tail;

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> iterator = new Iterator<Task>() {
            private int last = -1;
            private int current = 0;


            @Override
            public boolean hasNext() {
                return current < size() && getTask(current) != null;
            }

            @Override
            public Task next() {
                last = current;
                return getTask(current++);
            }

            @Override
            public void remove() {
                if (current > 0) {
                    LinkedTaskList.this.remove(getTask(last));
                    current--;
                }else {
                    throw new IllegalStateException();
                }
            }
        };
        return iterator;
    }


    class Node
    {
        private Task task;
        private Node next;

        public Node(Task task)
        {
            this.task=task;
            next=null;
        }

        public Task getTask() {
            return task;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(task, node.task) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, next);
        }


    }

    public LinkedTaskList()
    {
        head=null;
        tail=null;

    }

    public boolean isEmpty()
    {
        return (head==null);
    }

    @Override
    public void add(Task task)
    {
        Node addNode=new Node(task);
        if (head==null) {
            head = addNode;
            tail=addNode;
        }

        else
       tail.next=addNode;
        tail=addNode;
    }

    @Override
    public int size()
    {
        int length=0;
        Node current=head;
        while(current!=null)
        {
            length++;
            current=current.next;
        }
        return length;
    }

    @Override
    public Task getTask(int index) {
        if (index<0 || index>=size())
            throw new IllegalArgumentException("Некорректный индекс");
        else
        {

                Node node=head;
                for(int i=0;i<index;i++)
                {
                    node=node.next;
                }
                return node.getTask();

            }
        }

    @Override
    public Stream<Task> getStream() {
        Stream.Builder<Task> taskStream=Stream.builder();
        Node current=head;
        while (current!=null)
        {
            taskStream.accept(current.getTask());
            current=current.next;
        }
        return taskStream.build();
    }


    @Override
    public boolean remove(Task task)
    {

        Node tmp=head;
        Node p=null;

        if (!isEmpty())
        {
            if (head.getTask().equals(task))
            {
                tmp=head.next;
                head=tmp;
                return true;
            }
            while(!tmp.next.equals(null) && !tmp.next.getTask().equals(task))
                tmp=tmp.next;
            if (tmp.next.equals(null) && tmp.next.getTask().equals(task))
                return false;
            else
            {
                p=tmp.next;
                if (p.equals(null))
                    tail=tmp;
                else
                {
                    tmp.next=p.next;
                    return true;
                }
            }
        }

    return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        return Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }

    public Node getTail() {
        return tail;
    }
}

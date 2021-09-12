package ua.sumdu.j2se.julia.tasks;


import java.util.Objects;

public class LinkedTaskList {

    private Node head;

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
        public String toString() {
            return "Node{" +
                    "task=" + task +
                    ", next=" + next +
                    '}';
        }
    }

    public LinkedTaskList()
    {
        head=null;

    }

    public boolean isEmpty()
    {
        return (head==null);
    }
    public void add(Task task)
    {
        Node addNode=new Node(task);
        if (head==null) {
            head = addNode;
        }

        else
        addNode.next=head;
        head=addNode;
    }

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

    public boolean remove(Task task)
    {
        Node current=head;
        Node previous=head;


        while (!current.getTask().equals(task)) {
            if (current.next == null)
                return false;
            else
            {
                previous=current;
                current=current.next;
            }

        }
        if(current==head)
            head=head.next;
        else
            previous.next=current.next;
        return true;
    }




    public LinkedTaskList incoming(int from, int to) {
        if (from < 0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (to < 0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (from > to)
            throw new IllegalArgumentException("Начальное время не может быть больше конечного");
        LinkedTaskList linkedTaskList = new LinkedTaskList();

        Node current = head;
        while (current!=null)
        {
            if (current.task.nextTimeAfter(from)!=-1 && current.task.nextTimeAfter(from)<=to)
                linkedTaskList.add(current.task);
            current=current.next;

        }

        return linkedTaskList;
    }
    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                '}';
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

}

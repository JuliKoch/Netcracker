package ua.sumdu.j2se.julia.tasks;


import java.util.Objects;

public class LinkedTaskList implements AbstractTaskList {


    private Node head;
    private Node tail;

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
    public boolean remove(Task task)
    {
//        Node current=head;
//        Node previous=head;
//
//
//        while (!current.getTask().equals(task)) {
//            if (current.next == null)
//                return false;
//            else
//            {
//                previous=current;
//                current=current.next;
//            }
//
//        }
//        if(current==head)
//            head=head.next;
//        else
//            previous.next=current.next;
//        return true;

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

    public Node getTail() {
        return tail;
    }
}

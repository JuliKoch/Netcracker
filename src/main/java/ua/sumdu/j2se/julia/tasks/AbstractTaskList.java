package ua.sumdu.j2se.julia.tasks;


public interface AbstractTaskList {

    void add(Task task);
    boolean remove(Task task);
    int size();
    AbstractTaskList incoming(int from, int to);



}

package ua.sumdu.j2se.julia.tasks;

public  class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes listTypes)
    {
        AbstractTaskList abstractTaskList=null;

        switch (listTypes)
        {
            case ARRAY: abstractTaskList=new ArrayTaskList();break;
            case LiNKED:abstractTaskList=new LinkedTaskList();break;
            default:new IllegalArgumentException("Нет такого типа");break;
        }
        return abstractTaskList;
    }

}

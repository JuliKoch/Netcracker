package ua.sumdu.j2se.julia.tasks;

import java.util.Objects;

public class Task {
    private String title;
    private int time;
    private boolean active;
    private int start;
    private int end;
    private int interval;
    private boolean repeat;
    public Task(String title, int time)
    {
        if (time<0)
             throw new IllegalArgumentException("Время не может быть отрицательным");
        this.title=title;
        this.time=time;
        active=false;
        repeat=false;
    }

    public Task(String title, int start, int end, int interval )
    {
        if (start<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (end<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (interval<=0)
            throw new IllegalArgumentException("Интервал не может быть равен 0 или быть отрицательынм");
        if (start>end)
            throw new IllegalArgumentException("Начальное время не может быть больше конечного");
        this.title=title;
        this.start=start;
        this.end=end;
        this.interval=interval;
        active=false;
        repeat=true;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive()
    {
        active=true;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {

        return repeat?start:time;

    }

    public void setTime(int time) {

        if (time<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (repeat)
            repeat=false;
        this.time=time;

    }

    public int getStartTime()
    {
        return repeat?start:time;
    }

    public int getEndTime()
    {
        return repeat?end:time;
    }

    public int getRepeatInterval()
    {
        return repeat?interval:0;
    }
    public void setTime(int start, int end, int interval) {
        if (start<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (end<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (interval<=0)
            throw new IllegalArgumentException("Интервал не может быть равен 0 или быть отрицательынм");
        if (start>end)
            throw new IllegalArgumentException("Начальное время не может быть больше конечного");
        repeat=true;
        this.start=start;
        this.end=end;
        this.interval=interval;

    }
    public boolean isRepeated()
    {
        return repeat;
    }

    public int nextTimeAfter(int current)
    {
        if (current<0)
            throw new IllegalArgumentException("Время не может быть отрицательным");
        if (active && repeat) {
            int tmp=start;
            for (;tmp<end;tmp+=interval)
            {
                if (tmp>current)
                    break;
            }
            if (tmp < end)
                return tmp;
            else return -1;
        }
        if (active)
            if (current<=time)
                return time;
            else return -1;
        return -1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return time == task.time &&
                active == task.active &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                repeat == task.repeat &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, active, start, end, interval, repeat);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", active=" + active +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", repeat=" + repeat +
                '}';
    }
}

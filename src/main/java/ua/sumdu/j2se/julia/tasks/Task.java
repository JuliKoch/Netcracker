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
        this.title=title;
        this.time=time;
        active=false;
        repeat=false;
    }

    public Task(String title, int start, int end, int interval )
    {
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {

        return repeat?start:time;

    }

    public void setTime(int time) {

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
        if (active && repeat) {
            int tmp = start + (current / start) * interval;
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

}

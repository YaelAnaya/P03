package yael.ao.uabc.p03;

public class WorkDayTimer {
    private int hours;
    private int minutes;
    private final int WORK_HOURS_PER_DAY = 10;
    private final int WORKDAY_START_HOUR = 8;
    private final int WORKDAY_END_HOUR = 18;

    public WorkDayTimer() {
        hours = 0;
        minutes = 0;
    }

    public boolean increaseHours() {
        if (hours < WORK_HOURS_PER_DAY) {
            hours++;
            return true;
        }
        return false;
    }

    public boolean increaseMinutes() {
        if (minutes < 60) {
            minutes++;
            return true;
        }
        return false;
    }

    public void reset() {
        hours = 0;
        minutes = 0;
    }

    public String getTime() {
        var time = new StringBuilder();
        if (hours < 10) {
            time.append("0");
        }
        time.append(hours);
        if (minutes < 10) {
            time.append(":0");
        } else {
            time.append(":");
        }
        time.append(minutes);
        return time.toString();
    }
}

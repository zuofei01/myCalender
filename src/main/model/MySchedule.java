package main.model;

import java.io.Serializable;

/**
 * Created by zuoyafei on 2018/3/15.
 */
public final class MySchedule implements Comparable<MySchedule>, Serializable {


    private static final long serialVersionUID = -643512206281935672L;

    private long startTime;
    private long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MySchedule{");
        sb.append("startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(MySchedule ms) {
        if (ms.startTime >= this.endTime) {
            return -1;
        } else if (ms.endTime <= this.startTime) {
            return 1;
        } else {
            return 0;
        }
    }

    public MySchedule(MySchedule ms) {
        if (ms == null) {
            throw new RuntimeException("ms is null");
        }
        this.startTime = ms.startTime;
        this.endTime = ms.endTime;
    }

    public MySchedule(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public MySchedule() {

    }

    /**
     * 校验区间值的合法性，结束时间要在开始时间之后,并且开始和结束时间都要大于0
     *
     * @param ms
     * @return
     */
    public static boolean checkSchedule(MySchedule ms) {
        if (ms == null || ms.getStartTime() <= 0 || ms.getEndTime() <= 0 || ms.getStartTime() >= ms.getEndTime()) {
            return false;
        }
        return true;
    }
}

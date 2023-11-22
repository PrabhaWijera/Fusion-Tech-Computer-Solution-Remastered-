package lk.ijse.Fusion.entity;

import java.io.Serializable;

public class Attendence implements Serializable {
    private String AttendID;
    private int AttendDate;
    private String InTime;
    private String OutTime;


    public Attendence() {
    }

    public Attendence(String attendID, int attendDate, String inTime, String outTime) {
        this.AttendID=attendID;
        this.AttendDate=attendDate;
        this.InTime=inTime;
        this.OutTime=outTime;

    }

    public String getAttendID() {
        return AttendID;
    }

    public void setAttendID(String attendID) {
        AttendID = attendID;
    }

    public int getAttendDate() {
        return AttendDate;
    }

    public void setAttendDate(int attendDate) {
        AttendDate = attendDate;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    @Override
    public String toString() {
        return "AttendenceDTO{" +
                "AttendID='" + AttendID + '\'' +
                ", AttendDate=" + AttendDate +
                ", InTime=" + InTime +
                ", OutTime=" + OutTime +

                '}';
    }


}

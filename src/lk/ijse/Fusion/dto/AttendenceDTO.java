package  lk.ijse.Fusion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class AttendenceDTO {


    private String AttendID;
    private int AttendDate;
    private String InTime;
    private String OutTime;



    public AttendenceDTO() {
    }

    public AttendenceDTO(String attendID, int attendDate, String inTime, String outTime) {
        this.AttendID=attendID;
        this.AttendDate=attendDate;
        this.InTime=inTime;
        this.OutTime=outTime;
        System.out.println("Im TO");
    }

    public void setAttendID(String attendID) {
        AttendID = attendID;
    }

    public void setAttendDate(int attendDate) {
        AttendDate = attendDate;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
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

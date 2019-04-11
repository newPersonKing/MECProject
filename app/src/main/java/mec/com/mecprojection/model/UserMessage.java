package mec.com.mecprojection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserMessage  {

    @Id(autoincrement = true)
    private Long id =null;

    private String userName;

    private double top_number;
    private double top_body;
    private double top_chest;
    private double top_waist;
    private double top_sleeve;
    private double bottom_number;
    private double bottom_out_seam;
    private double bottom_waist;
    private double bottom_hip;
    private double bottom_sleeve;

    @Generated(hash = 1495938794)
    public UserMessage(Long id, String userName, double top_number, double top_body,
            double top_chest, double top_waist, double top_sleeve,
            double bottom_number, double bottom_out_seam, double bottom_waist,
            double bottom_hip, double bottom_sleeve) {
        this.id = id;
        this.userName = userName;
        this.top_number = top_number;
        this.top_body = top_body;
        this.top_chest = top_chest;
        this.top_waist = top_waist;
        this.top_sleeve = top_sleeve;
        this.bottom_number = bottom_number;
        this.bottom_out_seam = bottom_out_seam;
        this.bottom_waist = bottom_waist;
        this.bottom_hip = bottom_hip;
        this.bottom_sleeve = bottom_sleeve;
    }

    @Generated(hash = 113828295)
    public UserMessage() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTop_number() {
        return top_number;
    }

    public void setTop_number(double top_number) {
        this.top_number = top_number;
    }

    public double getTop_body() {
        return top_body;
    }

    public void setTop_body(double top_body) {
        this.top_body = top_body;
    }

    public double getTop_chest() {
        return top_chest;
    }

    public void setTop_chest(double top_chest) {
        this.top_chest = top_chest;
    }

    public double getTop_waist() {
        return top_waist;
    }

    public void setTop_waist(double top_waist) {
        this.top_waist = top_waist;
    }

    public double getTop_sleeve() {
        return top_sleeve;
    }

    public void setTop_sleeve(double top_sleeve) {
        this.top_sleeve = top_sleeve;
    }

    public double getBottom_number() {
        return bottom_number;
    }

    public void setBottom_number(double bottom_number) {
        this.bottom_number = bottom_number;
    }

    public double getBottom_out_seam() {
        return bottom_out_seam;
    }

    public void setBottom_out_seam(double bottom_out_seam) {
        this.bottom_out_seam = bottom_out_seam;
    }

    public double getBottom_waist() {
        return bottom_waist;
    }

    public void setBottom_waist(double bottom_waist) {
        this.bottom_waist = bottom_waist;
    }

    public double getBottom_hip() {
        return bottom_hip;
    }

    public void setBottom_hip(double bottom_hip) {
        this.bottom_hip = bottom_hip;
    }

    public double getBottom_sleeve() {
        return bottom_sleeve;
    }

    public void setBottom_sleeve(double bottom_sleeve) {
        this.bottom_sleeve = bottom_sleeve;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

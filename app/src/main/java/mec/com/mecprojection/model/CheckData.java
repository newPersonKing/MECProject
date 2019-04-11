package mec.com.mecprojection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CheckData {

    @Id(autoincrement = true)
    private Long id =null;

    private int  angeGrop;
    private double maleWeak;
    private double maleStrong;
    private double femaleWeak;
    private double femaleStrong;
    private double type;

    @Generated(hash = 920755559)
    public CheckData(Long id, int angeGrop, double maleWeak, double maleStrong,
            double femaleWeak, double femaleStrong, double type) {
        this.id = id;
        this.angeGrop = angeGrop;
        this.maleWeak = maleWeak;
        this.maleStrong = maleStrong;
        this.femaleWeak = femaleWeak;
        this.femaleStrong = femaleStrong;
        this.type = type;
    }

    @Generated(hash = 491376345)
    public CheckData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAngeGrop() {
        return angeGrop;
    }

    public void setAngeGrop(int angeGrop) {
        this.angeGrop = angeGrop;
    }

    public double getMaleWeak() {
        return maleWeak;
    }

    public void setMaleWeak(double maleWeak) {
        this.maleWeak = maleWeak;
    }

    public double getMaleStrong() {
        return maleStrong;
    }

    public void setMaleStrong(double maleStrong) {
        this.maleStrong = maleStrong;
    }

    public double getFemaleWeak() {
        return femaleWeak;
    }

    public void setFemaleWeak(double femaleWeak) {
        this.femaleWeak = femaleWeak;
    }

    public double getFemaleStrong() {
        return femaleStrong;
    }

    public void setFemaleStrong(double femaleStrong) {
        this.femaleStrong = femaleStrong;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }
}

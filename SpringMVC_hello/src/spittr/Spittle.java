package spittr;

import java.time.LocalDate;

public class Spittle {
    private  long id;
    private  String message;
    private  LocalDate time;
    private Double latitude;
    private Double longitude;
    public Spittle(String message,LocalDate time){
        this.message=message;
        this.time=time;
        this.latitude=null;
        this.longitude=null;
    }
    public Spittle(String message,LocalDate time,Double longitude,Double latitude){
        this(message,time);
        this.longitude=longitude;
        this.latitude=latitude;
    }
    public long getId(){
        return id;
    }
    public String getMessage(){
        return message;
    }
    public LocalDate getTime(){
        return time;
    }
    public Double getLatitude(){
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}

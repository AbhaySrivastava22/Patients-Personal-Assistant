package com.example.mytherapy;

public class reminderdata {
    private String title;
    private String description;
    private String end_date;
    private String start_date;
    private String morning_pill;
    private String afternoon_pill;
    private String night_pill;
public reminderdata()
{

}

    public reminderdata(String title, String description, String end_date, String start_date, String morning_pill, String afternoon_pill, String night_pill) {
        this.title = title;
        this.description = description;
        this.end_date = end_date;
        this.start_date = start_date;
        this.morning_pill = morning_pill;
        this.afternoon_pill = afternoon_pill;
        this.night_pill = night_pill;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getMorning_pill() {
        return morning_pill;
    }

    public String getAfternoon_pill() {
        return afternoon_pill;
    }

    public void setAfternoon_pill(String afternoon_pill) {
        this.afternoon_pill = afternoon_pill;
    }

    public String getNight_pill() {
        return night_pill;
    }

    public void setNight_pill(String night_pill) {
        this.night_pill = night_pill;
    }
}

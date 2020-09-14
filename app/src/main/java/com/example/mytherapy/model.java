package com.example.mytherapy;

public class model {
    private String Clinic_Name;
    private String Experience;
    private String email;
    private String specialization;
    private String username;
    public model()
    {

    }

    public model(String clinic_name, String experience, String email, String specialization, String username) {
        Clinic_Name = clinic_name;
        Experience = experience;
        this.email = email;
        this.specialization = specialization;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getClinic_Name() {
        return Clinic_Name;
    }

    public void setClinic_Name(String clinic_Name) {
        Clinic_Name = clinic_Name;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

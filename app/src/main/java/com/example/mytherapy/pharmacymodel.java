package com.example.mytherapy;

public class pharmacymodel {
    private String address;
    private String username;
    private String email;
    private String pharmacy_name;
    public pharmacymodel()
    {

    }

    public pharmacymodel(String address, String username, String email, String pharmacy_name) {
        this.address = address;
        this.username = username;
        this.email = email;
        this.pharmacy_name = pharmacy_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }
}

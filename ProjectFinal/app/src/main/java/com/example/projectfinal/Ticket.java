package com.example.projectfinal;

import java.sql.Date;

public class Ticket {
    private String id;
    private String name;
    private String phone;
    private String cityDeparture;
    private String cityArrive;
    private String timeDeparture;
    private String timeArrive;
    private String typeTicket;
    private int adultQuantity;
    private int childQuantity;


    public Ticket() {
    }

    public Ticket(String id, String name, String phone, String cityDeparture, String cityArrive, String timeDeparture, String timeArrive, String typeTicket, int adultQuantity, int childQuantity) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cityDeparture = cityDeparture;
        this.cityArrive = cityArrive;
        this.timeDeparture = timeDeparture;
        this.timeArrive = timeArrive;
        this.typeTicket = typeTicket;
        this.adultQuantity = adultQuantity;
        this.childQuantity = childQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    public String getCityArrive() {
        return cityArrive;
    }

    public void setCityArrive(String cityArrive) {
        this.cityArrive = cityArrive;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(String timeArrive) {
        this.timeArrive = timeArrive;
    }

    public String getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(String typeTicket) {
        this.typeTicket = typeTicket;
    }

    public int getAdultQuantity() {
        return adultQuantity;
    }

    public void setAdultQuantity(int adultQuantity) {
        this.adultQuantity = adultQuantity;
    }

    public int getChildQuantity() {
        return childQuantity;
    }

    public void setChildQuantity(int childQuantity) {
        this.childQuantity = childQuantity;
    }
}

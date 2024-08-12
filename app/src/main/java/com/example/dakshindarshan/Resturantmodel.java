package com.example.dakshindarshan;

import com.google.firebase.firestore.GeoPoint;

public class Resturantmodel {
    GeoPoint address,location;
String uri;
    String docId,date,time,hotelname,closetiming,openingtime,vegnonveg,service,language,contact,ownername,description,state,userid;


    public Resturantmodel() {
    }

    public Resturantmodel(GeoPoint address, GeoPoint location, String uri, String docId, String date, String time, String hotelname, String closetiming, String openingtime, String vegnonveg, String service, String language, String contact, String ownername, String description, String state, String userid) {
        this.address = address;
        this.location = location;
        this.uri = uri;
        this.docId = docId;
        this.date = date;
        this.time = time;
        this.hotelname = hotelname;
        this.closetiming = closetiming;
        this.openingtime = openingtime;
        this.vegnonveg = vegnonveg;
        this.service = service;
        this.language = language;
        this.contact = contact;
        this.ownername = ownername;
        this.description = description;
        this.state = state;
        this.userid = userid;
    }


    public GeoPoint getAddress() {
        return address;
    }

    public void setAddress(GeoPoint address) {
        this.address = address;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getClosetiming() {
        return closetiming;
    }

    public void setClosetiming(String closetiming) {
        this.closetiming = closetiming;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getVegnonveg() {
        return vegnonveg;
    }

    public void setVegnonveg(String vegnonveg) {
        this.vegnonveg = vegnonveg;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

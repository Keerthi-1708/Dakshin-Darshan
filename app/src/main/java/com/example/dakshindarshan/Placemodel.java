package com.example.dakshindarshan;

import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;

public class Placemodel implements Serializable {

   String docId,name,state,uri,aviblity,date,description,district,guidename,language,localarea,placename,price,time,
           service,userid;

GeoPoint address,location;

    public Placemodel() {
    }


    public Placemodel(String docId, String name, String state, String uri, String aviblity, String date, String description, String district, String guidename, String language, String localarea, String placename, String price, String time, String service, String userid, GeoPoint address, GeoPoint location) {
        this.docId = docId;
        this.name = name;
        this.state = state;
        this.uri = uri;
        this.aviblity = aviblity;
        this.date = date;
        this.description = description;
        this.district = district;
        this.guidename = guidename;
        this.language = language;
        this.localarea = localarea;
        this.placename = placename;
        this.price = price;
        this.time = time;
        this.service = service;
        this.userid = userid;
        this.address = address;
        this.location = location;
    }


    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAviblity() {
        return aviblity;
    }

    public void setAviblity(String aviblity) {
        this.aviblity = aviblity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGuidename() {
        return guidename;
    }

    public void setGuidename(String guidename) {
        this.guidename = guidename;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocalarea() {
        return localarea;
    }

    public void setLocalarea(String localarea) {
        this.localarea = localarea;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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
}

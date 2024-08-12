package com.example.dakshindarshan;

import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;

public class Shopmodel implements Serializable {
    String uri,docId,date,time,shopname,closing,opening,mens,service,language,contact,owner,about,state,userid;
    GeoPoint address,location;

    public Shopmodel() {
    }

    public Shopmodel(String uri, String docId, String date, String time, String shopname, String closing, String opening, String mens, String service, String language, String contact, String owner, String about, String state, String userid, GeoPoint address, GeoPoint location) {
        this.uri = uri;
        this.docId = docId;
        this.date = date;
        this.time = time;
        this.shopname = shopname;
        this.closing = closing;
        this.opening = opening;
        this.mens = mens;
        this.service = service;
        this.language = language;
        this.contact = contact;
        this.owner = owner;
        this.about = about;
        this.state = state;
        this.userid = userid;
        this.address = address;
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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getMens() {
        return mens;
    }

    public void setMens(String mens) {
        this.mens = mens;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

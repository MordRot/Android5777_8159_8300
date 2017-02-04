package com.android.mor_arye.android5777_8159_8300.Model.Entities;

import android.location.Address;

/**
 * Created by mor on 20 נובמבר 2016.
 */

public class Business {
    private static int currentID = 1;
    private int idBusiness;
    private String nameBusiness;
    private Address addressBusiness;
    private String phoneNumber;
    private String emailAddress;
    private String websiteLink;

    public Business(String nameBusiness, Address addressBusiness,
                    String phoneNumber,
                    String emailAddress,
                    String websiteLink) {

        this.idBusiness = currentID++;
        this.nameBusiness = nameBusiness;
        this.addressBusiness = addressBusiness;     // TODO to make it string?
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.websiteLink = websiteLink;
    }

    //<editor-fold desc="geters and seters">
    //TODO get/set idBusiness? integer overflow
    public int getIdBusiness() {
        return idBusiness;
    }

    public void setIdBusiness(int idBusiness) {
        this.idBusiness = idBusiness;
    }

    public String getNameBusiness() {
        return nameBusiness;
    }

    public void setNameBusiness(String nameBusiness) {
        this.nameBusiness = nameBusiness;
    }

    public Address getAddressBusiness() {
        return addressBusiness;
    }

    public void setAddressBusiness(Address addressBusiness) {
        this.addressBusiness = addressBusiness;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }
    //</editor-fold>
}

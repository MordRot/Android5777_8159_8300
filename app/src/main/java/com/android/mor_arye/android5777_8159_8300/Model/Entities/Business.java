package com.android.mor_arye.android5777_8159_8300.Model.Entities;

import android.location.Address;
import android.provider.ContactsContract;

import java.util.Locale;

/**
 * Created by mor on 20 נובמבר 2016.
 */

public class Business {
    private static int currentID = 1;
    private int idBusiness;
    private String nameBusiness;
    private Address addressBusiness;
    private ContactsContract.CommonDataKinds.Phone phoneNumber;
    private ContactsContract.CommonDataKinds.Email emailAddress;
    private ContactsContract.CommonDataKinds.Website websiteLink;

    public Business(String nameBusiness, Address addressBusiness,
                    ContactsContract.CommonDataKinds.Phone phoneNumber,
                    ContactsContract.CommonDataKinds.Email emailAddress,
                    ContactsContract.CommonDataKinds.Website websiteLink) {

        this.idBusiness = currentID++;
        this.nameBusiness = nameBusiness;
        this.addressBusiness = addressBusiness;
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

    public ContactsContract.CommonDataKinds.Phone getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(ContactsContract.CommonDataKinds.Phone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContactsContract.CommonDataKinds.Email getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(ContactsContract.CommonDataKinds.Email emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ContactsContract.CommonDataKinds.Website getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(ContactsContract.CommonDataKinds.Website websiteLink) {
        this.websiteLink = websiteLink;
    }
    //</editor-fold>
}

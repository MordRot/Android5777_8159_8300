package com.android.mor_arye.android5777_8159_8300.Model.Entities;

import java.util.GregorianCalendar;

/**
 * Created by Leon on 23-Nov-16.
 */

public class Recreation {
    TypeOfRecreation typeOfRecreation;
    String NameOfCountry;
    GregorianCalendar dateOfBeginning;
    GregorianCalendar dateOfEnding;
    double price;
    String description;
    int idBusiness;


    public Recreation(TypeOfRecreation typeOfRecreation,
                      String nameOfCountry,
                      GregorianCalendar dateOfBeginning,
                      GregorianCalendar dateOfEnding,
                      double price,
                      String description,
                      int idBuisness) {

        this.typeOfRecreation = typeOfRecreation;
        NameOfCountry = nameOfCountry;
        this.dateOfBeginning = dateOfBeginning;
        this.dateOfEnding = dateOfEnding;
        this.price = price;
        this.description = description;
        this.idBusiness = idBuisness;
    }


    //<editor-fold desc="geters and seters">
    public TypeOfRecreation getTypeOfRecreation() {
        return typeOfRecreation;
    }

    public void setTypeOfRecreation(TypeOfRecreation typeOfRecreation) {
        this.typeOfRecreation = typeOfRecreation;
    }

    public String getNameOfCountry() {
        return NameOfCountry;
    }

    public void setNameOfCountry(String nameOfCountry) {
        NameOfCountry = nameOfCountry;
    }

    public GregorianCalendar getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(GregorianCalendar dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public GregorianCalendar getDateOfEnding() {
        return dateOfEnding;
    }

    public void setDateOfEnding(GregorianCalendar dateOfEnding) {
        this.dateOfEnding = dateOfEnding;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdBusiness() {
        return idBusiness;
    }

    public void setIdBusiness(int idBusiness) {
        this.idBusiness = idBusiness;
    }
    //</editor-fold>
}

package com.zeest.emedz.models;

public class PharmaciesModel {
    private int pharmacyId;
    private String pharmacyName;
    private String pharmacyBrandImage;

    public PharmaciesModel() {
    }

    public PharmaciesModel(int pharmacyId, String pharmacyName, String pharmacyBrandImage) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyBrandImage = pharmacyBrandImage;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyBrandImage() {
        return pharmacyBrandImage;
    }

    public void setPharmacyBrandImage(String pharmacyBrandImage) {
        this.pharmacyBrandImage = pharmacyBrandImage;
    }
}

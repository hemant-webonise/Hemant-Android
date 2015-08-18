package com.example.webonise.listviewandsqlite;

/**
 * Created by webonise on 13/8/15.
 */
public class Details {
    int detailsId;
    String detailsName, detailsWeight, detailsAge, detailsHeight;

    public Details(String detailsName, String detailsWeight, String detailsAge, String detailsHeight) {
        this.detailsName = detailsName;
        this.detailsWeight = detailsWeight;
        this.detailsAge = detailsAge;
        this.detailsHeight = detailsHeight;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsName() {
        return detailsName;
    }

    public void setDetailsName(String detailsName) {
        this.detailsName = detailsName;
    }

    public String getDetailsWeight() {
        return detailsWeight;
    }

    public void setDetailsWeight(String detailsWeight) {
        this.detailsWeight = detailsWeight;
    }

    public String getDetailsAge() {
        return detailsAge;
    }

    public void setDetailsAge(String detailsAge) {
        this.detailsAge = detailsAge;
    }

    public String getDetailsHeight() {
        return detailsHeight;
    }

    public void setDetailsHeight(String detailsHeight) {
        this.detailsHeight = detailsHeight;
    }
}

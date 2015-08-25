package com.example.webonise.FragmentAssignment;

/**
 * Created by webonise on 14/8/15.
 */
public class Details {

    public static final int ID = 0;
    private int id;
    private String name;
    private String age;
    private String weight;
    private String height;

    public Details(int id, String name, String age, String weight, String height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }


    public Details() {
        this.id = ID;
        this.name = Constants.EMPTY;
        this.age = Constants.ZERO;
        this.weight = Constants.WEIGHT;
        this.height =Constants.HEIGHT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}

package ru.test.sibers.pokeapp.model;

import com.google.gson.annotations.SerializedName;

public class Evolution {
    @SerializedName("num")
    private String num;
    @SerializedName("name")
    private String name;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

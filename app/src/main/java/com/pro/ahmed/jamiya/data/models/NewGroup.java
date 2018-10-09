package com.pro.ahmed.jamiya.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewGroup implements Serializable{
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("MembersCount")
    @Expose
    private Integer membersCount;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    @SerializedName("arrangement")
    @Expose
    private Integer arrangement;
    @SerializedName("Installment")
    @Expose
    private Integer installment;

    public NewGroup(Integer id, Integer userId, String name,
                    String description, Integer membersCount,
                    Integer duration, Integer arrangement, Integer installment) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.membersCount = membersCount;
        this.duration = duration;
        this.arrangement = arrangement;
        this.installment = installment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getArrangement() {
        return arrangement;
    }

    public void setArrangement(Integer arrangement) {
        this.arrangement = arrangement;
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

}


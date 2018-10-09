package com.pro.ahmed.jamiya.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddArrangementMembers {

    @SerializedName("GroupUserId")
    @Expose
    private Integer groupUserId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("Arrangemen")
    @Expose
    private Integer arrangemen;

    public Integer getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Integer groupUserId) {
        this.groupUserId = groupUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArrangemen() {
        return arrangemen;
    }

    public void setArrangemen(Integer arrangemen) {
        this.arrangemen = arrangemen;
    }

}

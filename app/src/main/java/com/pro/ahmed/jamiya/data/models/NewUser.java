package com.pro.ahmed.jamiya.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewUser {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Sex")
    @Expose
    private Integer sex;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Photo")
    @Expose
    private String photo;

    public NewUser(Integer id, String firstName, String lastName,
                   Integer sex, String birthDate, Integer countryId,
                   String phone, String email, String userName,
                   String password, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.countryId = countryId;
        this.phone = phone;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

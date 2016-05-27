package com.example.tatiana.firsttest.model;

import android.os.Parcel;
import android.os.Parcelable;
//import android.util.Log;

/**
 * Created by Tatiana on 20.05.2016.
 */
public class User implements Parcelable {

    private static final long serialVersionUID = 1L;

    private Integer userid;
    private Integer isadmin;
    private String login;
    private String password;
    private String firstname;
    private String secondname;
    private String email;
    private double balance;
    private Integer sex;
    public int Authorisation;

    public User(
            Integer isadmin,
            String login,
            String password,
            String firstname,
            String secondname,
            String email,
            Double balance,
            Integer sex
    ) {
        this.isadmin = isadmin;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        this.email = email;
        this.balance = balance;
        this.sex = sex;
    }

    public User() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) { // упаковываем объект в Parcel
        //Log.d(LOG_TAG, "writeToParcel");
        parcel.writeInt(userid);
        parcel.writeInt(isadmin);
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeString(firstname);
        parcel.writeString(secondname);
        parcel.writeString(email);
        parcel.writeDouble(balance);
        parcel.writeInt(Authorisation);
        parcel.writeInt(sex);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        // распаковываем объект из Parcel
        public User createFromParcel(Parcel in) {
            //Log.d(LOG_TAG, "createFromParcel");
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private User(Parcel parcel) {
        //Log.d(LOG_TAG, "MyObject(Parcel parcel)");

        userid = parcel.readInt();
        isadmin = parcel.readInt();
        login = parcel.readString();
        password = parcel.readString();
        firstname = parcel.readString();
        secondname = parcel.readString();
        email = parcel.readString();
        balance = parcel.readDouble();
        Authorisation = parcel.readInt();
        sex = parcel.readInt();

    }

}


package com.sachin.wealthzen.Domain;
import android.os.Parcel;
import android.os.Parcelable;

public class DoctorsModel implements Parcelable {
    private String Address;
    private String Biography;
    private int Id;
    private String Name;
    private String Picture;
    private String Special;
    private int Experience;
    private String Location;
    private String Mobile;
    private String patieng;
    private double Rating;
    private String Site;

    public DoctorsModel() {
        this.Address = "";
        this.Biography = "";
        this.Id = 0;
        this.Name = "";
        this.Picture = "";
        this.Special = "";
        this.Experience = 0;
        this.Location = "";
        this.Mobile = "";
        this.patieng = "";
        this.Rating = 0.0;
        this.Site = "";
    }

    protected DoctorsModel(Parcel in) {
        Address = in.readString();
        Biography = in.readString();
        Id = in.readInt();
        Name = in.readString();
        Picture = in.readString();
        Special = in.readString();
        Experience = in.readInt();
        Location = in.readString();
        Mobile = in.readString();
        patieng = in.readString();
        Rating = in.readDouble();
        Site = in.readString();
    }

    public static final Parcelable.Creator<DoctorsModel> CREATOR = new Parcelable.Creator<DoctorsModel>() {
        @Override
        public DoctorsModel createFromParcel(Parcel in) {
            return new DoctorsModel(in);
        }

        @Override
        public DoctorsModel[] newArray(int size) {
            return new DoctorsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(Address);
        parcel.writeString(Biography);
        parcel.writeInt(Id);
        parcel.writeString(Name);
        parcel.writeString(Picture);
        parcel.writeString(Special);
        parcel.writeInt(Experience);
        parcel.writeString(Location);
        parcel.writeString(Mobile);
        parcel.writeString(patieng);
        parcel.writeDouble(Rating);
        parcel.writeString(Site);
    }

    public String getAddress() {
        return Address;
    }

    public String getBiography() {
        return Biography;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPicture() {
        return Picture;
    }

    public String getSpecial() {
        return Special;
    }

    public int getExperience() {
        return Experience;
    }

    public String getLocation() {
        return Location;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getPatieng() {
        return patieng;
    }

    public double getRating() {
        return Rating;
    }

    public String getSite() {
        return Site;
    }
}


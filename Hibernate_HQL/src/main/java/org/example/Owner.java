package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {
    @Id
    private int ownerId;
    private String ownerName;
    private String contactNumber;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Car> cars;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> owner) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", owner=" + cars +
                '}';
    }
}

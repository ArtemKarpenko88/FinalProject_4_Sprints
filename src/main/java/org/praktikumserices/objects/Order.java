package org.praktikumserices.objects;

public class Order {
    Client client;
    String dateStart;
    String rentalPeriod;
    String scooterColor;
    String comment;

    public Order(Client client, String dateStart, String rentalPeriod, String scooterColor, String comment) {
        this.client = client;
        this.dateStart = dateStart;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    public Client getClient() {
        return client;
    }

    public String getDateStart() {
        return dateStart;
    }


    public String getRentalPeriod() {
        return rentalPeriod;
    }


    public String getScooterColor() {
        return scooterColor;
    }


    public String getComment() {
        return comment;
    }


}

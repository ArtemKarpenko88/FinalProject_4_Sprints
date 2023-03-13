package org.praktikumserices.objects;

public class Client {

    String name;
    String surname;
    String address;
    String phone;
    String comment;
    String metroStation;

    public Client(String name, String surname, String address, String phone, String metroStation) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.metroStation = metroStation;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMetroStation() {
        return metroStation;
    }


}

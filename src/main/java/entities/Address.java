package entities;

import java.util.Objects;

public class Address {
    private Long id;
    private String country;
    private String distinct;
    private String city;
    private String street;
    private int house;
    private int unit; //корпус дома
    private int code; // индекс

    public Address() {
    }

    public Address(String country, String distinct, String city, String street, int house, int unit, int code) {
        this.country = country;
        this.distinct = distinct;
        this.city = city;
        this.street = street;
        this.house = house;
        this.unit = unit;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getHouse() == address.getHouse() &&
                getUnit() == address.getUnit() &&
                getCode() == address.getCode() &&
                Objects.equals(getId(), address.getId()) &&
                Objects.equals(getCountry(), address.getCountry()) &&
                Objects.equals(getDistinct(), address.getDistinct()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry(), getDistinct(), getCity(), getStreet(), getHouse(), getUnit(), getCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", distinct='" + distinct + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", unit=" + unit +
                ", code=" + code +
                '}';
    }
}

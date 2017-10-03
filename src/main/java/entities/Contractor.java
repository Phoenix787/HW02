package entities;

import java.util.HashSet;
import java.util.Set;
//todo аннотировать классы
public class Contractor {
    private String name;
    private Address mainAddress;
    private Address actualAddress;
    private Details details;
    private String email;
    private Set<Phone> phones = new HashSet<>();


    public Contractor(String name, Address mainAddress, Address actualAddress, Details details, String email, Set<Phone> phones) {
        this.name = name;
        this.mainAddress = mainAddress;
        this.actualAddress = actualAddress;
        this.details = details;
        this.email = email;
        this.phones = phones;
    }

    public Contractor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Address getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(Address actualAddress) {
        this.actualAddress = actualAddress;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone){
        phone.setContractor(this);
        getPhones().add(phone);
    }
}

package entities;

import java.util.Objects;

public class Phone {
    private Long id;
    private String phone;
    private Contractor Contractor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contractor getContractor() {
        return Contractor;
    }

    public void setContractor(Contractor idContractor) {
        this.Contractor = idContractor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phones = (Phone) o;
        return Objects.equals(getId(), phones.getId()) &&
                Objects.equals(getPhone(), phones.getPhone()) &&
                Objects.equals(getContractor(), phones.getContractor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhone(), getContractor());
    }

    @Override
    public String toString() {
        return "entities.Phone{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", idContractor=" + Contractor +
                '}';
    }
}


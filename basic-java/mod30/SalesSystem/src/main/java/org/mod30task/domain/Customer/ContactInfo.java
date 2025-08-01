package org.mod30task.domain.Customer;

import java.util.Objects;

public class ContactInfo {
    private Long id;
    private Long phone;
    private String email;

    public ContactInfo() {
    }

    public ContactInfo(Long id, Long phone, String email) {
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id=" + id +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
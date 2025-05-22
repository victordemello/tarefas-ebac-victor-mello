package org.mod30task.domain.Customer;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    private String name;
    private Long ssn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", referencedColumnName = "id")
    private ContactInfo contactInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public CustomerDomain() {
    }

    public CustomerDomain(Long id, String name, Long ssn, ContactInfo contactInfo, Address address) {
        this.id = id;
        this.name = name;
        this.ssn = ssn;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public CustomerDomain(Long ssn, ContactInfo contactInfo, Address address) {
        this.name = name;
        this.ssn = ssn;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDomain that = (CustomerDomain) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ssn, that.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ssn);
    }

    @Override
    public String toString() {
        return "CustomerDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn=" + ssn +
                ", contactInfo=" + contactInfo +
                ", address=" + address +
                '}';
    }
}
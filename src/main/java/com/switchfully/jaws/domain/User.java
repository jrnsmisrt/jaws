package com.switchfully.jaws.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToMany
    @JoinColumn(name = "fk_user", nullable = false)
    private List<Address> addressList;

    @Embedded
    private ContactInformation contactInformation;

    protected User() {}

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactInformation = builder.contactInformation;
        this.addressList = builder.addressList;
        this.licensePlate = builder.licensePlate;
        this.registrationDate = LocalDate.now();

    }

    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private String licensePlate;

        private LocalDate registrationDate;
        private List<Address> addressList;
        private ContactInformation contactInformation;

        public UserBuilder() {}

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public UserBuilder withRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserBuilder withAddressList(List<Address> addressList) {
            this.addressList = addressList;
            return this;
        }

        public UserBuilder withContactInformation(ContactInformation contactInformation) {
            this.contactInformation = contactInformation;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public User withAddressList(List<Address> addressList) {
        this.addressList = addressList;
        return this;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public User withContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
        return this;
    }
}

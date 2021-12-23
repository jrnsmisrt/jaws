package com.switchfully.jaws.domain.user;

import com.switchfully.jaws.domain.common.Address;
import com.switchfully.jaws.domain.common.ContactInformation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_address_id", nullable = false)
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    protected User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(contactInformation, user.contactInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactInformation);
    }

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactInformation = builder.contactInformation;
        this.address = builder.address;
        this.licensePlate = builder.licensePlate;
        this.registrationDate = LocalDate.now();

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private String licensePlate;
        private Address address;
        private ContactInformation contactInformation;

        public UserBuilder() {
        }

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

        public UserBuilder withAddress(Address address) {
            this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }


}

package com.switchfully.jaws.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotBlank
//    @NotNull
    private Long id;

    @Column(name = "first_name", nullable = false)
//    @NotBlank
//    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
//    @NotBlank
//    @NotNull
    private String lastName;

    @Column(name = "license_plate", nullable = false)
//    @NotBlank
//    @NotNull
    private String licensePlate;

    @Column(name = "registration_date", nullable = false)
//    @NotBlank
//    @NotNull
    private LocalDate registrationDate;

    @OneToOne( cascade = CascadeType.PERSIST, targetEntity = Address.class)
//    @NotBlank
//    @NotNull
    @JoinColumn(name = "fk_address_id", nullable = false)
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    protected User() {
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}

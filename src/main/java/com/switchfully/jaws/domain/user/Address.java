package com.switchfully.jaws.domain.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zip_code", nullable = false)
    private int zipCode;

    protected Address() {
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getZipCode() {
        return zipCode;
    }

    private Address(AddressBuilder builder) {
        this.city = builder.city;
        this.street = builder.street;
        this.zipCode = builder.zipCode;
        this.country = builder.country;
        this.streetNumber = builder.streetNumber;
    }

    public static class AddressBuilder {
        private String street;
        private String streetNumber;
        private String country;
        private String city;
        private int zipCode;

        public AddressBuilder() {
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder withZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, city, country);
    }
}

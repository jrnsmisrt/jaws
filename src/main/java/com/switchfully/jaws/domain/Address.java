package com.switchfully.jaws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    @NotBlank
    @NotNull
    private Long id;

    @Column(name = "street", nullable = false)
    @NotBlank
    @NotNull
    private String street;

    @Column(name = "street_number", nullable = false)
    @NotBlank
    @NotNull
    private String streetNumber;

    @Column(name = "city", nullable = false)
    @NotBlank
    @NotNull
    private String city;

    @Column(name = "country", nullable = false)
    @NotBlank
    @NotNull
    private String country;

    @Column(name = "zip_code", nullable = false)
    @NotBlank
    @NotNull
    private int zipCode;

    protected Address() {
    }

    private Address(AddressBuilder builder) {

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

package com.switchfully.jaws.domain.parkingLot;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.domain.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactPersonTest {

    Address validAddress;
    Name validName;

    @BeforeEach
    void setUp() {
        validAddress = new Address.AddressBuilder()
                .withStreet("Street")
                .withStreetNumber("Street number")
                .withCity("City")
                .withCountry("Country")
                .withZipCode(6666)
                .build();

        validName = new Name("Lastname", "Firstname");
    }

    @Test
    void createContactPerson_givenNullCellphoneNumberAndNullHomePhoneNumber_thenThrowsIllegalArgumentException() {
        ContactInformation invalidContactInformation = new ContactInformation.ContactInfoBuilder()
                .withEmailAddress("test@test.com")
                .build();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ContactPerson(validName, invalidContactInformation, validAddress));
    }

}
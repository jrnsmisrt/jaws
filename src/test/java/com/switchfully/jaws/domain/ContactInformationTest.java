package com.switchfully.jaws.domain;

import com.switchfully.jaws.Exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.domain.user.ContactInformation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


class ContactInformationTest {
    @Test
    void WhenProvidedWithWrongEmailThrowException() {
        try {
            ContactInformation contactInformation = new ContactInformation.ContactInfoBuilder()
                    .withCellPhoneNumber("500")
                    .withHomePhoneNumber("sf")
                    .withEmailAddress("wrongEmail")
                    .build();
            fail();
            
        } catch (EmailAddressIsInvalidException emailAddressIsInvalidException) {
            assertThat(emailAddressIsInvalidException.getMessage()).isEqualTo("The email address that you entered is not valid : wrongEmail");
        }
    }
}
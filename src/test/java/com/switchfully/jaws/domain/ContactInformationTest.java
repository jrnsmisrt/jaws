package com.switchfully.jaws.domain;

import com.switchfully.jaws.domain.common.ContactInformation;
import com.switchfully.jaws.exceptions.EmailAddressIsInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ContactInformationTest {
    @Test
    void WhenProvidedWithWrongEmailThrowException() {
        try {
           new ContactInformation.ContactInfoBuilder()
                    .withCellPhoneNumber("500")
                    .withHomePhoneNumber("sf")
                    .withEmailAddress("wrongEmail")
                    .build();
            Assertions.fail();
            
        } catch (EmailAddressIsInvalidException emailAddressIsInvalidException) {
            assertThat(emailAddressIsInvalidException.getMessage()).isEqualTo("The email address that you entered is not valid : wrongEmail");
        }
    }
}
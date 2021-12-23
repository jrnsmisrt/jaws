package com.switchfully.jaws.domain.common;

import com.switchfully.jaws.Exceptions.EmailAddressIsInvalidException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class ContactInformation {
    @Column(name = "cellphone_number")
    private String cellphoneNumber;

    @Column(name = "home_number")
    private String homePhoneNumber;

    @Column(name = "email")
    private String emailAddress;

    protected ContactInformation() {}

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    private ContactInformation(ContactInfoBuilder builder) {
        this.cellphoneNumber = builder.cellPhoneNumber;
        this.homePhoneNumber = builder.homePhoneNumber;
        this.emailAddress = builder.emailAddress;
    }

    public static class ContactInfoBuilder {
        private String cellPhoneNumber;
        private String homePhoneNumber;
        private String emailAddress;

        public ContactInfoBuilder() {
        }

        public ContactInfoBuilder withCellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
            return this;
        }

        public ContactInfoBuilder withHomePhoneNumber(String homePhoneNumber) {
            this.homePhoneNumber = homePhoneNumber;
            return this;
        }

        public ContactInfoBuilder withEmailAddress(String emailAddress) {
            setEmail(emailAddress);
            return this;
        }

        public ContactInformation build() {
            return new ContactInformation(this);
        }

        private void setEmail(String email) {
            if (!isValidEmailAddress(email)) {
                throw new EmailAddressIsInvalidException(email);
            }
            this.emailAddress = email;
        }

        private boolean isValidEmailAddress(String email) {
            boolean result = true;
            try {
                InternetAddress emailAddress = new InternetAddress(email);
                emailAddress.validate();
            } catch (AddressException ex) {
                result = false;
            }
            return result;
        }
    }

}

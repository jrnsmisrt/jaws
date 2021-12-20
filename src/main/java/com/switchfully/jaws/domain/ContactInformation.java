package com.switchfully.jaws.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class ContactInformation {
    @Column(name = "cellphone_number")
    private String cellphoneNumber;

    @Column(name = "home_number")
    private String homePhoneNumber;

    @Column(name = "email")
    private String emailAddress;

    protected ContactInformation() {}

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
            this.emailAddress = emailAddress;
            return this;
        }

        public ContactInformation build() {
            return new ContactInformation(this);
        }
    }
}

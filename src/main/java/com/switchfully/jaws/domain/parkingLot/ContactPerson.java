package com.switchfully.jaws.domain.parkingLot;

import com.switchfully.jaws.domain.Address;
import com.switchfully.jaws.domain.ContactInformation;
import com.switchfully.jaws.domain.Name;

import javax.persistence.*;

@Entity
@Table(name = "contact_person")
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact_person")
    private long id_contact_person;

    @Embedded
    private Name name;

    @Embedded
    private ContactInformation contactInformation;

    @OneToOne
    @JoinColumn(name = "fk_id_address")
    private Address address;

    public ContactPerson(long id_contact_person, Name name, ContactInformation contactInformation, Address address) {
        if (contactInformation.getCellphoneNumber() == null && contactInformation.getHomePhoneNumber() == null) {
            throw new IllegalArgumentException("A contact person must have at least one phone number.");
        }
        this.id_contact_person = id_contact_person;
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public ContactPerson() {
    }
}

package com.switchfully.jaws.services.parkingLot.dtos;

import com.switchfully.jaws.domain.Name;
import com.switchfully.jaws.domain.common.Address;
import com.switchfully.jaws.domain.common.ContactInformation;
import com.switchfully.jaws.domain.parkingLot.ContactPerson;
import com.switchfully.jaws.services.common.dto.AddressMapper;
import com.switchfully.jaws.services.common.dto.ContactInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonMapper {

    private final ContactInformationMapper contactInformationMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public ContactPersonMapper(ContactInformationMapper contactInformationMapper, AddressMapper addressMapper) {
        this.contactInformationMapper = contactInformationMapper;
        this.addressMapper = addressMapper;
    }

    public ContactPerson mapCreateDtoToEntity(CreateContactPersonDto createContactPersonDto) {
        Name name = new Name(createContactPersonDto.lastName(), createContactPersonDto.firstName());
        ContactInformation contactInformation = contactInformationMapper.mapDtoToEntity(createContactPersonDto.contactInformation());
        Address address = addressMapper.mapCreateDtoToEntity(createContactPersonDto.address());

        return new ContactPerson(name, contactInformation, address);
    }

    public CreateContactPersonDto mapEntityToCreateDto(ContactPerson contactPerson) {
        return new CreateContactPersonDto(contactPerson.getName().getLastName(),
                contactPerson.getName().getFirstName(),
                contactInformationMapper.mapEntityToDto(contactPerson.getContactInformation()),
                addressMapper.mapEntityToCreateDto(contactPerson.getAddress()));
    }

}

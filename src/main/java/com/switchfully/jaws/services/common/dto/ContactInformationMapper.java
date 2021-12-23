package com.switchfully.jaws.services.common.dto;

import com.switchfully.jaws.domain.common.ContactInformation;
import org.springframework.stereotype.Component;

@Component
public class ContactInformationMapper {

    public ContactInformation mapDtoToEntity(ContactInformationDto contactInformationDto) {
        return new ContactInformation.ContactInfoBuilder()
                .withCellPhoneNumber(contactInformationDto.cellPhoneNumber())
                .withEmailAddress(contactInformationDto.emailAddress())
                .withHomePhoneNumber(contactInformationDto.homePhoneNumber())
                .build();
    }

    public ContactInformationDto mapEntityToDto(ContactInformation contactInformation){
        return new ContactInformationDto(contactInformation.getCellphoneNumber(), contactInformation.getHomePhoneNumber(), contactInformation.getEmailAddress());
    }

}

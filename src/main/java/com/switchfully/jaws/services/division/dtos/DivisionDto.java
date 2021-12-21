package com.switchfully.jaws.services.division.dtos;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;


public record DivisionDto(long id, String name, String  originalName, String  directorFullName) {



}

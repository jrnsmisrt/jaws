package com.switchfully.jaws.services.division.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public class DivisionDto{
    private final String name;
    @JsonProperty("original_name")
    private final String originalName;
    @JsonProperty("director_fullname")
    private final String directorFullName;
    @JsonProperty("all_subdivisions")
    private final Optional<List<DivisionDto>> createDivisionDtoList;

    private DivisionDto (DivisionDtoBuilder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
        this.createDivisionDtoList = Optional.ofNullable(builder.divisionDtoList);
    }

    public static class DivisionDtoBuilder {
        private String name;
        private String originalName;
        private String directorFullName;
        private List<DivisionDto> divisionDtoList;

        public DivisionDtoBuilder() {
        }

        public DivisionDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DivisionDtoBuilder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public DivisionDtoBuilder withDirectorFullName(String directorFullName) {
            this.directorFullName = directorFullName;
            return this;
        }

        public DivisionDtoBuilder withDivisionDtoList(List<DivisionDto> divisionDtoList) {
            this.divisionDtoList = divisionDtoList;
            return this;
        }

        public DivisionDto build() {
            return new DivisionDto(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirectorFullName() {
        return directorFullName;
    }

    public Optional<List<DivisionDto>> getCreateDivisionDtoList() {
        return createDivisionDtoList;
    }
}

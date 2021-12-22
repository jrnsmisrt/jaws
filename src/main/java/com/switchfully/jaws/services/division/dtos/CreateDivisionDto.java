package com.switchfully.jaws.services.division.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public class CreateDivisionDto {
    private final String name;
    @JsonProperty("original_name")
    private final String originalName;
    @JsonProperty("director_fullname")
    private final String directorFullName;
    @JsonProperty("all_subdivisions")
    private final Optional<List<CreateDivisionDto>> createDivisionDtoList;

    private CreateDivisionDto(String name, String originalName, String directorFullName, Optional<List<CreateDivisionDto>> createDivisionDtoList) {
        this.name = name;
        this.originalName = originalName;
        this.directorFullName = directorFullName;
        this.createDivisionDtoList = createDivisionDtoList;
    }

    private CreateDivisionDto (CreateDivisionDtoBuilder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
        this.createDivisionDtoList = Optional.ofNullable(builder.createDivisionDtoList);
    }

    public static class CreateDivisionDtoBuilder {
        private String name;
        private String originalName;
        private String directorFullName;
        private List<CreateDivisionDto> createDivisionDtoList;

        public CreateDivisionDtoBuilder() {
        }

        public CreateDivisionDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateDivisionDtoBuilder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public CreateDivisionDtoBuilder withDirectorFullName(String directorFullName) {
            this.directorFullName = directorFullName;
            return this;
        }

        public CreateDivisionDtoBuilder withCreateDivisionDtoList(List<CreateDivisionDto> createDivisionDtoList) {
            this.createDivisionDtoList = createDivisionDtoList;
            return this;
        }

        public CreateDivisionDto build() {
            return new CreateDivisionDto(this);
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

    public Optional<List<CreateDivisionDto>> getCreateDivisionDtoList() {
        return createDivisionDtoList;
    }
}

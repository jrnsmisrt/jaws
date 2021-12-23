package com.switchfully.jaws.services.division.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class DivisionDto{
    private final String name;
    @JsonProperty("original_name")
    private final String originalName;
    @JsonProperty("director_fullname")
    private final String directorFullName;
    @JsonProperty("parent_division_id")
    private Optional<Long> parentDivisionId;

    private DivisionDto (DivisionDtoBuilder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
        this.parentDivisionId = builder.parentDivisionId;
    }

    public static class DivisionDtoBuilder {
        private String name;
        private String originalName;
        private String directorFullName;
        private Optional<Long> parentDivisionId;

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

        public DivisionDtoBuilder withParentDivisionId(Optional<Long> parentDivisionId) {
            this.parentDivisionId = parentDivisionId;
            return this;
        }

        public DivisionDto build() {
            return new DivisionDto(this);
        }
    }

    public String getName() {
        return name;
    }
}

package com.switchfully.jaws.services.division.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateDivisionDto {
    private final String name;
    @JsonProperty("original_name")
    private final String originalName;
    @JsonProperty("director_fullname")
    private final String directorFullName;
    @JsonProperty("parent_division_id")
    private Long parentDivisionId;

    private CreateDivisionDto(String name, String originalName, String directorFullName, long parentDivisionId) {
        this.name = name;
        this.originalName = originalName;
        this.directorFullName = directorFullName;
        this.parentDivisionId = parentDivisionId;
    }

    private CreateDivisionDto (CreateDivisionDtoBuilder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
        this.parentDivisionId = builder.parentDivisionId;
    }

    public static class CreateDivisionDtoBuilder {
        private String name;
        private String originalName;
        private String directorFullName;
        private Long parentDivisionId;

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

        public CreateDivisionDtoBuilder withParentDivisionId(Long parentDivisionId) {
            this.parentDivisionId = parentDivisionId;
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

    public Long getParentDivisionId() {
        return parentDivisionId;
    }
}

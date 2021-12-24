package com.switchfully.jaws.services.division.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.switchfully.jaws.domain.Division;

import java.util.List;
import java.util.Optional;

@JsonPropertyOrder({"division_id", "parent_division_id", "name", "original_name", "director_fullname", "subdivisions"})
public class DivisionDto{
    @JsonProperty("division_id")
    private Long divisionId;
    private String name;
    @JsonProperty("original_name")
    private String originalName;
    @JsonProperty("director_fullname")
    private String directorFullName;
    @JsonProperty("parent_division_id")
    private Optional<Long> parentDivisionId;
    @JsonProperty("subdivisions")
    private List<Division>  subDivisions;

    public DivisionDto() {

    }

    private DivisionDto(Long divisionId, String name, String originalName, String directorFullName,
                        Optional<Long> parentDivisionId, List<Division> subDivisions) {
        this.divisionId = divisionId;
        this.name = name;
        this.originalName = originalName;
        this.directorFullName = directorFullName;
        this.parentDivisionId = parentDivisionId;
        this.subDivisions = subDivisions;
    }

    private DivisionDto (DivisionDtoBuilder builder) {
        this.divisionId = builder.divisionId;
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
        this.parentDivisionId = builder.parentDivisionId;
        this.subDivisions = builder.subdivisions;
    }

    public static class DivisionDtoBuilder {
        private Long divisionId;
        private String name;
        private String originalName;
        private String directorFullName;
        private Optional<Long> parentDivisionId;
        private List<Division> subdivisions;

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

        public DivisionDtoBuilder withSubDivision(List<Division> subdivisions){
            this.subdivisions = subdivisions;
            return this;
        }

        public DivisionDtoBuilder withDivisionId(Long divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public DivisionDto build() {
            return new DivisionDto(this);
        }
    }

    public Long getDivisionId() {
        return divisionId;
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

    public Optional<Long> getParentDivisionId() {
        return parentDivisionId;
    }

    public List<Division> getSubDivisions() {
        return subDivisions;
    }
}

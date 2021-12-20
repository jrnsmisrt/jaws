package com.switchfully.jaws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_division", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "original_name", nullable = false, unique = false)
    private String originalName;

    @Column(name = "director_fullname", nullable = false, unique = true)
    private String directorFullName;

    protected Division() {}

    private Division(DivisionBuilder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.directorFullName = builder.directorFullName;
    }

    public static class DivisionBuilder {
        private String name;
        private String originalName;
        private String directorFullName;

        public DivisionBuilder() {
        }

        public DivisionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DivisionBuilder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public DivisionBuilder withDirectorFullName(String directorFullName) {
            this.directorFullName = directorFullName;
            return this;
        }

        public Division build() {
            return new Division(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return name.equals(division.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

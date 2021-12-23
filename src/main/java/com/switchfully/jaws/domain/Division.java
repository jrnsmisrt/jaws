package com.switchfully.jaws.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_division", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "director_fullname", nullable = false)
    private String directorFullName;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_div_fk")
    private List<Division> subDivisions;

    protected Division() {
    }

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

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", directorFullName='" + directorFullName + '\'' +
                ", parentDivisionId=" + subDivisions +
                '}';
    }

    public Long getId() {
        return id;
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

    public List<Division> getSubDivisions() {
        return subDivisions;
    }

    public void addSubdivision(Division division) {
        if (getSubDivisions() == null) {
            this.subDivisions = new ArrayList<>();
        }
        subDivisions.add(division);
    }

}

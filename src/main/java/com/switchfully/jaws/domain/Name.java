package com.switchfully.jaws.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class Name {

    @Column(name = "last_name")
    private final String lastName;

    @Column(name="first_name")
    private final String firstName;

    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Name() {
        this("", "");
    }
}

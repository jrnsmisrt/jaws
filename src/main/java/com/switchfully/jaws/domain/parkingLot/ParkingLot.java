package com.switchfully.jaws.domain.parkingLot;

import com.switchfully.jaws.domain.Address;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parking_lot")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_contact_person", nullable = false)
    private ContactPerson contactPerson;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_address", nullable = false)
    private Address address;

    @Column(name = "price_per_hour", nullable = false)
    private double pricePerHour;

    public ParkingLot(String name, Category category, int maxCapacity, ContactPerson contactPerson, Address address, double pricePerHour) {
        this.name = name;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    protected ParkingLot() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
}

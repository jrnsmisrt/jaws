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

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "fk_id_contact_person")
    private ContactPerson contactPerson;

    @OneToOne
    @JoinColumn(name = "fk_id_address")
    private Address address;

    @Column(name = "price_per_hour")
    private double pricePerHour;

}

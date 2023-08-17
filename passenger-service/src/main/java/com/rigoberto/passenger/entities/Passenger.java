package com.rigoberto.passenger.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "transport_id", nullable = false)
    private Integer transportId;

    @OneToOne(mappedBy = "passenger", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;
}

package com.rigoberto.transport.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "registration_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date registrationDate;

    @Column(name = "type_vehicle", nullable = false)
    @Enumerated(EnumType.STRING)
    protected TypeVehicle typeVehicle;

    @Column(name = "destination", nullable = false)
    @Enumerated(EnumType.STRING)
    protected Destination destination;

    @Column(name = "brand", nullable = false)
    protected String brand;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "transport_driver",
            joinColumns = { @JoinColumn(name = "transport_id", referencedColumnName = "id", table = "transport") },
            inverseJoinColumns = { @JoinColumn(name = "driver_id", referencedColumnName = "id", table = "driver")
            })
    private Set<Driver> drivers;
}

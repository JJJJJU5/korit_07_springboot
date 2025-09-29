package com.example.cardatabase4.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long owner_id;

    @NonNull
    private  String firstName;
    @NonNull
    private  String lastName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;
}

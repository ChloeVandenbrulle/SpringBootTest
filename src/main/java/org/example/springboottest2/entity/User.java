package org.example.springboottest2.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.springboottest2.enums.GenderEnum;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String countryOfResidence;

    @Column
    private String phoneNumber;

    @Column
    private GenderEnum gender;
}

package com.sportingevents.player;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "players", schema = "sporting_events_player_schema")
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "player_id")
    private Integer playerId;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "country")
    private String country;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;

    @NonNull
    @Column(name = "team_id")
    private Integer teamId;
}

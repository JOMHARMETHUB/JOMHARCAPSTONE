package com.sportingevents.team;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "teams", schema = "sporting_events_team_schema")
@NoArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "team_id")
    private Integer teamId;

    @NonNull
    @Column(name = "team_name")
    private String teamName;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;
}

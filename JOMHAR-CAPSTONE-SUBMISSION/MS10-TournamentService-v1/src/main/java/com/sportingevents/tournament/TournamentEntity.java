package com.sportingevents.tournament;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tournaments", schema = "sporting_events_tournament_schema")
@NoArgsConstructor
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "tournament_id")
    private Integer tournamentId;

    @NonNull
    @Column(name = "sports_category")
    private String sportsCategory;


    @NonNull
    @Column(name = "tournament_style")
    private String tournamentStyle;

    @NonNull
    @Column(name = "tournament_name")
    private String tournamentName;

    @NonNull
    @Column(name = "active")
    private Boolean active = true;
}

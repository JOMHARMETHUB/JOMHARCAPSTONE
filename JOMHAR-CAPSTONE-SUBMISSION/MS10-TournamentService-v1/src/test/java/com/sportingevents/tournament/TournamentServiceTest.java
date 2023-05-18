package com.sportingevents.tournament;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TournamentServiceTest {

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Mock
    private TournamentRepository tournamentRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetAllTournaments_returnAllTournaments() {
        Pageable page = PageRequest.of(0, 10);
        when(tournamentRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPageTournamentEntity());
        List<TournamentEntity> result = tournamentService.getAllTournaments(page);;
        Assert.assertNotNull(result);
    }

    @Test
    public void whenGetAllTournamentsIsEmpty_returnMessage() {
        exception.expect(TournamentException.class);
        exception.expectMessage(TournamentMessage.TOURNAMENT_NOT_FOUND);
        Pageable page = PageRequest.of(0, 10);
        when(tournamentRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        tournamentService.getAllTournaments(page);;
    }

    @Test
    public void givenValidId_whenGetTournament_returnTournament() {
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        TournamentEntity result = tournamentService.getTournament(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void givenInvalidId_whenGetTournament_returnMessage() {
        exception.expect(TournamentException.class);
        exception.expectMessage(TournamentMessage.TOURNAMENT_NOT_FOUND);
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        tournamentService.getTournament(1);
    }

    @Test
    public void givenValidRequestModel_whenSaveTournament_returnMessage() {
        TournamentRequestModel tournament = getTournamentRequestModel();
        String result = tournamentService.saveTournament(tournament);
        Assert.assertEquals(TournamentMessage.TOURNAMENT_SAVE_SUCCESS, result);
    }

    @Test
    public void givenValidRequestModelAndValidId_whenUpdateTournament_returnMessage() {
        TournamentRequestModel tournament = getTournamentRequestModel();
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        String result = tournamentService.updateTournament(1, tournament);
        Assert.assertEquals(TournamentMessage.TOURNAMENT_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenInvalidId_whenUpdateTournament_returnMessage() {
        exception.expect(TournamentException.class);
        exception.expectMessage(TournamentMessage.TOURNAMENT_NOT_FOUND);
        TournamentRequestModel tournament = getTournamentRequestModel();
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        tournamentService.updateTournament(1, tournament);
    }

    @Test
    public void givenValidId_whenDeleteTournament_returnMessage() {
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        String result = tournamentService.deleteTournament(1);
        Assert.assertEquals(TournamentMessage.TOURNAMENT_DELETE_SUCCESS, result);
    }

    @Test
    public void givenInvalidId_whenDeleteTournament_returnMessage() {
        exception.expect(TournamentException.class);
        exception.expectMessage(TournamentMessage.TOURNAMENT_NOT_FOUND);
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        tournamentService.deleteTournament(1);
    }

    private TournamentRequestModel getTournamentRequestModel() {
        TournamentRequestModel tournamentRequestModel = new TournamentRequestModel();
        tournamentRequestModel.setTournamentStyle("test");
        tournamentRequestModel.setTournamentName("test");
        tournamentRequestModel.setSportsCategory("test");
        return tournamentRequestModel;
    }

    private Page<TournamentEntity> getPageTournamentEntity() {
        Pageable pageable = PageRequest.of(0, 10);
        List<TournamentEntity> tournaments = getTournamentEntities();
        int start = Math.min((int)pageable.getOffset(), tournaments.size());
        int end = Math.min((start + pageable.getPageSize()), tournaments.size());
        Page<TournamentEntity> tournamentEntityPage = new PageImpl<>(tournaments.subList(start, end), pageable, tournaments.size());
        return tournamentEntityPage;
    }

    private List<TournamentEntity> getTournamentEntities() {
        List<TournamentEntity> tournaments = new ArrayList<>();
        for(int x=0;x<2;x++) {
            TournamentEntity tournament = getTournamentEntity();
            tournament.setTournamentId(x+1);
            tournaments.add(tournament);
        }
        return tournaments;
    }

    private TournamentEntity getTournamentEntity() {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentStyle("test");
        tournamentEntity.setSportsCategory("test");
        tournamentEntity.setActive(true);
        tournamentEntity.setTournamentId(1);
        tournamentEntity.setTournamentName("test");
        return tournamentEntity;
    }
}

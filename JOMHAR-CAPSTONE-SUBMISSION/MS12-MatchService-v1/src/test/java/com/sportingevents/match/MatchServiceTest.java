package com.sportingevents.match;

import com.sportingevents.field.FieldResponseModel;
import com.sportingevents.tournament.TournamentResponseModel;
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
public class MatchServiceTest {

    @InjectMocks
    private MatchServiceImpl matchService;

    @Mock
    private MatchRepository matchRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenFindAllMatches_returnMatches() {
        Pageable page = PageRequest.of(0, 10);
        when(matchRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPageMatches());
        List<MatchResponseModel> result = matchService.findAllMatches(page);
        Assert.assertNotNull(result);
    }

    @Test
    public void whenFindAllMatchesIsEmpty_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.MATCH_NOT_FOUND);
        Pageable page = PageRequest.of(0, 10);
        when(matchRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        matchService.findAllMatches(page);
    }

    @Test
    public void whenFindMatch_returnMatch() {
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getMatchEntity()));
        MatchResponseModel result = matchService.findMatch(1);
        Assert.assertNotNull(result);
    }
    @Test
    public void givenMatchNotExisting_whenFindMatch_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.MATCH_NOT_FOUND);
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        matchService.findMatch(1);
    }

    @Test
    public void givenValidId_whenDelete_returnMessage() {
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getMatchEntity()));
        String result = matchService.deleteMatch(1);
        Assert.assertEquals(MatchMessage.MATCH_DELETE_SUCCESS, result);
    }

    @Test
    public void givenMatchNotExisting_whenDelete_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.MATCH_NOT_FOUND);
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        matchService.deleteMatch(1);
    }

    @Test
    public void givenValidRequestModel_whenSave_returnMessage() {
        MatchRequestModel match = getMatchRequestModel();
        String result = matchService.saveMatch(match);
        Assert.assertEquals(MatchMessage.MATCH_SAVE_SUCCESS, result);
    }

    @Test
    public void givenInvalidDate_whenSave_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.INVALID_DATE);
        MatchRequestModel match = getMatchRequestModel();
        match.setDateTime("2023-05-03 10:10:23");
        matchService.saveMatch(match);
    }

    @Test
    public void givenInvalidDateFormat_whenSave_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.INVALID_DATE_FORMAT);
        MatchRequestModel match = getMatchRequestModel();
        match.setDateTime("");
        matchService.saveMatch(match);
    }

    @Test
    public void givenValidIdAndRequestModel_whenUpdate_returnMessage() {
        MatchRequestModel match = getMatchRequestModel();
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getMatchEntity()));
        String result = matchService.updateMatch(1, match);
        Assert.assertEquals(MatchMessage.MATCH_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenMatchNotExisting_whenUpdate_returnMessage() {
        exception.expect(MatchException.class);
        exception.expectMessage(MatchMessage.MATCH_NOT_FOUND);
        MatchRequestModel match = getMatchRequestModel();
        when(matchRepository.findByMatchIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        matchService.updateMatch(1, match);
    }

    private FieldResponseModel getFieldResponseModel() {
        FieldResponseModel field = new FieldResponseModel();
        field.setFieldAddress("test");
        field.setFieldName("test");
        field.setCapacity(100);
        field.setFieldId(1);
        return field;
    }

    private TournamentResponseModel getTournamentResponseModel() {
        TournamentResponseModel tournament = new TournamentResponseModel();
        tournament.setTournamentId(1);
        tournament.setTournamentName("test");
        tournament.setTournamentStyle("test");
        tournament.setSportsCategory("test");
        tournament.setActive(true);
        return tournament;
    }


    private Page<MatchEntity> getPageMatches() {
        Pageable pageable = PageRequest.of(0, 10);
        List<MatchEntity> matches = getMatchEntities();
        int start = Math.min((int)pageable.getOffset(), matches.size());
        int end = Math.min((start + pageable.getPageSize()), matches.size());
        Page<MatchEntity> matchesEntityPage = new PageImpl<>(matches.subList(start, end), pageable, matches.size());
        return matchesEntityPage;
    }

    private List<MatchEntity> getMatchEntities() {
        List<MatchEntity> matches= new ArrayList<>();
        for(int x=0;x<2;x++) {
            MatchEntity match = getMatchEntity();
            match.setMatchId(x+1);
            matches.add(match);
        }
        return matches;
    }

    private MatchEntity getMatchEntity() {
        MatchEntity match = new MatchEntity();
        match.setActive(true);
        match.setMatchId(1);
        match.setFieldId(1);
        match.setTournamentId(1);
        match.setDateTime("2099-05-03 11:43:15");
        match.setTeamsId("1,2");
        match.setParticipantsId("1,2");
        return match;
    }

    private MatchRequestModel getMatchRequestModel() {
        MatchRequestModel matchRequestModel = new MatchRequestModel();
        matchRequestModel.setDateTime("2099-05-03 11:43:15");
        matchRequestModel.setFieldId(1);
        matchRequestModel.setTournamentId(1);
        matchRequestModel.setFieldId(1);
        matchRequestModel.setParticipantsId("1,2");
        matchRequestModel.setTeamsId("1,2");
        return matchRequestModel;
    }


    private List<MatchResponseModel> getMatchResponseModels() {
        List<MatchResponseModel> matches = new ArrayList<>();
        for(int x=0;x<2;x++) {
            MatchResponseModel match = getMatchResponseModel();
            match.setMatchId(x+1);
            matches.add(match);
        }
        return matches;
    }

    private MatchResponseModel getMatchResponseModel() {
        MatchResponseModel matchResponseModel = new MatchResponseModel();
        matchResponseModel.setDateTime("2099-05-03 11:43:15");
        matchResponseModel.setMatchId(1);
        matchResponseModel.setFieldId(1);
        matchResponseModel.setTournamentId(1);
        return matchResponseModel;
    }


    private FieldResponseModel getFieldEntity() {
        FieldResponseModel fieldEntity = new FieldResponseModel();
        fieldEntity.setFieldAddress("test");
        fieldEntity.setActive(true);
        fieldEntity.setFieldId(1);
        fieldEntity.setCapacity(10);
        fieldEntity.setFieldName("test");
        return fieldEntity;
    }
}

package com.sportingevents.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository matchRepository;



    @Override
    public List<MatchResponseModel> findAllMatches(Pageable pageable) {
        List<MatchEntity> matchEntityList = matchRepository.findByActiveTrue(pageable).getContent();
        if(matchEntityList.isEmpty())
            throw new MatchException(MatchMessage.MATCH_NOT_FOUND);
        return matchEntityList.stream().map(this::mapMatchToResponseModel).collect(Collectors.toList());
    }

    @Override
    public MatchResponseModel findMatch(Integer matchId) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findByMatchIdAndActiveTrue(matchId);
        if(!matchEntityOptional.isPresent())
            throw new MatchException(MatchMessage.MATCH_NOT_FOUND);
        return mapMatchToResponseModel(matchEntityOptional.get());
    }

    @Override
    public String deleteMatch(Integer matchId) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findByMatchIdAndActiveTrue(matchId);
        if(!matchEntityOptional.isPresent())
            throw new MatchException(MatchMessage.MATCH_NOT_FOUND);
        MatchEntity matchEntity = matchEntityOptional.get();
        matchEntity.setActive(false);
        matchRepository.save(matchEntity);
        return MatchMessage.MATCH_DELETE_SUCCESS;
    }

    @Override
    public String saveMatch(MatchRequestModel matchRequestModel) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setFieldId(matchRequestModel.getFieldId());
        matchEntity.setTournamentId(matchRequestModel.getTournamentId());
        matchEntity.setTeamsId(matchRequestModel.getTeamsId());
        matchEntity.setParticipantsId(matchRequestModel.getParticipantsId());
        matchEntity.setDateTime(validateDate(matchRequestModel.getDateTime()));

        matchRepository.save(matchEntity);

        return MatchMessage.MATCH_SAVE_SUCCESS;
    }

    private String validateDate(String dateTime) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
            if(date.before(new Date()))
                throw new MatchException(MatchMessage.INVALID_DATE);
        } catch (ParseException ex) {
            throw new MatchException(MatchMessage.INVALID_DATE_FORMAT);
        }
        return dateTime;
    }

    @Override
    public String updateMatch(Integer matchId, MatchRequestModel matchRequestModel) {
        Optional<MatchEntity> matchEntityOptional = matchRepository.findByMatchIdAndActiveTrue(matchId);
        if(!matchEntityOptional.isPresent())
            throw new MatchException(MatchMessage.MATCH_NOT_FOUND);

        MatchEntity matchEntity = matchEntityOptional.get();
        matchEntity.setFieldId(matchRequestModel.getFieldId());
        matchEntity.setTournamentId(matchRequestModel.getTournamentId());
        matchEntity.setTeamsId(matchRequestModel.getTeamsId());
        matchEntity.setParticipantsId(matchRequestModel.getParticipantsId());
        matchEntity.setDateTime(validateDate(matchRequestModel.getDateTime()));

        matchRepository.save(matchEntity);

        return MatchMessage.MATCH_UPDATE_SUCCESS;
    }

    public MatchResponseModel mapMatchToResponseModel(MatchEntity matchEntity) {
        MatchResponseModel matchResponseModel = new MatchResponseModel();
        matchResponseModel.setMatchId(matchEntity.getMatchId());
        matchResponseModel.setFieldId(matchEntity.getFieldId());
        matchResponseModel.setTournamentId(matchEntity.getTournamentId());
        matchResponseModel.setDateTime(matchEntity.getDateTime());
        matchResponseModel.setTeamsId(matchEntity.getTeamsId());
        matchResponseModel.setParticipantsId(matchEntity.getParticipantsId());
        return matchResponseModel;
    }

}

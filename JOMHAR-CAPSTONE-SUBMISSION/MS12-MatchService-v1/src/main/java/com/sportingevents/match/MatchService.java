package com.sportingevents.match;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchService {
    List<MatchResponseModel> findAllMatches(Pageable pageable);

    MatchResponseModel findMatch(Integer matchId);

    String deleteMatch(Integer matchId);

    String saveMatch(MatchRequestModel matchRequestMode);

    String updateMatch(Integer matchId, MatchRequestModel matchRequestModel);
}

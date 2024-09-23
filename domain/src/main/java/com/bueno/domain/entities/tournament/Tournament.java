package com.bueno.domain.entities.tournament;

import com.bueno.domain.usecases.bot.providers.BotManagerService;
import com.bueno.domain.usecases.bot.providers.RemoteBotApi;
import com.bueno.domain.usecases.bot.repository.RemoteBotRepository;

import java.util.List;
import java.util.UUID;

public class Tournament {
    private final UUID tournamentUUID;
    private final int size;
    private final List<String> participantNames;
    private List<Match> matches;

    public Tournament(List<String> participantNames, int size) {
        this.tournamentUUID = UUID.randomUUID();
        this.size = size;
        this.participantNames = participantNames;
    }

    public void playAllAvailable(RemoteBotApi api, RemoteBotRepository repository, BotManagerService botManagerService) {
        for (Match m : matches) {
            if (m.isAvailable()) {
                m.play(repository, api, botManagerService, 51);
            }
        }

    }

    public void playByMatchUuid(UUID matchUuid, RemoteBotApi api, BotManagerService botManagerService, RemoteBotRepository repository) {
        matches.stream()
                .filter(match -> match.getId() == matchUuid)
                .findFirst()
                .ifPresentOrElse(match -> match.play(repository, api, botManagerService, 51),
                        () -> System.out.println("No match found"));
    }

    public void setAvailableMatches() {
        for (Match match : matches) {
            if (match.isAvailable() && match.getWinnerName() != null) {
                match.setAvailable(false);
            }
            if (match.getP1Name() != null && match.getP2Name() != null && match.getWinnerName() == null) {
                match.setAvailable(true);
            }
        }
    }

    public UUID getTournamentUUID() {
        return tournamentUUID;
    }

    public int getSize() {
        return size;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<String> getParticipantNames() {
        return participantNames;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Tournament{" +
               "tournamentUUID=" + tournamentUUID +
               ", size=" + size +
               ", matches=" + (matches == null ? " null" : matches.stream().map(match -> "\n\t" + match.toString()).toList()) +
               '}';
    }
}

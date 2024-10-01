package com.bueno.persistence.repositories;

import com.bueno.domain.usecases.tournament.dtos.MatchDTO;
import com.bueno.domain.usecases.tournament.dtos.TournamentDTO;
import com.bueno.domain.usecases.tournament.repos.TournamentRepository;
import com.bueno.persistence.dao.MatchDao;
import com.bueno.persistence.dao.TournamentDao;
import com.bueno.persistence.dto.MatchEntity;
import com.bueno.persistence.dto.TournamentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TournamentRepositoryMongoImpl implements TournamentRepository {
    private final TournamentDao tournamentDao;
    private final MatchDao matchDao;

    public TournamentRepositoryMongoImpl(TournamentDao tournamentDao, MatchDao matchDao) {
        this.tournamentDao = tournamentDao;
        this.matchDao = matchDao;
    }

    @Override
    public Optional<TournamentDTO> findTournamentById(UUID uuid) {
        TournamentEntity entity = tournamentDao.findTournamentEntityByUuid(uuid).orElse(null);
        return getTournamentDTO(entity);
    }

    @Override
    public List<MatchDTO> findMatchesByTournamentId(UUID uuid) {
        TournamentEntity entity = tournamentDao.findTournamentEntityByUuid(uuid).orElse(null);
        Optional<TournamentDTO> dto = getTournamentDTO(entity);
        if (dto.isPresent()) {
            return dto.get().matchesDTO();
        }
        return List.of();
    }

    @Override
    public List<TournamentDTO> findAll() {
        return tournamentDao.findAll().stream().map(entity -> getTournamentDTO(entity).orElse(null)).toList();
    }

    @Override
    public void save(TournamentDTO dto) {
        TournamentEntity entity = TournamentEntity.from(dto);
        matchDao.saveAll(dto.matchesDTO().stream().map(MatchEntity::from).toList());
        tournamentDao.save(entity);
    }

    @Override
    public void update(TournamentDTO dto) {
        dto.matchesDTO().forEach(m -> matchDao.deleteMatchEntitiesByUuid(m.uuid()));
        tournamentDao.deleteTournamentEntityByUuid(dto.uuid());
//        if (tournamentDao.findTournamentEntityByUuid(dto.uuid()).isPresent()) {
//            throw new RuntimeException("Objeto não deletado");
//        }
        TournamentEntity entity = TournamentEntity.from(dto);
        tournamentDao.save(entity);
        matchDao.saveAll(dto.matchesDTO().stream().map(MatchEntity::from).toList());
        if (tournamentDao.findTournamentEntityByUuid(dto.uuid()).isPresent()) {
            System.out.println("dto salvo");
        }
    }

    @Override
    public void deleteAll() {
        tournamentDao.deleteAll();
        matchDao.deleteAll();
    }

    private Optional<TournamentDTO> getTournamentDTO(TournamentEntity entity) {
        if (entity == null || entity.getUuid() == null) {
            return Optional.empty();
        }

        return Optional.of(entity.toDto());

    }

}

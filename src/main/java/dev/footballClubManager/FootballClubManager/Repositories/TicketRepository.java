package dev.footballClubManager.FootballClubManager.Repositories;

import dev.footballClubManager.FootballClubManager.Models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByUserId(String userId);
}

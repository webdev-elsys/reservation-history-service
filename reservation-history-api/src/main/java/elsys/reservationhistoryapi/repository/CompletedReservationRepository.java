package elsys.reservationhistoryapi.repository;

import elsys.reservationhistoryapi.entity.CompletedReservation;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompletedReservationRepository extends CassandraRepository<CompletedReservation, String> {
    Long countAllByPropertyUuidAndCheckInBetween(String propertyUuid, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(totalPrice) FROM CompletedReservation WHERE propertyUuid = ?0 AND checkIn >= ?1 AND checkIn <= ?2")
    Float sumTotalPriceByPropertyUuidAndCheckInBetween(String propertyUuid, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(guests) FROM CompletedReservation WHERE propertyUuid = ?0 AND checkIn >= ?1 AND checkIn <= ?2")
    Long sumGuestsByPropertyUuidAndCheckInBetween(String propertyUuid, LocalDate startDate, LocalDate endDate);

    @Query("SELECT roomUuid FROM CompletedReservation WHERE propertyUuid = ?0 AND checkIn >= ?1 AND checkIn <= ?2 GROUP BY roomUuid ORDER BY COUNT(roomUuid) DESC LIMIT 1")
    String getMostPopularRoomByPropertyUuidAndCheckInBetween(String propertyUuid, LocalDate startDate, LocalDate endDate);
}

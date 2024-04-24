package elsys.reservationhistoryapi.service.Impl;

import elsys.reservationhistoryapi.dto.PropertyStats;
import elsys.reservationhistoryapi.entity.CompletedReservation;
import elsys.reservationhistoryapi.repository.CompletedReservationRepository;
import elsys.reservationhistoryapi.service.CompletedReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompletedReservationServiceImpl implements CompletedReservationService {
    private final CompletedReservationRepository completedReservationRepository;

    @Override
    public CompletedReservation addCompletedReservation(CompletedReservation completedReservation) {
        return completedReservationRepository.save(completedReservation);
    }

    @Override
    public PropertyStats getPropertyStatsBetweenDates(String propertyUuid, LocalDate startDate, LocalDate endDate) {
        Long totalReservations = completedReservationRepository.countAllByPropertyUuidAndCheckInBetween(propertyUuid, startDate, endDate);
        Float totalIncome = completedReservationRepository.sumTotalPriceByPropertyUuidAndCheckInBetween(propertyUuid, startDate, endDate);
        Long totalGuests = completedReservationRepository.sumGuestsByPropertyUuidAndCheckInBetween(propertyUuid, startDate, endDate);
        String mostPopularRoom = completedReservationRepository.getMostPopularRoomByPropertyUuidAndCheckInBetween(propertyUuid, startDate, endDate);

        return new PropertyStats(totalReservations, totalIncome, totalGuests, mostPopularRoom);
    }
}

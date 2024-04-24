package elsys.reservationhistoryapi.service;

import elsys.reservationhistoryapi.dto.PropertyStats;
import elsys.reservationhistoryapi.entity.CompletedReservation;

import java.time.LocalDate;

public interface CompletedReservationService {
    CompletedReservation addCompletedReservation(CompletedReservation completedReservation);
    PropertyStats getPropertyStatsBetweenDates(String propertyUuid, LocalDate startDate, LocalDate endDate);
}

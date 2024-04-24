package elsys.reservationhistoryapi.controller;

import elsys.reservationhistoryapi.dto.PropertyStats;
import elsys.reservationhistoryapi.service.CompletedReservationService;
import jakarta.validation.constraints.Future;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reservations-history")
@RequiredArgsConstructor
public class CompletedReservationController {
    private final CompletedReservationService completedReservationService;

    @GetMapping("/properties/{propertyUuid}/stats")
    public ResponseEntity<PropertyStats> getPropertyStatsBetweenDates(
        @PathVariable("propertyUuid") @UUID String propertyUuid,
        @RequestParam("startDate") @Future LocalDate startDate,
        @RequestParam("endDate") @Future LocalDate endDate
    ) {
        return ResponseEntity.ok(completedReservationService.getPropertyStatsBetweenDates(propertyUuid, startDate, endDate));
    }
}

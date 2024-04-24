package elsys.reservationhistoryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyStats {
    private Long totalCompletedReservations;
    private Float totalIncome;
    private Long totalGuests;
    private String mostPopularRoom;
}

package elsys.reservationhistoryapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Table("completed_reservations")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletedReservation {
    @JsonProperty
    @PrimaryKey private String uuid;

    @JsonProperty
    @Column private String propertyUuid;

    @JsonProperty
    @Column private String roomUuid;

    @JsonProperty
    @Column private String clientUuid;

    @JsonProperty
    @Column private LocalDate checkIn;

    @JsonProperty
    @Column private LocalDate checkOut;

    @JsonProperty
    @Column private int guests;

    @JsonProperty
    @Column private float totalPrice;

    @JsonProperty
    @Column private String comment;
}

package elsys.reservationhistoryapi.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import elsys.reservationhistoryapi.entity.CompletedReservation;
import elsys.reservationhistoryapi.service.CompletedReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CompletedReservationService completedReservationService;

    @KafkaListener(
        topics = "${kafka.reservation-completed-topic}",
        groupId = "${kafka.group-id}",
        containerFactory = "reservationDataKafkaListenerContainerFactory"
    )
    public void handleReservationCompleted(String message) throws JsonProcessingException {
        completedReservationService.addCompletedReservation(deserializeNotificationData(message));
    }

    private CompletedReservation deserializeNotificationData(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(); // I have tried to use JsonDeserializer, but it didn't work...
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(message, CompletedReservation.class);
    }
}

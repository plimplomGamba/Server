package de.plimplom.gamba.server.data;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@AnonymousAllowed
@RolesAllowed("GAMBA")
@RequestMapping("/api")
@Slf4j
public class DataController {

    private final ApplicationEventPublisher eventPublisher;

    public DataController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = "/data")
    public ResponseEntity<Void> acceptData(@RequestBody ClientData data) {
        log.info("Received data: {}", data);
        if (data == null || data.getHighLowDataList() == null || data.getDeathrollDataList() == null) {
            return ResponseEntity.badRequest().build();
        }
        eventPublisher.publishEvent(new ClientDataEvent(this, data));
        return ResponseEntity.accepted().build();
    }
}

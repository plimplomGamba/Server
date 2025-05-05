package de.plimplom.gamba.server.stats;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.jspecify.annotations.NonNull;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    public @NonNull StatsWrapper getFullData() {
        return service.getFullData();
    }
    public @NonNull StatsWrapper getDeathrollData() {
        return service.getDeathrollData();
    }
    public @NonNull StatsWrapper getHighLowData() {
        return service.getHighLowData();
    }
}

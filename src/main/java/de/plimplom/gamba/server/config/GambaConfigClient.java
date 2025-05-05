package de.plimplom.gamba.server.config;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.Nonnull;

@BrowserCallable
@AnonymousAllowed
public class GambaConfigClient {
    private final GambaConfig config;

    public GambaConfigClient(GambaConfig config) {
        this.config = config;
    }

    public @Nonnull String getGuildName() {
        return config.getGuild();
    }
}

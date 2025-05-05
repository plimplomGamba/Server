package de.plimplom.gamba.server.data;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ClientDataEvent extends ApplicationEvent {
    private final ClientData data;

    public ClientDataEvent(Object source, ClientData data) {
        super(source);
        this.data = data;
    }
}

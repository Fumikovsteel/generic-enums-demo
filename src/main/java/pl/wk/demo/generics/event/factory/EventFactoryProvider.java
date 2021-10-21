package pl.wk.demo.generics.event.factory;

import pl.wk.demo.generics.event.EventType;
import pl.wk.demo.generics.event.domain.DomainEvent;

public class EventFactoryProvider {

    public <E extends DomainEvent, T extends EventFactory<E>> T of(EventType<E, T> eventType) {
        try {
            return eventType.getEventFactoryClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // do something about it
            throw new RuntimeException(e);
        }
    }
}

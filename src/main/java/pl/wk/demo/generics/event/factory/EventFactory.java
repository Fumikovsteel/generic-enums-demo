package pl.wk.demo.generics.event.factory;

import pl.wk.demo.generics.event.EventType;
import pl.wk.demo.generics.event.domain.DomainEvent;

/**
 * Event factory, responsible for creating events by event type
 */
public interface EventFactory {

    <T extends DomainEvent> T of(EventType<T> eventType, String eventData);

    static EventFactory defaultFactory() {
        return new DefaultEventFactory();
    }
}

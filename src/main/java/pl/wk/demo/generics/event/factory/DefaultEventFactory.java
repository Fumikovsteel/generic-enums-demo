package pl.wk.demo.generics.event.factory;

import pl.wk.demo.generics.event.EventType;
import pl.wk.demo.generics.event.domain.DomainEvent;

/**
 * Default event factory which instantiates the event by class declared by the event type
 */
class DefaultEventFactory implements EventFactory {

    @Override
    public <T extends DomainEvent> T of(EventType<T> eventType, String eventData) {
        try {
            T event =  eventType.getEventClass().getDeclaredConstructor().newInstance();
            event.handle(eventData);
            return event;
        } catch (Exception e) {
            // do something clever about it
            return null;
        }
    }
}

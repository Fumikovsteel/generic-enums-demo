package pl.wk.demo.generics.event.domain;

/**
 * Domain event
 */
public interface DomainEvent {

    /**
     * Handle some stored event data to f.e. initialize the event with it
     *
     * @param eventData - event-specific stored data
     */
    default void handle(String eventData) {}
}

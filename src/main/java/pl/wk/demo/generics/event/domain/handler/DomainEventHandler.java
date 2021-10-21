package pl.wk.demo.generics.event.domain.handler;

import pl.wk.demo.generics.event.domain.DomainEvent;

import java.util.stream.Stream;

/**
 * Subclasses should provide single argument methods per event class, in order to handle any DomainEvent in generic way
 */
public interface DomainEventHandler {

    default void handle(DomainEvent event) {
        try {
            Stream.of(this.getClass().getDeclaredMethods())
                    .filter(m -> m.getParameterTypes().length == 1)
                    .filter(m -> m.getParameterTypes()[0] == event.getClass())
                    .findFirst()
                    .ifPresent(handler -> {
                        try {
                            handler.invoke(this, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package pl.wk.demo.generics.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import pl.wk.demo.generics.event.domain.CreateRoleEvent;
import pl.wk.demo.generics.event.domain.CreateUserEvent;
import pl.wk.demo.generics.event.domain.DomainEvent;
import pl.wk.demo.generics.event.domain.UpdateUserEvent;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Key can be used to map specific event type by string value (f.e. from db)
 *
 * @param <T> - type of domain event related to specific event type
 */
@Value(staticConstructor = "of")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventType<T extends DomainEvent> {

    public static EventType<CreateUserEvent> CREATE_USER = new EventType<>("CREATE_USER", CreateUserEvent.class);
    public static EventType<UpdateUserEvent> UPDATE_USER = new EventType<>("UPDATE_USER", UpdateUserEvent.class);
    public static EventType<CreateRoleEvent> CREATE_ROLE = new EventType<>("CREATE_ROLE", CreateRoleEvent.class);

    private static Map<String, EventType> EVENTS;

    static {
        try {
            EVENTS = Stream.of(EventType.class.getFields())
                    .map(f -> {
                        try {
                            return f.get(null); // btw, null serving as static-level accessor is mind-blowing
                        } catch (IllegalAccessException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .filter(EventType.class::isInstance)
                    .map(EventType.class::cast)
                    .collect(Collectors.toUnmodifiableMap(EventType::getKey, Function.identity()));
        } catch (Exception e) {
            // initialization error
            throw new RuntimeException("Unable to initialize events map", e);
        }
    }

    private final String key;
    private final Class<T> eventClass;

    public static Optional<EventType<?>> of(String key) {
        return Optional.ofNullable(EVENTS.get(key));
    }
}

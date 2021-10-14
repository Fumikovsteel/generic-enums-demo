package pl.wk.demo.generics;

import pl.wk.demo.generics.event.EventType;
import pl.wk.demo.generics.event.domain.CreateRoleEvent;
import pl.wk.demo.generics.event.domain.CreateUserEvent;
import pl.wk.demo.generics.event.domain.DomainEvent;
import pl.wk.demo.generics.event.domain.UpdateUserEvent;
import pl.wk.demo.generics.event.factory.EventFactory;

public class GenericEnumApp {

    public static void main(String... args) {
        EventFactory eventFactory = EventFactory.defaultFactory();

        // statically-typed
        CreateUserEvent event1 = eventFactory.of(EventType.CREATE_USER, "{...}");
        UpdateUserEvent event2 = eventFactory.of(EventType.UPDATE_USER, "{...}");
        CreateRoleEvent event3 = eventFactory.of(EventType.CREATE_ROLE, "{...}");
        System.out.println(String.format("%s, %s, %s", event1, event2, event3));
        // CreateUserEvent event4 = eventFactory.of(EventType.UPDATE_USER, "{...}"); // compilation error

        // dynamically-typed
        DomainEvent event4 = EventType.of("CREATE_USER")
                .map(type -> eventFactory.of(type, "{...}"))
                .orElse(null);
        DomainEvent event5 = EventType.of("UPDATE_USER")
                .map(type -> eventFactory.of(type, "{...}"))
                .orElse(null);
        System.out.println(String.format("%s, %s", event4, event5));
    }
}

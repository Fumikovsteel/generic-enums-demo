package pl.wk.demo.generics;

import pl.wk.demo.generics.event.EventType;
import pl.wk.demo.generics.event.domain.CreateRoleEvent;
import pl.wk.demo.generics.event.domain.CreateUserEvent;
import pl.wk.demo.generics.event.domain.DomainEvent;
import pl.wk.demo.generics.event.domain.UpdateUserEvent;
import pl.wk.demo.generics.event.factory.EventFactoryProvider;
import pl.wk.demo.generics.user.UserAggregate;

import java.util.List;

public class GenericEnumApp {

    public static void main(String... args) {
        EventFactoryProvider eventFactoryProvider = new EventFactoryProvider();

        // statically-typed
        CreateUserEvent event1 = eventFactoryProvider.of(EventType.CREATE_USER).create("{...}");
        UpdateUserEvent event2 = eventFactoryProvider.of(EventType.UPDATE_USER).create("{...}");
        CreateRoleEvent event3 = eventFactoryProvider.of(EventType.CREATE_ROLE).create("{...}");
        System.out.println(String.format("%s, %s, %s", event1, event2, event3));
        // CreateUserEvent event4 = eventFactoryProvider.of(EventType.UPDATE_USER).create("{...}"); // compilation error

        // dynamically-typed
        DomainEvent event4 = EventType.of("CREATE_USER")
                .map(type -> eventFactoryProvider.of(type).create("{...}"))
                .orElse(null);
        DomainEvent event5 = EventType.of("UPDATE_USER")
                .map(type -> eventFactoryProvider.of(type).create("{...}"))
                .orElse(null);
        DomainEvent event6 = EventType.of("CREATE_ROLE")
                .map(type -> eventFactoryProvider.of(type).create("{...}"))
                .orElse(null);
        System.out.println(String.format("%s, %s, %s", event4, event5, event6));

        // dynamic event-specific method call
        UserAggregate user = new UserAggregate();
        // event6 should be omitted
        List.of(event4, event5, event6).forEach(user::handle);
    }
}

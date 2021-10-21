package pl.wk.demo.generics.user;

import pl.wk.demo.generics.event.domain.CreateUserEvent;
import pl.wk.demo.generics.event.domain.handler.DomainEventHandler;
import pl.wk.demo.generics.event.domain.UpdateUserEvent;

public class UserAggregate implements DomainEventHandler {

    public void handle(CreateUserEvent event) {
        System.out.println("handling create user event");
    }

    public void handle(UpdateUserEvent event) {
        System.out.println("handling update user event");
    }
}

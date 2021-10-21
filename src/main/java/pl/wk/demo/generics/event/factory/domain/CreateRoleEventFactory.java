package pl.wk.demo.generics.event.factory.domain;

import pl.wk.demo.generics.event.domain.CreateRoleEvent;
import pl.wk.demo.generics.event.factory.EventFactory;

public class CreateRoleEventFactory implements EventFactory<CreateRoleEvent> {

    @Override
    public CreateRoleEvent create(String eventData) {
        return new CreateRoleEvent();
    }
}

package pl.wk.demo.generics.event.factory.domain;

import pl.wk.demo.generics.event.domain.CreateUserEvent;
import pl.wk.demo.generics.event.factory.EventFactory;

public class CreateUserEventFactory implements EventFactory<CreateUserEvent> {

    @Override
    public CreateUserEvent create(String eventData) {
        return new CreateUserEvent();
    }
}

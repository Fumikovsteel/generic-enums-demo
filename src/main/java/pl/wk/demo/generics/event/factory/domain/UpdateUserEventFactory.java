package pl.wk.demo.generics.event.factory.domain;

import pl.wk.demo.generics.event.domain.UpdateUserEvent;
import pl.wk.demo.generics.event.factory.EventFactory;

public class UpdateUserEventFactory implements EventFactory<UpdateUserEvent> {

    @Override
    public UpdateUserEvent create(String eventData) {
        return new UpdateUserEvent();
    }
}

package com.personal.productcatalog.action;

import static java.util.Objects.isNull;

public abstract class AbstractAction<T> {

    private AbstractAction<T> next;

    public AbstractAction<T> linkWith(AbstractAction<T> next) {
        this.next = next;
        return next;
    }

    public abstract T perform(T object);

    protected T performNext(T object) {
        return isNull(next) ? object : next.perform(object);
    }
}

package com.woogie.realworld.support

import org.springframework.data.domain.AfterDomainEventPublication
import org.springframework.data.domain.DomainEvents
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseAggregateRoot<A : BaseAggregateRoot<A>> : BaseAggregate() {
    @Transient
    private val domainEvents: MutableList<Any> = ArrayList()

    /**
     * Registers the given event object for publication on a call to a Spring Data repository's save methods.
     *
     * @param event must not be null.
     * @return the event that has been added.
     * @see .andEvent
     */
    protected fun <T : Any> registerEvent(event: T): T {
        domainEvents.add(event)
        return event
    }

    /**
     * Clears all domain events currently held. Usually invoked by the infrastructure in place in Spring Data
     * repositories.
     */
    @AfterDomainEventPublication
    protected fun clearDomainEvents() {
        domainEvents.clear()
    }

    /**
     * All domain events currently captured by the aggregate.
     */
    @DomainEvents
    protected fun domainEvents(): Collection<Any> {
        return domainEvents.toList()
    }

    /**
     * Adds all events contained in the given aggregate to the current one.
     *
     * @param aggregate must not be null.
     * @return the aggregate
     */
    protected fun andEventsFrom(aggregate: A): A {
        domainEvents.addAll(aggregate.domainEvents())

        @Suppress("UNCHECKED_CAST")
        return this as A
    }

    /**
     * Adds the given event to the aggregate for later publication when calling a Spring Data repository's save-method.
     * Does the same as [.registerEvent] but returns the aggregate instead of the event.
     *
     * @param event must not be null.
     * @return the aggregate
     * @see .registerEvent
     */
    protected fun andEvent(event: Any): A {
        registerEvent(event)

        @Suppress("UNCHECKED_CAST")
        return this as A
    }
}

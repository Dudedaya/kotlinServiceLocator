package service.foo

import service.Service

interface FooService : Service {
    companion object : Service.Key<FooService>

    override val key: Service.Key<*>
        get() = FooService

    fun foo()
}
package service.baz

import service.Service

interface BazService : Service {
    companion object : Service.Key<BazService>

    override val key: Service.Key<*>
        get() = BazService

    fun baz()
}
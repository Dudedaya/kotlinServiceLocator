package service.bar

import service.Service

interface BarService : Service {
    companion object : Service.Key<BarService>

    override val key: Service.Key<*>
        get() = BarService

    fun bar()
}
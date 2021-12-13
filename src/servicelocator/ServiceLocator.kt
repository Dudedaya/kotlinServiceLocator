package servicelocator

import service.Service
import java.lang.ref.WeakReference

interface ServiceLocator {
    operator fun <T : Service> get(key: Service.Key<T>): T
    fun <T : Service> getOrNull(key: Service.Key<T>): T?

    companion object {
        private var instanceRef: WeakReference<ServiceLocator>? = null
        private val INSTANCE: ServiceLocator
            get() {
                synchronized(this) {
                    if (instanceRef?.get() == null) {
                        instanceRef = WeakReference(ServiceLocatorImpl())
                    }
                }
                return instanceRef?.get()!!
            }

        operator fun <T : Service> get(key: Service.Key<T>): T = INSTANCE[key]
        fun <T : Service> getOrNull(key: Service.Key<T>): T? = INSTANCE.getOrNull(key)
    }
}
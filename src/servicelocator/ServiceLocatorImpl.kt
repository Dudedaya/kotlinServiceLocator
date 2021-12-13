package servicelocator

import service.Service
import service.bar.BarServiceImpl
import service.baz.BazServiceImpl
import service.foo.FooServiceImpl

@Suppress("UNCHECKED_CAST")
class ServiceLocatorImpl : ServiceLocator {
    /**
     * Just for example.
     * Loading and unloading services should be done in runtime to
     * get benefit from this pattern.
     */
    private val map = listOf(
        FooServiceImpl(),
        BarServiceImpl(),
//        BazServiceImpl() - not added to showcase getOrNull
    ).associateBy { it.key }

    override fun <T : Service> get(key: Service.Key<T>): T = map[key] as T
    override fun <T : Service> getOrNull(key: Service.Key<T>): T? = map[key] as? T
}
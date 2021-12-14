# Kotlin Service Locator

Idiomatic way to implement Service Locator pattern in Kotlin

Inspired by kotlinx.coroutines

## Usage:

### Loaded services:

```kotlin
ServiceLocator[FooService].foo()  
ServiceLocator[BarService].bar()  
```

#### output:

```
foo
bar
```

### Not loaded service:

```kotlin
try {  
    ServiceLocator[BazService].baz()  
} catch (ex: Exception) {  
    println("BazService is not loaded")  
}  
ServiceLocator.getOrNull(BazService)?.baz() ?: println("BazService is null")
```  

#### output:

```
BazService is not loaded  
BazService is null
```

## Implementation:

#### ServiceLocator

```kotlin
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
```

#### ServiceLocatorImpl

```kotlin
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
```

#### Service

```kotlin
interface Service {
    interface Key<T : Service>

    val key: Key<*>
}
```

#### FooService

```kotlin
interface FooService : Service {
    companion object : Service.Key<FooService>

    override val key: Service.Key<*>
        get() = FooService

    fun foo()
}
```

#### FooServiceImpl

```kotlin
class FooServiceImpl : FooService {
    override fun foo() {
        println("foo")
    }
}
```
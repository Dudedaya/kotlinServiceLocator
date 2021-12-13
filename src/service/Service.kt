package service

interface Service {
    interface Key<T : Service>

    val key: Key<*>
}
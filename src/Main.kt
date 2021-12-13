import service.bar.BarService
import service.baz.BazService
import service.foo.FooService
import servicelocator.ServiceLocator

fun main() {
    ServiceLocator[FooService].foo()
    ServiceLocator[BarService].bar()
    try {
        ServiceLocator[BazService].baz()
    } catch (ex: Exception) {
        println("BazService is not loaded")
    }
    ServiceLocator.getOrNull(BazService)?.baz() ?: println("BazService is null")
}

// output:
//
// foo
// bar
// BazService is not loaded
// BazService is null
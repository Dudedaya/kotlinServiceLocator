# Kotlin Service Locator
Idiomatic way to implement Service Locator pattern in Kotlin

## Usage:

### Loaded services:

  ```
ServiceLocator[FooService].foo()  
ServiceLocator[BarService].bar()  
```

#### output:

  ```
foo
bar
```

### Not loaded service:

```
try {  
    ServiceLocator[BazService].baz()  
} catch (ex: Exception) {  
    println("BazService is not loaded")  
}  
ServiceLocator.getOrNull(BazService)?.baz() ?: println("BazService is null")
```  

#### output:

  ```
foo  
bar  
BazService is not loaded  
BazService is null
```
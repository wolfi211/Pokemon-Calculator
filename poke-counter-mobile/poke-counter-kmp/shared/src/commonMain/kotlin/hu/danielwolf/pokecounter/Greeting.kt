package hu.danielwolf.pokecounter

class Greeting {
  private val platform = getPlatform()

  fun greet(): String {
    return sayHello(platform.name)
  }
}
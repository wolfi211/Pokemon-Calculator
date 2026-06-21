package hu.danielwolf.pokecounter

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
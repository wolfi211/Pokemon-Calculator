package hu.danielwolf.pokeCounter.domain.model

/**
 * Hibernate mutates collection backing sets using element [hashCode]. Kotlin `data class`
 * includes lazy associations in [hashCode], which triggers nested collection loads during
 * [org.hibernate.collection.spi.PersistentSet.injectLoadedState] and causes
 * [ConcurrentModificationException] on Hibernate 7.
 *
 * Use [persistenceHashCode] from entity [hashCode] when the PK may still be `0` before flush.
 */
fun Any.persistenceHashCode(id: Int): Int =
  if (id == 0) {
    System.identityHashCode(this)
  } else {
    id
  }

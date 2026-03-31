package sabotage.lib

trait CapabilityCompanion[T]:
  inline def get(using e: T) = e

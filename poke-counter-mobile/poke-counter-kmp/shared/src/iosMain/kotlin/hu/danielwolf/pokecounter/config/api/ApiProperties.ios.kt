package hu.danielwolf.pokecounter.config.api

import platform.Foundation.NSProcessInfo

private const val DEV_MAC_LAN_HOST = "192.168.0.83"

actual fun createApiProperties(): ApiProperties {
  val isSimulator = NSProcessInfo.processInfo.environment["SIMULATOR_DEVICE_NAME"] != null
  val host = if (isSimulator) "localhost" else DEV_MAC_LAN_HOST
  return ApiProperties(baseUrl = "http://$host:8080")
}

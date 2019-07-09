package utils

object DiscoveryData {
    const val PORT = 5150
    const val HOST = "192.168.1.113"

    private const val DISCOVERY_BASE_PATH = "/discovery"
    const val DISCOVERY_PUBLISH_SERVICE = "$DISCOVERY_BASE_PATH/publish"
    const val DISCOVERY_UNPUBLISH_SERVICE = "$DISCOVERY_BASE_PATH/unpublish/:serviceRegistration"
    const val DISCOVERY_GET_SERVICE = "$DISCOVERY_BASE_PATH/discover/:serviceName"
}
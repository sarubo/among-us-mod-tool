package sarubo.json

import kotlinx.serialization.Serializable

@Serializable
data class RawJsonData(
    val whiteList: Set<String>? = null,
    val modNameAndFolderName: Map<String, String?>? = null
) {
    fun toNonNull() = NonNullJsonData(this)
}

package sarubo.json

class NonNullJsonData(rawJsonData: RawJsonData) {
    val whiteList: Set<String> = rawJsonData.whiteList ?: emptySet()
    val modNameAndFolderName: Map<String, String> = rawJsonData.modNameAndFolderName?.mapValues { it.value ?: "" } ?: emptyMap()
    override fun toString(): String {
        return "vanillaFileAndFolder=$whiteList, modNameAndFolderName=$modNameAndFolderName"
    }
}
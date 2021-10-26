package sarubo

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sarubo.json.NonNullJsonData
import sarubo.json.RawJsonData
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

private fun loadJson(): NonNullJsonData =
    File("config.json")
        .reader(Charsets.UTF_8)
        .readText()
        .let { Json.decodeFromString<RawJsonData>(it).toNonNull() }

private fun help() = println("""
    使用方法:
        java -jar <このjarfile> <コマンド>
        このjarfileとconfig.jsonがあるフォルダで実行してください。
    
    コマンドは次のとおりです:
        v もしくは vanilla
            これはconfig.jsonがあれば、config.jsonのwhiteListにあるファイルやフォルダ以外をコマンドを打ったフォルダから消します。
        <modName>
            <modName>はconfig.jsonのmodNameAndFolderNameのtableのkeyにあたる場所に登録した文字列です。
            modをmodsフォルダから取り出し、コマンドを打ったフォルダに展開します。
""".trimIndent())

fun main(args: Array<String>) {
    val config = try {
        loadJson()
    } catch (e: Exception) {
        println("jsonの読み込みに失敗しました。")
        return
    }
    val type = args.getOrNull(0) ?: return help()
    if (type == "vanilla" || type == "v") {
        val whiteList = config.whiteList.map { Paths.get("./$it") }
        val here = Paths.get("./")
        Files.list(here)
            .filter { !whiteList.contains(it) }
            .forEach {
                it.toFile().deleteRecursively().let { isSuccess ->
                    if (!isSuccess) {
                        println("fail delete")
                    }
                }
            }
    } else {
        val modFolderPath = config.modNameAndFolderName[type]?.let { Paths.get("./mods/$it") }?: return help()
        Files.list(modFolderPath).forEach {
            val target = Paths.get("./${it.fileName}").toFile()
            it.toFile().copyRecursively(target, overwrite = true).let { isSuccess ->
                if (!isSuccess) {
                    println("fail delete")
                }
            }
        }
    }
}
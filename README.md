# among-us-mod-tool

## 何であるか
私用のAmongUsのmod管理ツールです。あんまり他人が使うことを考えてないです。こんなん使うくらいならPythonとかでScript書いた方が絶対にいい。

## 準備
* java8以上で動くはず(java8では動作未確認。java11(Ubuntu21.04), java17(Windows10)での動作確認済)
* `config.json` ファイルと `mods` フォルダを `AmongUs` フォルダ内に作成しておく
* modのzipファイルを展開したフォルダを `mods` においておく

### `config.json` の記述について
```
{
  "whiteList": [
    "among-us-mod-tool-0.0.1.jar",
    "config.json",
    "mods"
  ],
  "modNameAndFolderName": {
    "my-mod": "my-mod-1.0.0"
  }
}
```

上記のようになる
* `whiteList` には `AmongUs` フォルダ内で削除して欲しくないファイルとフォルダを記述すること。
* `modNameAndFolderName` にはコマンドで使う名前をkeyに記述する。 `mods` フォルダ内のmodのフォルダ名を値として記述する。

## 使用方法
```
java -jar <このjarfile> <コマンド>
```
このjarfileと `config.json` があるフォルダで実行してください。

コマンドは次のとおりです:

1. `v` もしくは `vanilla`: これは `config.json` があれば、 `config.json` の `whiteList` にあるファイルやフォルダ以外をコマンドを打ったフォルダから消します。
2. `<modName>`: `<modName>` は `config.json` の `modNameAndFolderName` のtableのkeyにあたる場所に記述した文字列です。
modを `mods` フォルダから取り出し、コマンドを打ったフォルダに展開します。

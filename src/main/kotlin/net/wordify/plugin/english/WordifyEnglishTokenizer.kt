package net.wordify.plugin.english

import net.wordify.api.WordifyTokenizer

class WordifyEnglishTokenizer : WordifyTokenizer {
    override fun getLang(): String = Consts.LANG_CODE

    override fun parse(rawText: String): MutableIterator<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

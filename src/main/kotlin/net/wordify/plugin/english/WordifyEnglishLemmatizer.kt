package net.wordify.plugin.english

import net.wordify.api.WordifyLemmatizer

class WordifyEnglishLemmatizer : WordifyLemmatizer {
    override fun getLang(): String = Consts.LANG_CODE

    override fun extract(p0: MutableIterator<String>?): MutableIterator<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

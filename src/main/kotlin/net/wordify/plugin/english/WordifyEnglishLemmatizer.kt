package net.wordify.plugin.english

import net.wordify.api.WordifyLemmatizer

class WordifyEnglishLemmatizer(private val dict: LemmatizerDict) : WordifyLemmatizer {
    override fun getLang(): String = Consts.LANG_CODE

    override fun extract(tokens: MutableIterator<String>): Iterator<String> {
        return tokens.asSequence().map { token ->
            dict.lemmatize(token) ?: token
        }.iterator()
    }
}

package net.wordify.plugin.english

import net.wordify.api.WordifyLemmatizer

class WordifyEnglishLemmatizer : WordifyLemmatizer {
    override fun getLang(): String = Consts.LANG_CODE

    override fun extract(tokens: MutableIterator<String>): Iterator<String> {
        // TODO: add super logic to extract lemma
        return tokens.asSequence().iterator()
    }
}

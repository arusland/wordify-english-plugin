package net.wordify.plugin.english

import net.wordify.api.WordifyTokenizer

class WordifyEnglishTokenizer : WordifyTokenizer {
    private val splitRegexEn = Regex("[^a-z\\d\\-']+")

    override fun getLang(): String = Consts.LANG_CODE

    override fun parse(rawText: String): Iterator<String> {
        return rawText.toLowerCase()
                .split(splitRegexEn)
                .filter { isLegalToken(it) }
                .iterator()
    }

    /**
     * Some token is represented as word if one contains at least one non-digit character
     */
    override fun isLegalToken(token: String): Boolean {
        if (token.isEmpty()){
            return false
        }

        val first = token[0]

        if (token.length == 1) {
            return first == 'a' || first == 'i'
        }

        if (!first.isLetter()) {
            return false
        }

        for (ch in token) {
            if (ch.isLetter()) {
                return true
            }
        }

        return false
    }
}

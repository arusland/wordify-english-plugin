package net.wordify.plugin.english

import net.wordify.api.WordifyTokenizer

class WordifyEnglishTokenizer : WordifyTokenizer {
    private val splitRegexEn = Regex("[^a-z\\d\\-]+")

    override fun getLang(): String = Consts.LANG_CODE

    override fun parse(rawText: String): Iterator<String> {
        return rawText.toLowerCase()
                .split(splitRegexEn)
                .filter { it.length > 1 }
                .filter { isLegalToken(it) }
                .iterator()
    }

    /**
     * Some token is represented as word if one contains at least one non-digit character
     */
    override fun isLegalToken(token: String): Boolean {
        if (token[0].isDigit()) {
            return false
        }

        for (ch in token) {
            if (!(ch.isDigit() || ch == '-')) {
                return true
            }
        }

        return false
    }
}

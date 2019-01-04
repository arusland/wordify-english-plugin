package net.wordify.plugin.english

import java.lang.RuntimeException

class LemmatizerDict() {
    private val items: Map<String, List<DictItem>>
    private val irregVerbs: Set<String>

    init {
        this.items = javaClass.classLoader
                .getResourceAsStream("dict/en-lemmatizer.dict")
                .bufferedReader()
                .lineSequence()
                .map { parseLine(it) }
                .groupBy { it.word }
        this.irregVerbs = javaClass.classLoader.getResourceAsStream("dict/english_irregular_verbs.txt")
                .bufferedReader()
                .lineSequence()
                .toSet()
    }

    fun lemmatize(word: String): String? {
        val found = items[word]

        if (found != null) {
            val noun = found.firstOrNull { it.tag == "NN" }

            if (noun != null) {
                return noun.lemma
            }

            if (irregVerbs.contains(word)) {
                return word
            }

            return found.first().lemma
        }

        if (irregVerbs.contains(word)) {
            return word
        }

        return null
    }

    private inline fun parseLine(line: String): DictItem {
        val parts = line.split('\t')

        if (parts.size != 3) {
            throw RuntimeException("Invalid format of line: '$line'")
        }

        return DictItem(word = parts[0],
                lemma = parts[2],
                tag = parts[1])
    }
}

data class DictItem(val word: String,
                    val tag: String,
                    val lemma: String)

package net.wordify.plugin.english

import java.lang.RuntimeException

class LemmatizerDict() {
    val items: List<DictItem>

    init {
        val lines = ArrayList<DictItem>()

        javaClass.classLoader.getResourceAsStream("dict/en-lemmatizer.dict")
                .reader()
                .forEachLine { line ->
                    lines.add(parseLine(line))
                }

        this.items = lines
    }

    fun lemmatize(word: String) : String? {
        val found = items.filter { it.word == word }.toList()

        if (found.isNotEmpty()) {

            val noun = found.firstOrNull { it.tag == "NN" }

            if (noun != null) {
                return noun.lemma
            }

            return found.first().lemma
        }

        return null
    }

    private fun parseLine(line: String): DictItem {
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

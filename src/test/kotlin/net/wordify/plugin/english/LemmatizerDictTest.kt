package net.wordify.plugin.english

import org.junit.jupiter.api.Test

class LemmatizerDictTest {
    @Test
    fun testInit() {
        val dict = LemmatizerDict()

        println("Loaded dict: ${dict.items.size}")

        dict.items.filter { it.word == "anus" }.forEach { println(it) }

        println()

        dict.items.filter { it.word == "works" || it.word == "work" }.forEach { println(it) }

        println()

        dict.items.filter { it.tag == "VBG" && !it.word.endsWith("ing") }
                .forEachIndexed { index, item -> println("$index: $item") }
    }

    @Test
    fun testLemmatizeSample() {
        val dict = LemmatizerDict()
        val tokenizer = WordifyEnglishTokenizer()
        val words = tokenizer.parse(sample1).asSequence().toList()

        val lastTime = System.currentTimeMillis()

        words.forEach { word ->
            println("$word:\t\t" + dict.lemmatize(word))
        }
        val elapsed = System.currentTimeMillis() - lastTime

        println("${words.size} words parsed in $elapsed ms")
    }

    companion object {
        const val sample1 = """I sat down on the side of the bed, and commenced thinking about this
head-peddling harpooneer, and his door mat. After thinking some time on
the bed-side, I got up and took off my monkey jacket, and then stood in
the middle of the room thinking. I then took off my coat, and thought a
little more in my shirt sleeves. But beginning to feel very cold now,
half undressed as I was, and remembering what the landlord said about
the harpooneer’s not coming home at all that night, it being so very
late, I made no more ado, but jumped out of my pantaloons and boots,
and then blowing out the light tumbled into bed, and commended myself
to the care of heaven."""
    }
}

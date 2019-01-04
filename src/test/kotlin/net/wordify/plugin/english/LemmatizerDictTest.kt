package net.wordify.plugin.english

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LemmatizerDictTest {
    @Test
    fun `Lemmatizing irregular verb returns the same word`() {
        val dict = LemmatizerDict()
        val verbs = javaClass.classLoader
                .getResourceAsStream("dict/english_irregular_verbs.txt")
                .bufferedReader()
                .lineSequence()
                .toSet()

        val lastTime = System.currentTimeMillis()

        verbs.forEach { verb ->
            assertEquals(verb, dict.lemmatize(verb))
        }
        val elapsed = System.currentTimeMillis() - lastTime

        println("${verbs.size} verbs parsed in $elapsed ms")
    }

    @Test
    fun `Lemmatize real sample`() {
        val dict = LemmatizerDict()
        val tokenizer = WordifyEnglishTokenizer()
        val words = tokenizer.parse(sample1).asSequence().toList()

        val lastTime = System.currentTimeMillis()

        words.forEach { word ->
            val lemma = dict.lemmatize(word)
            println("$word:\t\t$lemma")
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

package net.wordify.plugin.english

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WordifyEnglishTokenizerTest {
    @Test
    fun `Test skipping non-legal tokens`() {
        val tokenizer = WordifyEnglishTokenizer()
        val words = tokenizer.parse("What's up! Today i have 2 weapons: AK-47 and 3-BFG")
                .asSequence()
                .toList()

        assertEquals(words, listOf("what", "up", "today", "have", "weapons", "ak-47", "and"))
    }
}
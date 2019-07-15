package com.oocl;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class WordFrequencyTest {
    @Test
    public void test() {
        assertThat(WordFrequency.process("")).isEqualTo("");
        assertThat(WordFrequency.process("apple")).isEqualTo("apple 1");
        assertThat(WordFrequency.process("apple pie pie")).isEqualTo("pie 2\r\napple 1");
        assertThat(WordFrequency.process("apple pie   pie")).isEqualTo("pie 2\r\napple 1");
    }
}

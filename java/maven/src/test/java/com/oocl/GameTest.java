package com.oocl;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void testJudge() {
        Game game = new Game();
        assertThat(game.judge("1234", "5678"), equalTo("0A0B"));
        assertThat(game.judge("1234", "1234"), equalTo("4A0B"));
        assertThat(game.judge("1234", "4321"), equalTo("0A4B"));
        assertThat(game.judge("1234", "2134"), equalTo("2A2B"));
    }

    @Test
    public void testGenerateSecret() {
        Game game = new Game();
        List<String> secrets = generateSecret(game, 10);
        assertThat(secrets.get(0).length(), equalTo(4));
        assertThatSecretsNotRepeat(secrets);
    }

    private void assertThatSecretsNotRepeat(List<String> secrets) {
        assertThat(secrets.stream().collect(Collectors.toSet()).size(), equalTo(secrets.size()));
    }

    private List<String> generateSecret(Game game, int max) {
        return IntStream.rangeClosed(1, max).mapToObj(i -> game.generateSecret()).collect(toList());
    }
}


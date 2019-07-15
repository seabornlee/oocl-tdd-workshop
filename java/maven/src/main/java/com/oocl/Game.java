package com.oocl;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private int leftRetryCount = 6;

    public void start() {
        String secret = generateSecret();

        while (true) {
            if (isGameOver()) {
                showGameOver();
                break;
            }

            String userAnswer = askForAnswer();
            if (hasWon(secret, userAnswer)) {
                congratsPlayer();
                break;
            }

            String xAyB = judge(secret, userAnswer);
            printResult(xAyB);

            reduceRetryCount();
        }
    }

    private void showGameOver() {
        System.out.println("Game Over!");
    }

    private void printResult(String xAyB) {
        System.out.println(xAyB);
    }

    private void reduceRetryCount() {
        this.leftRetryCount--;
    }

    private void congratsPlayer() {
        System.out.println("Congratulations!");
    }

    private boolean hasWon(String secret, String answer) {
        return answer.equals(secret);
    }

    public String judge(String secret, String answer) {
        int hitCount = getMatchedNumberCount(secret, answer);
        int x = getXCount(secret, answer);
        int y = hitCount - x;
        return x + "A" + y + "B";
    }

    private int getXCount(String secret, String answer) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == secret.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    private int getMatchedNumberCount(String secret, String answer) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (secret.contains(answer.substring(i, i + 1))) {
                count++;
            }
        }

        return count;
    }

    private String askForAnswer() {
        System.out.println("Please input your answer:");
        return new Scanner(System.in).nextLine();
    }

    private boolean isGameOver() {
        return this.leftRetryCount == 0;
    }

    public String generateSecret() {
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder builder = new StringBuilder("0123456789");
        for (int i = 0; i < 4; i++) {
            int index = getRandomNumber(builder.length());
            resultBuilder.append(builder.charAt(index));
            builder.deleteCharAt(index);
        }
        return resultBuilder.toString();
    }

    private int getRandomNumber(int max) {
        return new Random().nextInt(max - 1);
    }
}

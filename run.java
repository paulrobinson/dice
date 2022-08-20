///usr/bin/env jbang "$0" "$@" ; exit $?


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.System.*;

public class run {

    private static final Random dice = new Random(System.currentTimeMillis());
    private static final int roleCount = 100000;
    private static final int diceSize = 6;
    private static final boolean changeDicetoFour = true;
    private static Map<Integer, Integer> results;

    public static void main(String... args) {

        init();

        for (int i=0; i<roleCount; i++) {
            int role1 = rollDice();
            int role2 = rollDice();

            if (changeDicetoFour) {
                if (role1 < 4) {
                    role1 = 4;
                } else if (role2 < 4) {
                    role2 = 4;
                }
            }
            
            int total = role1 + role2;
            logResult(total);
        }

        printResults();
    }

    private static void init() {
        results = new HashMap<>();
        for (int i=2; i<=diceSize*2; i++) {
            results.put(i, 0);
        }
    }

    private static int rollDice() {
        return dice.nextInt(diceSize) +1;
    }

    private static void logResult(int result) {
        int currentCount = results.get(result);
        currentCount++;
        results.put(result, currentCount);
    }

    private static void printResults() {
        for (int i=2; i<=diceSize*2; i++) {
            out.println(i + "," + results.get(i));
        }
    }
}

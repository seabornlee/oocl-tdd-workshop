package com.oocl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordFrequency {
    public static String process(String text) {
        if (text.isEmpty()) {
            return text;
        }

        String[] array = split(text);
        Map<String, Long> map = group(array);
        Map<String, Long> sortedMap = sort(map);
        return format(sortedMap);
    }

    private static String[] split(String text) {
        return text.split("\\s+");
    }

    private static Map<String, Long> group(String[] array) {
        return asList(array).stream().collect(groupingBy(identity(), counting()));
    }

    private static Map<String, Long> sort(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static String format(Map<String, Long> sortedMap) {
        return sortedMap.entrySet().stream().map((Map.Entry<String, Long> entry) -> entry.getKey() + " " + entry.getValue()).collect(Collectors.joining("\r\n"));
    }
}

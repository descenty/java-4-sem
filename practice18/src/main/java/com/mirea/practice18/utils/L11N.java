package com.mirea.practice18.utils;

import java.util.HashMap;
import java.util.Map;

public class L11n {
    public static Map<String, String> abc = new HashMap<>();

    static {
        abc.put("а", "a");
        abc.put("б", "b");
        abc.put("в", "v");
        abc.put("г", "g");
        abc.put("д", "d");
        abc.put("е", "e");
        abc.put("ё", "e");
        abc.put("ж", "zh");
        abc.put("з", "z");
        abc.put("и", "i");
        abc.put("й", "y");
        abc.put("к", "k");
        abc.put("л", "l");
        abc.put("м", "m");
        abc.put("н", "n");
        abc.put("о", "o");
        abc.put("п", "p");
        abc.put("р", "r");
        abc.put("с", "s");
        abc.put("т", "t");
        abc.put("у", "u");
        abc.put("ф", "f");
        abc.put("х", "h");
        abc.put("ц", "ts");
        abc.put("ч", "ch");
        abc.put("ш", "sh");
        abc.put("щ", "sch");
        abc.put("ъ", "");
        abc.put("ы", "i");
        abc.put("ь", "");
        abc.put("э", "e");
        abc.put("ю", "ju");
        abc.put("я", "ja");
        abc.put("А", "A");
        abc.put("Б", "B");
        abc.put("В", "V");
        abc.put("Г", "G");
        abc.put("Д", "D");
        abc.put("Е", "E");
        abc.put("Ё", "E");
        abc.put("Ж", "Zh");
        abc.put("З", "Z");
        abc.put("И", "I");
        abc.put("Й", "Y");
        abc.put("К", "K");
        abc.put("Л", "L");
        abc.put("М", "M");
        abc.put("Н", "N");
        abc.put("О", "O");
        abc.put("П", "P");
        abc.put("Р", "R");
        abc.put("С", "S");
        abc.put("Т", "T");
        abc.put("У", "U");
        abc.put("Ф", "F");
        abc.put("Х", "H");
        abc.put("Ц", "Ts");
        abc.put("Ч", "Ch");
        abc.put("Ш", "Sh");
        abc.put("Щ", "Sch");
        abc.put("Ъ", "");
        abc.put("Ы", "I");
        abc.put("Ь", "");
        abc.put("Э", "E");
        abc.put("Ю", "Ju");
        abc.put("Я", "Ja");
    }

    public static String transliterate(String message) {
        return message.chars()
                .mapToObj(c -> (char) c)
                .map(c -> abc.getOrDefault(c.toString(), c.toString()))
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
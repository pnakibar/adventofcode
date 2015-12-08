package fuckpackaging;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static boolean isPretty(String s) {
        //three vowels
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int hasThree = 3;
        for (char c: s.toCharArray())
            for (char vowel: vowels)
                if (c == vowel)
                    hasThree--;

        if (hasThree > 0) return false;

        //one letter twice
        Pattern r = Pattern.compile("(.)\\1+");
        Matcher m = r.matcher(s);
        if (!m.find()) return false;

        //does not contains ab, cd, pq, xy
        Pattern specifiedWords = Pattern.compile("(ab|cd|pq|xy)");
        if (specifiedWords.matcher(s).find()) return false;


        return true;
    }

    public static void tests() throws Exception {
        if (!isPretty("ugknbfddgicrmopn")) throw new Exception("test 1 bad");
        if (!isPretty("aaa")) throw new Exception("test 2 bad");
        if (isPretty("jchzalrnumimnmhp")) throw new Exception("test 3 bad");
        if (isPretty("haegwjzuvuyypxyu")) throw new Exception("test 4 bad");
        if (isPretty("dvszwmarrgswjxmb")) throw new Exception("test 5 bad");
    }

    public static void main(String[] args) throws Exception {
        tests();

        int qtyGood = 0;
        for (String line: Files.readAllLines(Paths.get("input.txt"))){
            if (isPretty(line))
                qtyGood++;
        }

        System.out.println(qtyGood);
    }
}

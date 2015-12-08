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


    public static boolean hasLetterInBetween(String s){
        if (s.length() == 2)
            return false;

        if (s.charAt(0) == s.charAt(2))
            return true;

        else
            return hasLetterInBetween(s.substring(1));
    }




    public static boolean appearsTwice(String s){
        if (s.length() == 1) return false;

        String pattern = ""+s.charAt(0)+s.charAt(1);

        if (s.substring(2).contains(pattern))
            return true;
        else
            return appearsTwice(s.substring(1));
    }

    public static boolean isPretty2(String s){
        return appearsTwice(s) && hasLetterInBetween(s);
    }
    public static void teste2() throws Exception {
        if(!isPretty2("qjhvhtzxzqqjkmpb")) throw new Exception("test2 1 bad");
        if(!isPretty2("xxyxx")) throw new Exception("teste2 2 bad");
        if(isPretty2("uurcxstgmygtbstg")) throw new Exception("teste2 3 bad");
        if(isPretty2("ieodomkazucvgmuy")) throw new Exception("teste2 4 bad");
    }

    public static void main(String[] args) throws Exception {
        tests();
        teste2();

        int qtyGood = 0;
        for (String line: Files.readAllLines(Paths.get("input.txt"))){
            if (isPretty(line))
                qtyGood++;
        }

        System.out.println(qtyGood);

        int qtyGood2 = 0;
        for (String line: Files.readAllLines(Paths.get("input.txt"))){
            if (isPretty2(line))
                qtyGood2++;
        }

        System.out.println(qtyGood2);
    }
}

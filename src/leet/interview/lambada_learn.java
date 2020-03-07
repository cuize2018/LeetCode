package leet.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class lambada_learn {
    public static void main(String[] args) {
        learn();
    }

    public static void learn(){
        List<String> list=new ArrayList<String >();
        list.add("o1sdsd");
        list.add("a1");
        list.add("a2");
        list.add("b1");
        list.add("b2sds");
        list.add("b3");


//        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

//        list.stream().sorted((s1, s2) -> Integer.compare(s1.length(), s2.length())).forEach(System.out::println);
//        list.stream().map(s -> s + "_1").forEach(System.out::println);

//        boolean anyMatch = list.stream().allMatch(s -> s.contains("2"));
//        System.out.println(anyMatch);

//        List<Boolean> s1 = list.stream().map(s -> s.contains("s")).collect(Collectors.toList());
//        System.out.println(s1);

    }
}

package 笔试.meituan;

import java.util.*;

public class No3 {
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<TreeSet<Integer>> homes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            homes.add(new TreeSet<>());
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            boolean successful = false;

            for (int j = 0; j < n; j++) {
                Set<Integer> home = homes.get(j);
                if (home.contains(a) || home.contains(b)){
                    home.add(a);
                    home.add(b);
                    successful = true;
                    break;
                }
            }
            if (successful){
                continue;
            }
            for (int j = 0; j < n; j++) {
                Set<Integer> home = homes.get(j);
                if (home.isEmpty()) {
                    home.add(a);
                    home.add(b);
                    break;
                }
            }
        }

        List<TreeSet<Integer>> homes2 = new ArrayList<>();
        for (TreeSet<Integer> home : homes) {
            if (!home.isEmpty()){
                homes2.add(home);
            }
        }

        homes2.sort((o1, o2) -> {
            Iterator<Integer> iterator1 = o1.iterator();
            Iterator<Integer> iterator2 = o2.iterator();
            Integer a = iterator1.next();
            Integer b = iterator2.next();
            return Integer.compare(a, b);
        });

        System.out.println(homes2.size());
        for (TreeSet<Integer> set : homes2) {
            List<Integer> temp = new ArrayList<>(set);
            for (int i = 0; i < temp.size()-1; i++) {
                System.out.print(temp.get(i) + " ");
            }
            System.out.println(temp.get(temp.size()-1));
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<TreeSet<Integer>> homes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            boolean successful = false;

            if (homes.isEmpty()){
                TreeSet<Integer> t = new TreeSet<>();
                t.add(a);
                t.add(b);
                homes.add(t);
                continue;
            }

            for (int j = 0; j < homes.size(); j++) {
                Set<Integer> home = homes.get(j);
                if (home.contains(a) || home.contains(b)){
                    home.add(a);
                    home.add(b);
                    successful = true;
                    break;
                }
            }

            if (!successful){
                TreeSet<Integer> t = new TreeSet<>();
                t.add(a);
                t.add(b);
                homes.add(t);
            }
        }
        homes.sort((o1, o2) -> {
            Iterator<Integer> iterator1 = o1.iterator();
            Iterator<Integer> iterator2 = o2.iterator();
            Integer a = iterator1.next();
            Integer b = iterator2.next();
            return Integer.compare(a, b);
        });

//        homes.sort((o1, o2) -> {
//            Iterator<Integer> iterator1 = o1.iterator();
//            Iterator<Integer> iterator2 = o2.iterator();
//            while (iterator1.hasNext() && iterator2.hasNext()){
//                Integer a = iterator1.next();
//                Integer b = iterator2.next();
//                if (a < b)return -1;
//                else if (a > b)return 1;
//            }
//            return Integer.compare(o2.size(), o1.size());
//        });

        System.out.println(homes.size());
        for (TreeSet<Integer> set : homes) {
            List<Integer> temp = new ArrayList<>(set);
            for (int i = 0; i < temp.size()-1; i++) {
                System.out.print(temp.get(i) + " ");
            }
            System.out.println(temp.get(temp.size()-1));
        }
    }
}

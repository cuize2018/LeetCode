package 笔试.meituan;

import java.util.*;

public class No4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            cars.add(new Car(x,y));
        }

        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Integer.compare(o2.aProfit, o1.aProfit);
            }
        });
        int sum = 0;
        for (int i = 0; i < a; i++) {
            sum += cars.get(i).aProfit;
        }
        List<Car> cars1 = cars.subList(a, cars.size());
        Collections.sort(cars1, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Integer.compare(o2.bProfit, o1.bProfit);
            }
        });
        for (int i = 0; i < b; i++) {
            sum += cars1.get(i).bProfit;
        }
        System.out.println(sum);
    }
}

class Car{
    int aProfit;
    int bProfit;

    public Car(int a, int b){
        aProfit = a;
        bProfit = b;
    }
}

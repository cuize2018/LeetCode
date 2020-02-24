package leet;

public class Solution537 {
    public static void main(String[] args) {
        String a = "78+-76i";
        String b = "-86+72i";
        System.out.println(complexNumberMultiply(a,b));
    }


    public static String complexNumberMultiply(String a, String b) {
        String[] a_array = a.split("\\+");
        String[] b_array = b.split("\\+");
        int real = 0;
        int fake = 0;
        for (int i = 0;i < a_array.length;i++){
            for (int j = 0;j < b_array.length;j++){
                int num1_real = 0;
                int num2_real = 0;

                String num1_fake = "";
                String num2_fake = "";

                if (a_array[i].contains("i")){
                    num1_fake = a_array[i];
                }
                else {
                    num1_real = Integer.parseInt(a_array[i]);
                }

                if (b_array[j].contains("i")){
                    num2_fake = b_array[j];
                }
                else {
                    num2_real = Integer.parseInt(b_array[j]);
                }

                if (!num1_fake.isEmpty()||!num2_fake.isEmpty()){
                    if (!num1_fake.isEmpty() && num2_fake.isEmpty()){
                        int val = cal(num2_real, num1_fake);
                        fake += val;
                    }
                    else if (num1_fake.isEmpty() && !num2_fake.isEmpty()){
                        int val = cal(num1_real, num2_fake);
                        fake += val;
                    }
                    else {
                        int val = cal_fakes(num1_fake,num2_fake);
                        real += val;
                    }
                }
                else {
                    real += num1_real*num2_real;
                }
            }
        }

        StringBuilder str = new StringBuilder();
        str.append(String.valueOf(real));
        str.append("+");
        str.append(String.valueOf(fake));
        str.append("i");
        return str.toString();
    }

    public static int cal(int real, String fake){
        int val = 0;
        String temp1 = new String(fake);
        int val1 = Integer.parseInt(temp1.replace("i","")
                .replace("-",""));

        val = val1 * real;
        if (fake.contains("-")){
            val = -val;
        }
        return val;
    }

    public static int cal_fakes(String num1_fake, String num2_fake){
        String temp1 = new String(num1_fake);
        String temp2 = new String(num2_fake);

        int val1 = Integer.parseInt(temp1.replace("i","").replace("-",""));
        int val2 = Integer.parseInt(temp2.replace("i","").replace("-",""));

        int val = val1 * val2 * -1;

        if (num1_fake.contains("-"))val=-val;
        if (num2_fake.contains("-"))val=-val;

        return val;
    }
}

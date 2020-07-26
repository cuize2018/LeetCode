package leet.interview;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeLearning {
    public static void main(String[] args) {
//        String s = byteOrder();
//        filedAddress();
        TestClass testClass = new TestClass();
        System.out.println(objectSize(testClass));

//        if(ByteOrder.nativeOrder()==ByteOrder.BIG_ENDIAN)
//            System.out.println("big endian");
//        else System.out.println("little endian");

    }

    private static Unsafe reflectionGetUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String byteOrder() {
        Unsafe unsafe = reflectionGetUnsafe();
        long address = unsafe.allocateMemory(8L);
        System.out.println("Hex Base Address: " + Long.toHexString(address));
        System.out.println("Binary Base Address: " + Long.toBinaryString(address));
        try {
            unsafe.putLong(address, 0x0102030405060708L);
            byte bytes = unsafe.getByte(address);
            if (bytes == 0x01) {
                return "大端存储";
            } else if (bytes == 0x08) {
                return "小端存储";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unsafe.freeMemory(address);
        }
        return null;
    }

    public static void filedAddress(){
        Unsafe unsafe = reflectionGetUnsafe();
        try {
            TestClass testClass = TestClass.class.newInstance();
            Field field = testClass.getClass().getDeclaredField("a");

            long base  = System.identityHashCode(testClass);
            long offset = unsafe.objectFieldOffset(field);
            System.out.println("Object Base : " + Long.toBinaryString(base));
            System.out.println("Filed Offset: " + Long.toBinaryString(offset));

            int val = unsafe.getInt(testClass, offset);
            System.out.println("Filed Value: " + val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String arrayAddress(){
        Unsafe unsafe = reflectionGetUnsafe();
        int[] arr = new int[]{1,2,3};
        long offset = unsafe.arrayBaseOffset(arr.getClass());
        int bytes = unsafe.arrayIndexScale(int[].class);//多少个字节

        System.out.println("Array First Element offset : " + Long.toBinaryString(offset));
        System.out.println("Array Element bytes : " + Long.toBinaryString(bytes));

        /*------------通过元素位置计算偏移地址------------*/
        long l = (unsafe.getInt(arr, offset + bytes*2L));
        System.out.println("The offset of element at: " + 2 + " -> " + Long.toHexString(l));
        return null;
    }

    public static int objectSize(Object o){
        Unsafe unsafe = reflectionGetUnsafe();

        Field[] declaredFields = o.getClass().getDeclaredFields();
        long max = -1;
        for (Field field : declaredFields) {
            max = Math.max(max, unsafe.objectFieldOffset(field));
        }

        int res = (int) (max + 4);
        if ((res & (res - 1)) != 0) {
            int n = res - 1;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            res = n+1;
        }
        return res;
    }
}


class TestClass{
    int a = 1;
    int b = 2;
    int c = 3;
    int e = 3;
    int f = 3;
    int g = 3;
}
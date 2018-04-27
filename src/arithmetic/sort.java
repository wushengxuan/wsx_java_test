package arithmetic;

import java.util.Random;

/**
 * 都已int数组为例
 */
public class sort {

    public static void main(String[] args) {
//        int[] test = randomArray(999999);
//        Long beginTimeStamp = System.currentTimeMillis();
//        int[] a = selectionSort(test);
//        Long endTimeStamp = System.currentTimeMillis();
//        System.out.println(endTimeStamp - beginTimeStamp);
//
//        beginTimeStamp = System.currentTimeMillis();
//        int[] b = insertionSort(test);
//        endTimeStamp = System.currentTimeMillis();
//        System.out.println(endTimeStamp - beginTimeStamp);
//
//        //希尔
//        beginTimeStamp = System.currentTimeMillis();
//        int[] c = shellSort(test);
//        endTimeStamp = System.currentTimeMillis();
//        System.out.println(endTimeStamp - beginTimeStamp);

        //归并排序
        int[] test1 = {1,3,4,5,9,2,4,6,10,11};
        int[] d = mergeSort(test1, 0, 9, 4);
        System.out.println(d);
    }

    /**
     * 选择排序(时间复杂度O(n^2))
     */
    public static int[] selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 插入排序(时间复杂度<=O(n^2))
     */
    public static int[] insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[i];
                    a[i] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 希尔排序(时间复杂度未知)
     */
    public static int[] shellSort(int[] a) {
        int h = 1;
        while (h <= a.length / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j > h; j -= h) {
                    if (a[j - h] > a[j]) {
                        int temp = a[j - h];
                        a[j - h] = a[i];
                        a[i] = temp;
                    }
                }
            }
            h = (h - 1) / 3;
        }

        return a;
    }

    /**
     * 归并排序(时间复杂度log2N*N)
     */
    public static int[] mergeSort(int[] a, int low, int high, int mid) {
        int i = low;
        int j = mid + 1;
        int[] copy = new int[high - low];
        for (int k = 0; k < copy.length; k++) {
            if(j > high) {
                //右边数组用完把左边剩余的数移入数组
                copy[k] = a[i++];
            } else if(i > mid) {
                //左边数组用完把右边剩余的数移入数组
                copy[k] = a[j++];
            } else if(a[i] > a[j]) {
                copy[k] = a[j++];
            } else {
                copy[k] = a[i++];
            }
        }
        return copy;
    }

    /**
     * 生成随机大小数组
     */
    private static int[] randomArray(int num) {
        Random rand = new Random();
        int[] a = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = rand.nextInt(num);
        }
        return a;
    }
}
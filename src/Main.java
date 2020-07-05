import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static int[] readArrayFromKeyboard() {

        System.out.println("Enter the number of elements in the array:");
        int size = Integer.parseInt(scanner.nextLine());

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.println("Enter an item with an index " + i);
            int number = Integer.parseInt(scanner.nextLine());
            arr[i] = number;
        }

        return arr;
    }

    public static List<Integer> readListFromKeyboard() {

        System.out.println("Enter the number of items in the list");
        int size = Integer.parseInt(scanner.nextLine());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("Enter an item with an index " + i);
            int number = Integer.parseInt(scanner.nextLine());
            list.add(number);
        }

        return list;
    }

    public static List<Integer> readListFromFile(String pathToFile) {
        try {
            File file = new File(pathToFile);
            Scanner scanner = new Scanner(file);

            List<Integer> list = new ArrayList<>();

            while (scanner.hasNext()) {
                int number = Integer.parseInt(scanner.nextLine());
                list.add(number);
            }

            return list;
        } catch (FileNotFoundException e) {
            System.out.println("There is no file at the specified path!");
            return null;
        }
    }

    public static void simpleInsertionSort(List<Integer> list) {
        int compareCount = 0;
        int exchangeCount = 0;

        int n = list.size();
        for (int i = 1; i < n; i++) {
            System.out.println("Step №" + i);
            System.out.println("List Status: " + list.toString());
            int key = list.get(i);
            int j = i - 1;

            while (true) {
                if (j >= 0) {
                    System.out.println("Compare " + key + " with " + list.get(j));
                    if (list.get(j) > key) {
                        compareCount++;

                        System.out.println(key + " less than " + list.get(j) + ", make a permutation");

                        list.set(j + 1, list.get(j));
                        j = j - 1;

                        exchangeCount++;
                    } else {
                        System.out.println(key + " more than " + list.get(j) + ", a permutation is not required, go to the next step");

                        compareCount++;
                        break;
                    }
                } else {
                    break;
                }
            }
            list.set(j + 1, key);
            System.out.println();
        }

        System.out.println("The number of items in the list = " + list.size());
        System.out.println("Number of comparisons = " + compareCount);
        System.out.println("Number of exchanges = " + exchangeCount);
        System.out.println();
        System.out.println("Sorted by simple inserts list: " + list.toString());
    }

    public static int binarySearch(int arr[], int item, int low, int high) {
        if (high <= low)
            return (item > arr[low]) ? (low + 1) : low;
        int mid = (low + high) / 2;
        if (item == arr[mid])
            return mid + 1;
        if (item > arr[mid])
            return binarySearch(arr, item, mid + 1, high);
        return binarySearch(arr, item, low, mid - 1);
    }

    public static void binaryInsertionSort(int arr[]) {
        int compareCount = 0;
        int exchangeCount = 0;

        int i, loc, j, key;
        for (i = 1; i < arr.length; ++i) {
            System.out.println("Step №" + i);
            System.out.println("Array Status: " + Arrays.toString(arr));
            j = i - 1;
            key = arr[i];
            loc = binarySearch(arr, key, 0, j);

            compareCount++;

            while (j >= loc) {

                System.out.println(arr[j + 1] + " less than " + arr[j] + ", make a permutation");

                arr[j + 1] = arr[j];
                j--;

                compareCount++;
                exchangeCount++;
            }
            arr[j + 1] = key;
            System.out.println();
        }

        System.out.println("The number of elements in the array = " + arr.length);
        System.out.println("Number of comparisons = " + compareCount);
        System.out.println("Number of exchanges = " + exchangeCount);
        System.out.println();
        System.out.println("Array sorted by binary inserts: " + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        List<Integer> list = readListFromFile("res/numbers.txt");
        System.out.println("List of numbers from a file:");
        System.out.println(list);

        System.out.println();
        simpleInsertionSort(list);

        System.out.println();

        int[] arr = readArrayFromKeyboard();
        System.out.println("Array entered from the keyboard:");
        System.out.println(Arrays.toString(arr));
        System.out.println();

        binaryInsertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}

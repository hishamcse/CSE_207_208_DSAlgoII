import java.util.Arrays;

public class Array {

    private static final int PREDEFINED_SIZE = 20;

    private String[] customItems;
    private int pointer;

    // constructors
    public Array() {
        customItems = new String[PREDEFINED_SIZE];
        pointer = 0;
    }

    public Array(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative Size");
        customItems = new String[n];
        pointer = 0;
    }

    public Array(String[] items) {
        if (items == null) throw new IllegalArgumentException("argument is null");
        this.customItems = items;
        pointer = items.length;
    }

    // private helper methods
    private String[] resizedArray() {
        String[] newArray = new String[customItems.length + 1];
        System.arraycopy(customItems, 0, newArray, 0, customItems.length);
        return newArray;
    }

    private String[] resizedArray(int increase, int splitter_id) {
        String[] newArray = new String[customItems.length + increase];
        System.arraycopy(customItems, 0, newArray, 0, splitter_id);
        System.arraycopy(customItems, splitter_id, newArray, splitter_id + 1, newArray.length - splitter_id - 1);
        return newArray;
    }

    private int frequency(String element) {
        int count = 0;
        for (int i = 0; i < pointer; i++) {
            if (customItems[i].equals(element)) {
                count++;
            }
        }
        return count;
    }

    // public methods
    public String[] getArray() {
        return customItems;
    }

    public String getAnElement(int i) {
        if (i < 0 || i >= customItems.length) {
            throw new IllegalArgumentException("Index Out Of Bound");
        }
        return customItems[i];
    }

    public void add(String element) {
        if (element == null) throw new IllegalArgumentException("argument is null");
        if (pointer == customItems.length) {
            customItems = resizedArray();
        }
        customItems[pointer] = element;
        pointer++;
    }

    public void add(int index, String element) {
        if (element == null) throw new IllegalArgumentException("argument is null");
        if (index < 0 || index >= customItems.length) {
            throw new IllegalArgumentException("Index Out Of Bound");
        }
        customItems = (pointer == customItems.length) ? resizedArray(1, index) : resizedArray(0, index);
        customItems[index] = element;
        pointer++;
    }

    public void remove(String element) {
        if (element == null) throw new IllegalArgumentException("argument is null");
        int count = frequency(element);
        if (count == 0) return;
        String[] temp = customItems;
        customItems = new String[customItems.length - count];
        int j = 0;
        for (int i = 0; i < pointer; i++) {
            if (temp[i].equals(element)) continue;
            customItems[j++] = temp[i];
        }
        pointer -= count;
    }

    public int[] findIndex(String element) {
        if (element == null) throw new IllegalArgumentException("argument is null");
        int count = frequency(element);
        if (count == 0) return new int[]{};
        int[] required_indices = new int[count];
        int j = 0;
        for (int i = 0; i < pointer; i++) {
            if (customItems[i].equals(element)) {
                required_indices[j++] = i;
            }
        }
        return required_indices;
    }

    public String[] subArray(int start, int end) {
        if (start < 0 || start >= customItems.length || end < 0 || end > customItems.length) {
            throw new IllegalArgumentException("Invalid Range");
        }
        return Arrays.copyOfRange(customItems, start, end);
    }

    public void merge(String[] arr1, String[] arr2) {
        int size_arr1 = arr1.length;
        int size_arr2 = arr2.length;
        customItems = new String[size_arr1 + size_arr2];
        int i = 0, j = 0, k = 0;
        do {
            if (arr1[i].compareTo(arr2[j]) <= 0) {
                customItems[k++] = arr1[i++];
            } else {
                customItems[k++] = arr2[j++];
            }
        } while (i != size_arr1 && j != size_arr2);

        while (i < size_arr1) {
            customItems[k++] = arr1[i++];
        }
        while (j < size_arr2) {
            customItems[k++] = arr2[j++];
        }
        pointer = k;
    }

    public int length() {
        return customItems.length;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    // testing
    public static void main(String[] args) {

        // Test 1 (using constructor(string[] A))
        System.out.println("---------------------------Test 1----------------------------------");
        Array array = new Array(new String[]{"hisham", "mihad", "hello", "hisham", "mihad", "hello",
                "never", "change", "yourself", "just", "keep", "growing", "never", "lose", "hope"});
        System.out.println("total length: " + array.length());
        System.out.println("empty array: " + array.isEmpty());
        System.out.println(Arrays.toString(array.getArray()));

        array.add("great");
        array.add("one");
        System.out.println(Arrays.toString(array.getArray()));
        System.out.println("items ranging from 6 to 8: " + Arrays.toString(array.subArray(6, 8)));

        array.add(5, "hey Mihad");
        System.out.println(Arrays.toString(array.getArray()));
        System.out.println("element at 7: " + array.getAnElement(7));

        System.out.println("total length: " + array.length());
        System.out.println("indices of never: " + Arrays.toString(array.findIndex("never")));
        System.out.println("indices of hisham: " + Arrays.toString(array.findIndex("hisham")));
        System.out.println("element at 13: " + array.getAnElement(13));

        array.remove("hope");
        array.remove("never");
        array.remove("hisham");
        System.out.println("After removing hope,never,hisham. The array is now:");
        System.out.println(Arrays.toString(array.getArray()));

        System.out.println("indices of never: " + Arrays.toString(array.findIndex("never")));
        System.out.println("indices of hisham: " + Arrays.toString(array.findIndex("hisham")));
        System.out.println("element at 6: " + array.getAnElement(6));
        array.add(5, "finishing");
        array.add("phase");
        System.out.println(Arrays.toString(array.getArray()));
        System.out.println();

        // test 2 (defining size)
        System.out.println("---------------------------Test 2----------------------------------");
        Array array1 = new Array(10);
        array1.add("hey Hisham");
        array1.add("hey Mihad");
        System.out.println("total length: " + array1.length());
        System.out.println("empty array: " + array1.isEmpty());

        array1.add("great");
        array1.add("one");
        System.out.println(Arrays.toString(array1.getArray()));
        System.out.println("total length: " + array1.length());
        System.out.println("items ranging from 1 to 3: " + Arrays.toString(array1.subArray(1, 3)));

        array1.add(2, "added");
        System.out.println("element at 3: " + array1.getAnElement(3));
        System.out.println(Arrays.toString(array1.getArray()));

        System.out.println("total length: " + array1.length());
        System.out.println("indices of great: " + Arrays.toString(array1.findIndex("great")));
        System.out.println("indices of hisham: " + Arrays.toString(array1.findIndex("hisham")));
        System.out.println("element at 3: " + array1.getAnElement(3));

        array1.remove("one");
        System.out.println("total length: " + array1.length());
        System.out.println("After removing hope,never,hisham. The array is now:");
        System.out.println(Arrays.toString(array1.getArray()));
        array1.add(2, "again");
        array1.add("again");
        System.out.println(Arrays.toString(array1.getArray()));
        System.out.println();

        // test 3 (predefined size)
        System.out.println("---------------------------Test 3----------------------------------");
        Array array2 = new Array();
        array2.add("hey Hisham");
        array2.add("hey Mihad");
        System.out.println("total length: " + array2.length());
        System.out.println("empty array: " + array2.isEmpty());

        array2.add("great");
        array2.add("one");
        System.out.println(Arrays.toString(array2.getArray()));
        System.out.println("total length: " + array2.length());
        System.out.println("items ranging from 1 to 3: " + Arrays.toString(array2.subArray(1, 3)));

        array2.add(2, "added");
        System.out.println("element at 3: " + array2.getAnElement(3));
        System.out.println(Arrays.toString(array2.getArray()));

        System.out.println("total length: " + array2.length());
        System.out.println("indices of never: " + Arrays.toString(array2.findIndex("great")));
        System.out.println("indices of hisham: " + Arrays.toString(array2.findIndex("hisham")));
        System.out.println("element at 3: " + array2.getAnElement(3));

        array2.remove("one");
        System.out.println("total length: " + array2.length());
        System.out.println("After removing one. The array is now:");
        System.out.println(Arrays.toString(array2.getArray()));
        array2.add(2, "again");
        System.out.println(Arrays.toString(array2.getArray()));
        array2.add("again");
        array2.add("one");
        System.out.println(Arrays.toString(array2.getArray()));
        System.out.println();

        // test 4 (merge array)
        System.out.println("---------------------------Test 4----------------------------------");
        array.merge(new String[]{"aaa", "aab", "baa", "caa", "ccd"},
                new String[]{"aad", "bbab", "bbac", "bbd", "cbd", "dbc"});
        System.out.println(Arrays.toString(array.getArray()));
    }
}
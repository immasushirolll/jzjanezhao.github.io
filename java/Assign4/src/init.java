private static void init(int[] a, int size) {
    for (int i = 0; i < 3; ++i) {
        ++size;
        a[i] = (1-i)* size;
    }
}

public static void main(String[] args) {
    int[] a = new int[3];
    int size = 0;
    init(a, size);
    for (int i = 1; i < size; ++i) System.out.println(a[i]);
    System.out.println(a[0]);
}
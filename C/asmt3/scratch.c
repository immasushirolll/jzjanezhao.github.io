#include <stdio.h>

int main() {
    int numIntegers;

    // Ask the user for the number of integers
    do {
        printf("Enter the number of integers (between 5 and 12): ");
        scanf("%d", &numIntegers);
    } while (numIntegers < 5 || numIntegers > 12);

    // Print the number of integers and size of the array in bytes
    printf("You provided %d integers.\n", numIntegers);

    // Create an array to store the integers
    int integerArray[12]; // Using the maximum allowed size (12)

    // Prompt the user for the list of integers
    printf("Enter %d integers separated by spaces: ", numIntegers);

    for (int i = 0; i < numIntegers; i++) {
        scanf("%d", &integerArray[i]);
    }

    // Print the size of the array in bytes
    size_t sizeInBytes = numIntegers * sizeof(int);
    printf("Size of the array: %lu bytes\n", (unsigned long)sizeInBytes);

    // Perform operations on the integers in the array (you can add your own logic here)
    printf("Integers in the array: ");
    for (int i = 0; i < numIntegers; i++) {
        printf("%d ", integerArray[i]);
    }
    printf("\n");

    return 0;
}

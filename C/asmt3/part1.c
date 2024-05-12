# include <stdio.h>

/* input: integers separated by spaces, place into array, work on integer */
int main() {
    int numInt;
    int byte;
    int array[12];
    int indices[12];

    // enter number of numbers to be processed
    printf("Please enter the number of integers to process: ");
    scanf("%d", &numInt);

    // fill array with numbers
    for (int i = 0; i < numInt; i++) {
        array[i] = 0;
        indices[i] = i;
    }

    // calculate byte size
    byte = numInt*sizeof(int);

    printf("    There is enough room in your array for %d integers (%d bytes)\n\n", numInt, byte);

    // prompt for list of numbers
    printf("Please enter your integers separated by spaces: ");

    for (int i = 0; i < numInt; i++) {
        scanf("%d", &array[i]);
    }
    printf("\n");

    // part 1: print elements of array and their position
    printf("Part 1: \n");
    printf("\tYour array is: ");
    for (int i = 0; i < numInt - 1; i++) {
        printf("[%d] = %d, ", i, array[i]);
    }
    printf("[%d] = %d\n\n", numInt-1, array[numInt-1]);

    // part 2: print elements of array in reverse order and their position
    printf("Part 2: \n");
    printf("\tYour array in reverse is: ");
    
    for (int i = numInt - 1; i >= 1; i--) {     // reverse array
        printf("[%d] = %d, ", i, array[i]);
    } 
    printf("[0] = %d\n\n", array[0]);

    // part 3: print all even elements in array and their position
    printf("Part 3: \n");
    printf("\tThe even elements in the array is: ");

    for (int i = 0; i < numInt; i++) {     // even numbers
        if (array[i] % 2 == 0) {
            printf("[%d] = %d, ", i, array[i]);
        }
    }    
    printf("\n\n");

    // part 4: print sum of all values in array
    printf("Part 4:\n");
    printf("\tThe sum of all values in your array is: ");

    int sum = 0;
    for (int i = 0; i < numInt; i++) {
        sum += array[i];
    }
    printf("%d\n\n", sum);

    // part 5: print in ascending order and their positions
    printf("Part 5:\n");
    printf("\tYour array in sorted order is: ");

    int ascend[12]; // make a copy so we don't mess with the original entries
    for (int i = 0; i < numInt; i++) {
        ascend[i] = array[i];
    }

    for (int i = 0; i < numInt; i++) {      // sort in order
        for (int j = i + 1; j < numInt; j++) {
            if (ascend[j] < ascend[i]) {
                int tmp = ascend[i];
                ascend[i] = ascend[j];
                ascend[j] = tmp;
            }
        }
    }

    for (int i = 0; i < numInt - 1; i++) {
        printf("[%d] = %d, ", i, ascend[i]);
    }
    printf("[%d] = %d\n\n", numInt-1, ascend[numInt-1]);

    // part 6: print elements of array with 1st and last element swapped and their position
    printf("Part 6:\n");
    printf("\tYour array with first and last element switched is: ");

    int first = array[0];   // rearrange first and last
    int last = array[numInt - 1];
    array[0] = last;
    array[numInt - 1] = first;

    printf("[%d] = %d, ", numInt - 1, last);
    for (int i = 1; i < numInt - 1; i++) {      // print output
        printf("[%d] = %d, ", i, array[i]);
    }
    printf("[0] = %d\n\n", first);

    return 0;

}
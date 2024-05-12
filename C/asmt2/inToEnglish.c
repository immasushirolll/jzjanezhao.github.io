#include <stdio.h>

#define TRUE 1

/* inToEnglish.c: converts any integer from 1-999 to English */
/* Jin Zhao 251138547*/
int main(void)
{
    char*underTwenty[20] = {
        "zero", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen",
        "sixteen", "seventeen", "eighteen", "nineteen"
    };

    char*tens[10] = {
        "INDEX0", "INDEX1", "twenty", "thirty", "forty", "fifty",
        "sixty", "seventy", "eighty", "ninety"
    };

    while (TRUE) {
        int input = 1;
        printf("Please enter a value (1-999, 0 to quit): ");
        scanf(" %d", &input);
        if (input == 0) {
            return 0;
        }
        while (getchar() != '\n');

        printf("You entered the number ");
        if (input >= 100) {
            printf("%s hundred", underTwenty[input / 100]);
            input %= 100;
            if (input > 0) {
                printf(" and ");
            }
        }

        if (input >= 20) {
            printf("%s", tens[input / 10]);
            input %= 10;
            if (input > 0) {
                printf("-%s", underTwenty[input]);
            }
        } else if (input > 0) {
            printf("%s", underTwenty[input]);
        }
        printf("\n");
    }
}

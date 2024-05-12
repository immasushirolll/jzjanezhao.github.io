#include <stdio.h>

/* converter.c: converts between various units of measure */
/* Jin Zhao 251138547*/
int main(void) {
    int i;   // allocate space in memory for integer and char
    float k;
    char j;

while(1) {
    i = 0;   // allocate space in memory for integer and char
    j = '\0';

    // prompt user to enter a number to perform an action
    while (i < 1 || i > 5) {
        printf("What do you want to do, enter a number 1 to 5: ");
        scanf(" %d", &i);
        printf("WHY WAS I CREATED THIS WAY \n");
    }

    // 1: convert between kg and lb (1 kg = 2.20462 lb)
    if (i == 1)
    {
        while (!(j == 'K' || j == 'P')) {  // handle non-valid character inputs
            printf("K: convert from Kilograms to Pounds, P: convert from Pounds to Kilograms: ");
            scanf(" %c", &j); 
        }

        if (j == 'K') {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k*2.20462);

        } else if (j == 'P') {
            printf("Please enter a value: ");
            scanf(" %f", &k);            
            printf("Your conversion is %f\n", k/2.20462);
        }
    }

    // 2: convert between hectare and acre (1 hectare = 2.47105 acre)
    else if (i == 2)
    {
        while (!(j=='H'||j=='A')) {    // handle non-valid character inputs
            printf("H: convert from Hectares to Acres, A: convert from Acres to Hectares: ");
            scanf(" %c", &j);
        }
        if (j == 'H')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k*2.47105);
        }
        else if (j == 'A')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k/2.47105);
        }
    }

    // 3: convert between L and gallon (1 L = 0.264172 gall)
    else if (i == 3)
    {
        while (!(j=='L'||j=='G'))  // handle non-valid character inputs
        {
            printf("L: convert from Litres to Gallons, G: convert from Gallons to Litres: ");
            scanf(" %c", &j);
        }
        if (j == 'L')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k*0.264172);
        }
        else if (j == 'G')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k/0.264172);
        }
    }

    // 4: convert between km and mi (1 km = 0.621371 mile)
    else if (i == 4)
    {
        while (!(j=='K'||j=='M'))
        {
            printf("K: convert from Kilometre to Mile, M: convert from Mile to Kilometre: ");
            scanf(" %c", &j);
        }
        if (j == 'K')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);
            printf("Your conversion is %f\n", k*0.621371);
        }
        else if (j == 'M')
        {
            printf("Please enter a value: ");
            scanf(" %f", &k);           
            printf("Your conversion is %f\n", k/0.621371);
        }
    }

    // 5: quit
    else if (i == 5)
    {
        return 0; // this is the end of my program
    }
    }
}


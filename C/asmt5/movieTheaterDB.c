#include "movieTheaterDB.h"

/* Create entries in and perform operations on a movie theater database*/
int main() {
    // title
    printf("*********************\n");
    printf("* 2211 MovieList Cinema *\n");
    printf("*********************\n");
    
    // variables
    char cmd = '\0';    // program command

    // prompt user for program command  
    while (cmd != 'q')
    {
        printf("Enter program command (h/m/a/q): ");
        scanf(" %c", &cmd);  // exits when user enters 'q'

        // print help page
        if (cmd == 'h') {
            printf("\th = print help: show program commands\n");
            printf("\tm = control movies: (i = insert/s = search/u = update/p = print/e = erase/q = return to main)\n");
            printf("\ta = control actors (i/s/u/p/e/q)\n");
            printf("\tq = quit program\n");
        }
        // control movies
        if (cmd == 'm') {
            ControlMovies();
        }

        // control actors
        if (cmd == 'a') {
            ControlActors();
        }
    }
    return 0;
}
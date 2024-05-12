#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NAME_LEN 99
#define GENRE_LEN 24
#define ARR_LEN 100

// make structure storing each movie
struct Movie 
{
    int code;   // positive, unique
    char name[NAME_LEN+1];     // up to 100 chars in length including null
    char genre[GENRE_LEN+1];    // up to 24 chars
    float rating;     // out of 10, 0.0-10.0 valid, to 1 decimal
};

// function to search for a movie code in structure using counter, outputs index
int searchMovie(int inputCode, int counter, const struct Movie* movies)
{
    for (int i = 0; i < counter ; i++) {
        // if found, return index of where entry is
        if (movies[i].code == inputCode) {
            return i;
        }
    }
    return -1;
};

// function to print movie information in a neat manner
void printMovieInfo(const struct Movie *movies)
{
    printf("%-13d%-30s%-20s%.1f\n", movies->code, movies->name, movies->genre, movies->rating);
};

/* Create entries in and perform operations on a movie theatre database*/
int main() {
    // title
    printf("*********************\n");
    printf("* 2211 Movie Cinema *\n");
    printf("*********************\n");
    
    // variables
    char op = '\0';
    int mCode;
    int counter = 0;    // counts number of movies entered

    // declare structure type variable
    struct Movie* movies = (struct Movie*) calloc(ARR_LEN, sizeof(struct Movie));
    for (int i = 0; i < ARR_LEN; i++) {
        movies[i].code = -1;
        strcpy(movies[i].genre, "");
        strcpy(movies[i].name, "");
        movies[i].rating = -1;
    }

    // prompt user for operation code  
    while (op != 'q')
    {
        printf("Enter operation code: ");   // i, s, u, p, q
        scanf(" %c", &op);  // exits when user enters 'q'

        // i: insert new movie
        if (op == 'i') {

            /* check if code < 0, already exists, or if database full, 
            keeps prompting user until successfully added code */
            while (1) {
                printf("\tEnter movie code: ");
                scanf(" %d", &mCode);
                while (getchar() != '\n');

                if (mCode < 0) {
                    printf("Movie code must be positive and unique integer.");
                    continue;
                }
                if (strcmp(movies[mCode].name, "")) {
                    printf("Movie already exists.\n");
                    continue;
                }
                movies[mCode].code = mCode;
                printf("\tEnter movie name: ");     // prompts for movie name
                fgets(movies[mCode].name, sizeof(movies[mCode].name), stdin);
                if (movies[mCode].name[strlen(movies[mCode].name) - 1] == '\n') {
                    movies[mCode].name[strlen(movies[mCode].name) - 1] = '\0';
                }

                printf("\tEnter movie genre: ");    //  prompts for movie genre
                fgets(movies[mCode].genre, sizeof(movies[mCode].genre), stdin);
                if (movies[mCode].genre[strlen(movies[mCode].genre) - 1] == '\n') {
                    movies[mCode].genre[strlen(movies[mCode].genre) - 1] = '\0';
                }

                // keeps prompting until valid rating given
                while (!(movies[mCode].rating >= 0.0 && movies[mCode].rating <= 10.0))
                {
                    printf("\tEnter movie rating: ");   // prompts for rating
                    scanf(" %f", &movies[mCode].rating);
                    while (getchar() != '\n');
                }
                counter++;
                break;       
            }    
        }

        // s: search for movie using the code and print it out
        if (op == 's') {
            int j = -1;     // j will store index of the found movie
            // if not found, report error, prompt again
            while (j == -1)
            {
                printf("\tEnter movie code: ");
                scanf(" %d", &mCode);
                j = searchMovie(mCode, counter, &movies[0]);

                // if found, j not -1, print out all values for this movie 
                if (j != -1) {
                    printf("%-13s%-30s%-20s%-20s%.0f\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");
                    printMovieInfo(&movies[j]);
                    break;
                }
                else {
                    printf("Movie not found, try again.\n");
                }
            }
        }

        // u: update movie in database
        if (op == 'u') {  
            int j = -1;
            // if not found, report error, prompt again
            while (j == -1)
            {
                while (getchar() != '\n');
                printf("\tEnter movie code: ");
                scanf(" %d", &mCode);
                while (getchar() != '\n');
                j = searchMovie(mCode, counter, &movies[0]);

                // if found, j not -1, prompt to update values for movie 
                if (j != -1) {
                    /* keep prompting user until successfully updated information */
                    while (1) {                        
                        printf("\tEnter movie name: ");     // prompts for movie name
                        fgets(movies[j].name, sizeof(movies[j].name), stdin);
                        if (movies[j].name[strlen(movies[j].name) - 1] == '\n') {
                            movies[j].name[strlen(movies[j].name) - 1] = '\0';
                        }

                        printf("\tEnter movie genre: ");
                        fgets(movies[j].genre, sizeof(movies[j].genre), stdin);
                        if (movies[j].genre[strlen(movies[j].genre) - 1] == '\n') {
                            movies[j].genre[strlen(movies[j].genre) - 1] = '\0';
                        }

                        movies[j].rating = -1.0;    // set to -1.0 to enter while loop below

                        // keeps prompting until valid rating given
                        while (!(movies[j].rating >= 0.0 && movies[j].rating <= 10.0))
                        {
                            printf("\tEnter movie rating: ");   // prompts for rating
                            scanf(" %f", &movies[j].rating);
                            while (getchar() != '\n');
                        }
                        break;
                    }
                    break;
                }
                else {
                    printf("Movie not found, try again.\n");
                }             
            }
        }

        // p: print entire list of movies
        if (op == 'p') {
            printf("%-13s%-30s%-20s%-20s%.0f\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");
            for (int i = 0; i < counter; ++i) {
                printMovieInfo(&movies[i]);
            }
        }

        // q: quit
            // will quit when opp == 'q'
    }
    
    free(movies);
    return 0;
}
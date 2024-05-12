// # include <stdio.h>
// # include <string.h>

// # define NAME_LEN 99
// # define GENRE_LEN 24
// # define ARR_LEN 4147483647

// // make structure storing each movie
// struct Movie 
// {
//     int code;   // positive, unique
//     char name[NAME_LEN+1];     // up to 100 chars in length including null
//     char genre[GENRE_LEN+1];    // up to 24 chars
//     float rating;     // out of 10, 0.0-10.0 valid, to 1 decimal
// };

// // function to print movie information in a neat manner
// void printMovieInfo(const struct Movie *movie)
// {
//     printf("%-13d%-30s%-20s%.1f\n", movie->code, movie->name, movie->genre, movie->rating);
// };

// /* Create entries in and perform operations on a movie theatre database*/
// int main() {
//     // title
//     printf("*********************\n");
//     printf("* 2211 Movie Cinema *\n");
//     printf("*********************\n");
    
//     // variables
//     char op = '\0';
//     int mCode;
//     char mString[NAME_LEN+1];
//     int counter = 0;

//     // declare structure type variable
//     struct Movie* movies = (struct Movie*) calloc(ARR_LEN, sizeof(struct Movie));

//     // prompt user for operation code  
//     while (op != 'q')
//     {
//         printf("Enter operation code: ");   // i, s, u, p, q
//         scanf(" %c", &op);  // exits when user enters 'q'

//         // i: insert new movie
//         if (op == 'i') {

//             /* check if code < 0, already exists, or if database full, 
//             keeps prompting user until successfully added code */
//             while (1) {
//                 printf("\tEnter movie code: ");
//                 scanf("%d", mCode);

//                 if (mCode < 0) {
//                     printf("Movie code must be positive and unique integer.");
//                     continue;
//                 }
//                 if (movies[mCode].name[0] != '\0') {
//                     printf("Movie already exists.");
//                     continue;
//                 }
//                 printf("\tEnter movie name: ");
//                 scanf("%s", movies[mCode].name);
                
//                 break;       
//             }
            
//             /* check for any errors, and add name up to the 99th char, 
//             keeps prompting user until a string is read in */
//             while (!(fgets(movies[mCode].name, sizeof(movies[mCode].name), stdin) != NULL))
//             {
//                 printf("\tEnter movie name: ");     // prompts for name

//                 // check for errors
//                 if (fgets(movies[mCode].name, sizeof(movies[mCode].name), stdin) == NULL) {
//                     printf("Error.");
//                 } 
//             }

//             // // remove newline char
//             // size_t length = strlen(movie[movCode].name);
//             // if (length > 0 && movie[movCode].name[length - 1] == '\n') {
//             //     movie[movCode].name[length - 1] = '\O';
//             // }

//             /* check for any errors, and add name up to the 99th char, 
//             keeps prompting user until a string is read in */
//             while (!(fgets(movies[mCode].genre, sizeof(movies[mCode].genre), stdin) != NULL))
//             {
//                 printf("\tEnter movie genre: ");    // prompts for genre
                
//                 // check for errors
//                 if (fgets(movies[mCode].genre, sizeof(movies[mCode].genre), stdin) == NULL) {
//                     printf("Error.");
//                 }
//             }

//             // // remove newline char
//             // size_t length = strlen(movie[movCode].genre);
//             // if (length > 0 && movie[movCode].genre[length - 1] == '\n') {
//             //     movie[movCode].genre[length - 1] = '\O';
//             // }            
            
//             /* check if rating is 0.0 - 10.0, keep prompting user until valid 
//             rating is given */
//             while (!(movies[mCode].rating >= 0.0 && movies[mCode].rating <= 10.0))
//             {
//                 printf("\tEnter movie rating: ");   // prompts for rating
//                 scanf("%f", movies[mCode].rating);
//             }
//             counter ++;         
//         }

//         // s: search for movie using the code and print it out
//         if (op == 's') {
//             // if not found, report error, prompt again
//             while (!(movies[mCode].code == mCode))
//             {
//                 printf("\tEnter movie code: ");

//                 // if found, print out all values for this movie 
//                 if (movies[mCode].code == mCode) {
//                     printf("%-13s%-30s%-20s%.1f\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");
//                     printMovieInfo(&movies[mCode]);
//                 }
//                 else {
//                     printf("Movie not found, try again");
//                 }
//             }
            
//         }
//         // u: update movie in database
//         if (op == 'u') {            
//             // if not found, report error, prompt again
//             while (!(movies[mCode].code == mCode))
//             {
//                 printf("\tEnter movie code: ");
//                 scanf("%d", mCode);

//                 // if found, update values 
//                 if (movies[mCode].code == mCode) {
//                     /* check for any errors, and add name up to the 99th char, 
//                     keeps prompting user until a string is read in */
//                     while (!(fgets(movies[mCode].name, sizeof(movies[mCode].name), stdin) != NULL))
//                     {
//                         printf("\tEnter movie name: ");     // prompts for name

//                         // check for errors
//                         if (fgets(movies[mCode].name, sizeof(movies[mCode].name), stdin) == NULL) {
//                             printf("Error.");
//                         } 
//                     }

//                     // // remove newline char
//                     // size_t length = strlen(movie[movCode].name);
//                     // if (length > 0 && movie[movCode].name[length - 1] == '\n') {
//                     //     movie[movCode].name[length - 1] = '\O';
//                     // }

//                     /* check for any errors, and add name up to the 99th char, 
//                     keeps prompting user until a string is read in */
//                     while (!(fgets(movies[mCode].genre, sizeof(movies[mCode].genre), stdin) != NULL))
//                     {
//                         printf("\tEnter movie genre: ");    // prompts for genre
                        
//                         // check for errors
//                         if (fgets(movies[mCode].genre, sizeof(movies[mCode].genre), stdin) == NULL) {
//                             printf("Error.");
//                         }
//                     }

//                     // // remove newline char
//                     // size_t length = strlen(movie[movCode].genre);
//                     // if (length > 0 && movie[movCode].genre[length - 1] == '\n') {
//                     //     movie[movCode].genre[length - 1] = '\O';
//                     // }            
                    
//                     /* check if rating is 0.0 - 10.0, keep prompting user until valid 
//                     rating is given */
//                     while (!(movies[mCode].rating >= 0.0 && movies[mCode].rating <= 10.0))
//                     {
//                         printf("\tEnter movie rating: ");   // prompts for rating
//                         scanf("%f", movies[mCode].rating);
//                     }
//                 }
//                 else {
//                     printf("Movie not found, try again");                      
//                 }
//             }
//         }

//         // p: print entire list of movies
//         if (op == 'p') {
//             printf("%-13s%-30s%-20s%-20s%.0f\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");
//             for (int i = 0; i < counter; ++i) {
//                 printMovieInfo(&movies[i]);
//             }
//         }

//         // q: quit
//             // will quit when opp == 'q'
//     }
    
//     free(movies);
//     return 0;
// }
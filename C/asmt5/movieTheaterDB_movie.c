#include "movieTheaterDB_movie.h"

// structure of movie nodes
struct MovieNode
{
    int code;
    char name[100];
    char genre[25];
    float rating;
    struct MovieNode *next;
};

typedef struct MovieNode *movie;

/* function to search linked list for movie node using code
if found, return pointer to node, else return null */
movie searchMovies(movie head, int inputCode) {
    movie p = head;
    while (p != NULL && p->code != inputCode) {
        p = p->next;
    }
    return p;
}

// make new node
movie createMovie(movie head) {
    movie temp = (movie)malloc(sizeof(struct MovieNode));
    temp->next = NULL;
    int inputCode;
    while (1) {
        printf("\tEnter movie code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');
        
        if (inputCode < 0) {
            printf("Movie code must be positive and unique integer.\n");
            continue;
        }

        movie p = searchMovies(head, inputCode);  // look for node with inputted code
        if (p != NULL) {    // node with code exists, report error
            printf("Movie already exists.\n");
            continue;
        }
        temp->code = inputCode;
        
        printf("\tEnter movie name: ");     // prompts for movie name
        if (fgets(temp->name, sizeof(temp->name), stdin) != NULL) {
            if (temp->name[strlen(temp->name) - 1] == '\n') {   // clear buffer
                temp->name[strlen(temp->name) - 1] = '\0';
            }
            else {      // problem reading input, report error
                printf("Error reading input.\n");
                continue;
            }   
        }

        printf("\tEnter movie genre: ");    // prompts for movie genre
        if (fgets(temp->genre, sizeof(temp->genre), stdin) != NULL) {
            if (temp->genre[strlen(temp->genre) - 1] == '\n') {
                temp->genre[strlen(temp->genre) - 1] = '\0';
            }
            else {
                printf("Error reading input.\n");
                continue;
            }
        }

        temp->rating = -1.0;
        // keeps prompting until valid rating given
        while (!(temp->rating >= 0.0 && temp->rating <= 10.0))
        {
            printf("\tEnter movie rating: ");   // prompts for rating
            scanf(" %f", &temp->rating);
            while (getchar() != '\n');
        }
        break;
    }
    return temp;
}

/* takes in pointer to head of linked list, insert new movie node at front */
movie insertMovie(movie head) {
    movie temp, p;
    temp = createMovie(head);
    if (head == NULL) {
        head = temp;
    }
    else {
        p = head;
        while (p->next != NULL) {
            p = p->next;
        }
        p->next = temp;
    }
    return head;
}

// function to update movie information
void updateMovie(movie head) {
    movie p = head;
    movie temp = NULL;
    int inputCode;

    while (1)    // keep prompting user until valid code given
    {
        printf("\tEnter movie code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');
                    
        movie temp = searchMovies(head, inputCode);       
        if (temp == NULL) {     // temp = null if not found, report error
            printf("Movie not found, try again.\n");    
            continue;  
        }

        printf("\tEnter movie name: ");     // prompts for movie name
        if (fgets(temp->name, sizeof(temp->name), stdin) != NULL) {
            if (temp->name[strlen(temp->name) - 1] == '\n') {   // clear buffer
                temp->name[strlen(temp->name) - 1] = '\0';
            }
            else {      // problem reading input, report error
                printf("Error reading input.\n");
                continue;
            }   
        }

        printf("\tEnter movie genre: ");    // prompts for movie genre
        if (fgets(temp->genre, sizeof(temp->genre), stdin) != NULL) {
            if (temp->genre[strlen(temp->genre) - 1] == '\n') {
                temp->genre[strlen(temp->genre) - 1] = '\0';
            }
            else {
                printf("Error reading input.\n");
                continue;
            }
        }

        temp->rating = -1;
        // keeps prompting until valid rating given
        while (!(temp->rating >= 0.0 && temp->rating <= 10.0))
        {
            printf("\tEnter movie rating: ");   // prompts for rating
            scanf(" %f", &temp->rating);
            while (getchar() != '\n');
        }
        break;
    }
}

// function to print movie information in a neat manner
void printMovie(movie head) {
    movie p = head;

    printf("%-13s %-30s %-20s %-20s\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");    // headers
    
    p = p->next;
    while (p != NULL)
    {
        printf("%-13d %-30s %-20s %.1f\n", p->code, p->name, p->genre, p->rating);
        p = p->next;
    }
}

// function to erase movie node using code
void eraseMovie(movie head) {
    movie current, prev;
    int inputCode;
    
    while (1)    // keep prompting user until valid code given
    {
        printf("\tEnter movie code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');               
    
        current = head;
        prev = NULL;
        while (current != NULL && current->code != inputCode) {
            prev = current;
            current = current->next;
        }

        // if movie w code found, remove from list and free memory
        if (current == NULL) {      
            printf("Movie not found, try again.\n");    // if not, report error
            continue;
        }   
        if (prev == NULL) {
            head = head->next;      // found in first node
        }     
        else {
            prev->next = current->next;
        }
        free(current);
        break;
    }
}

void ControlMovies() {
    char op = '\0';
    movie head = (movie)malloc(sizeof(struct MovieNode));
    head->code = -1;
    head->next = NULL;
    while (op != 'q')
    {
        printf("Enter operation code: ");
        scanf(" %c", &op);
        
        if (op == 'i') {
            movie temp = insertMovie(head);
        }
        if (op == 's') {
            int inputCode;
            movie temp = NULL;
            
            while (1)    // keep prompting user until valid code given
            {
                printf("\tEnter movie code: ");
                scanf(" %d", &inputCode);
                while (getchar() != '\n');
                
                movie temp = searchMovies(head, inputCode);       // temp = null if not found, report error
                if (temp == NULL) {
                    printf("Movie not found, try again.\n");    // this works fine
                    continue;  
                }
                if (temp != NULL) {     // does sense that it's not null, but why doesn't it print, and kicks me out
                    printf("%-13s %-30s %-20s %-20s\n", "Movie Code", "Movie Name", "Movie Genre", "Movie Rating");    // headers
                    printf("%-13d %-30s %-20s %.1f\n", temp->code, temp->name, temp->genre, temp->rating);
                }
                break;
            }
        }
        if (op == 'u') {
            updateMovie(head);
        }
        if (op == 'p') {
            printMovie(head);
        }
        if (op == 'e') {
            eraseMovie(head);
        }
    }
}
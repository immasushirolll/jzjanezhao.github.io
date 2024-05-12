#include "movieTheaterDB_actor.h"

// structure of actor nodes
struct ActorNode
{
    int code;
    char name[50];
    int age;        // only integers 0-120
    char pfp[50];
    struct ActorNode *next;
};

typedef struct ActorNode *actor;

/* function to search linked list for actor node using code
if found, return pointer to node, else return null */
actor searchActors(actor head, int inputCode) {
    actor p = head;
    while (p != NULL && p->code != inputCode) {
        p = p->next;
    }
    return p;
}

// make new node
actor createActor(actor head) {
    actor temp = (actor)malloc(sizeof(struct ActorNode));
    temp->next = NULL;
    int inputCode, inputAge;    // technically unnecessary, both could just be input, but allocated separately for readability
    while (1) {
        printf("\tEnter actor code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');
        
        if (inputCode < 0) {
            printf("Actor code must be positive and unique integer.\n");
            continue;
        }

        actor p = searchActors(head, inputCode);  // look for node with inputted code
        if (p != NULL) {    // node with code exists, report error
            printf("Actor already exists.\n");
            continue;
        }
        temp->code = inputCode;
        
        printf("\tEnter actor name: ");     // prompts for actor name
        if (fgets(temp->name, sizeof(temp->name), stdin) != NULL) {
            if (temp->name[strlen(temp->name) - 1] == '\n') {   // clear buffer
                temp->name[strlen(temp->name) - 1] = '\0';
            }
            else {      // problem reading input, report error
                printf("Error reading input.\n");
                continue;
            }   
        }

        while (1)
        {
            printf("\tEnter actor age: ");
            scanf(" %d", &inputAge);
            while (getchar() != '\n');
            
            if (inputAge < 0 || inputAge > 120) {
                printf("Actor age must be between 0 and 120.\n");
                continue;
            }
            break;
        }
        temp->age = inputAge;

        printf("\tEnter actor IMDB profile page: ");    // prompts for actor profile
        if (fgets(temp->pfp, sizeof(temp->pfp), stdin) != NULL) {
            if (temp->pfp[strlen(temp->pfp) - 1] == '\n') {
                temp->pfp[strlen(temp->pfp) - 1] = '\0';
            }
            else {
                printf("Error reading input.\n");
                continue;
            }
        }
        break;
    }
    return temp;
}

/* takes in pointer to head of linked list, insert new actor node at front */
actor insertActor(actor head) {
    actor temp, p;
    temp = createActor(head);
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

// function to update actor information
void updateActor(actor head) {
    actor p = head;
    actor temp = NULL;
    int inputCode, inputAge;

    while (1)    // keep prompting user until valid code given
    {
        printf("\tEnter actor code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');
                    
        actor temp = searchActors(head, inputCode);       
        if (temp == NULL) {     // temp = null if not found, report error
            printf("Actor not found, try again.\n");    
            continue;  
        }

        printf("\tEnter actor name: ");     // prompts for actor name
        if (fgets(temp->name, sizeof(temp->name), stdin) != NULL) {
            if (temp->name[strlen(temp->name) - 1] == '\n') {   // clear buffer
                temp->name[strlen(temp->name) - 1] = '\0';
            }
            else {      // problem reading input, report error
                printf("Error reading input.\n");
                continue;
            }   
        }

        while (1)
        {
            printf("\tEnter actor age: ");
            scanf(" %d", &inputAge);
            while (getchar() != '\n');
        
            if (inputAge < 0 || inputAge > 120) {
                printf("Actor age must be between 0 and 120.\n");
                continue;
            }
            break;
        }
        temp->age = inputAge;

        printf("\tEnter actor IMDB profile page: ");    // prompts for actor profile
        if (fgets(temp->pfp, sizeof(temp->pfp), stdin) != NULL) {
            if (temp->pfp[strlen(temp->pfp) - 1] == '\n') {
                temp->pfp[strlen(temp->pfp) - 1] = '\0';
            }
            else {
                printf("Error reading input.\n");
                continue;
            }
        }
        break;
    }
}

// function to print actor information in a neat manner
void printActor(actor head) {
    actor p = head;

    printf("%-13s %-30s %-20s %-20s\n", "Actor Code", "Actor Name", "Actor Age", "Actor IMDB Profile");    // headers
    
    p = p->next;
    while (p != NULL)
    {
        printf("%-13d %-30s %-20d %-20s\n", p->code, p->name, p->age, p->pfp);
        p = p->next;
    }
}

// function to erase actor node using code
void eraseActor(actor head) {
    actor current, prev;
    int inputCode;
    
    while (1)    // keep prompting user until valid code given
    {
        printf("\tEnter actor code: ");
        scanf(" %d", &inputCode);
        while (getchar() != '\n');               
    
        current = head;
        prev = NULL;
        while (current != NULL && current->code != inputCode) {
            prev = current;
            current = current->next;
        }

        // if actor w code found, remove from list and free memory
        if (current == NULL) {      
            printf("Actor not found, try again.\n");    // if not, report error
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

void ControlActors() {
    char op = '\0';
    actor head = (actor)malloc(sizeof(struct ActorNode));;
    head->code = -1;
    head->next = NULL;
    while (op != 'q')
    {
        printf("Enter operation code: ");
        scanf(" %c", &op);
        
        if (op == 'i') {
            actor temp = insertActor(head);
        }
        if (op == 's') {
            int inputCode;
            actor temp = NULL;
            
            while (1)    // keep prompting user until valid code given
            {
                printf("\tEnter actor code: ");
                scanf(" %d", &inputCode);
                while (getchar() != '\n');
                
                actor temp = searchActors(head, inputCode);       // temp = null if not found, report error
                if (temp == NULL) {
                    printf("Actor not found, try again.\n");    
                    continue;  
                }
                if (temp != NULL) {     
                    printf("%-13s %-30s %-20s %-20s\n", "Actor Code", "Actor Name", "Actor Age", "Actor IMDB Profile");    // headers
                    printf("%-13d %-30s %-20d %-20s\n", temp->code, temp->name, temp->age, temp->pfp);
                }
                break;
            }
        }
        if (op == 'u') {
            updateActor(head);
        }
        if (op == 'p') {
            printActor(head);
        }
        if (op == 'e') {
            eraseActor(head);
        }
    }
}
// Assignment 1
// This program was written by Miller Zhang z5429518
// on 9/7/2023
// This program builds a car park which could recognize different commands
// and can calculate the fee automatically, and record the park history

#include <stdio.h>

#include <math.h>

// Provided constants
#define CARPARK_ROWS 8
#define CARPARK_COLUMNS 6
#define NORMAL_PARKING_RATE 10
#define EMPTY -1

// Your constants here
#define EXIT_ROW 7
#define EXIT_COL 3
#define PREMIUM_PARKING_RATE 50

// Provided enums
enum space_type
{
    NORMAL,
    PREMIUM,
    CARWASH_BAY,
    ELECTRIC_CHARGING_STATION,
    LEAVE
};

// Provided structs
struct car_space
{
    enum space_type type;
    // car
    int parking_rate;
    int licence_plate;
    int occupied_since;
    int stay_time;
    int fee;
    int build_time;
    // wash bay
    int in_use;
    int bay_vincity;
    // charge_station
    int cable_length;
    int charge_from;
    int charge_till;
};

struct history
{
    enum space_type type;
    short from;
    short till;
    short row;
    short col;
};

struct report
{
    // this would record the time
    short cnt;
    struct history parking_history[50];
};

////////////////////////////////////////////////////////////////////////////////
////////////////////////  YOUR FUNCTION PROTOTYPE  /////////////////////////////
////////////////////////////////////////////////////////////////////////////////
void init_cars(int licence, int row, int col,
               struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
               struct report reports[]);
void park_car(int licence, int row, int col, int time,
              struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
              struct report reports[]);
void count_cars(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void print_available_spaces(
    struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void apply_discount(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void find_car(int licence,
              struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void leave_car(int licence, int time_of_leaving,
               struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
               struct report reports[]);
void build_estation(int row, int col, int build_time, int cable_length,
                    struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void move_car(int licence, int nrow, int ncol, int move_time,
              struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
              struct report reports[]);
void move2nearbay(int licence, int nrow, int ncol,
                  int move_time, int row, int col,
                  struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
                  struct report reports[]);
void move2washbay(int licence, int nrow, int ncol, int move_time,
                  int row, int col,
                  struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
                  struct report reports[]);
void charging_car(int licence, int from, int till,
                  struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
                  struct report reports[]);
void build_wash_bay(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
                    int build_time, int row, int column);
void initialise_reports(struct report reports[]);
void add_history(int license, int type, int row, int col, int from,
                 int till, struct report reports[]);
void show_history(int license, struct report reports[]);
int can_exit(int row, int column,
             struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
////////////////////////////////////////////////////////////////////////////////
////////////////////// PROVIDED FUNCTION PROTOTYPE  ////////////////////////////
////////////////////////////////////////////////////////////////////////////////
void initialise_carpark(
    struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void print_carpark(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]);
void print_car_space(struct car_space space);
///////////////////////////// Mian Function  ///////////////////////////////////
int main(void)
{
    struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS];
    struct report reports[10000];
    initialise_carpark(carpark);
    initialise_reports(reports);
    printf("Welcome to CS Carpark!\n\n");
    printf("Which row of the carpark would you like to reserve "
           "for premium parking? ");
    // choose premium parking row
    int premium_parking_row;
    scanf("%d", &premium_parking_row);
    for (int i = 0; i < CARPARK_COLUMNS; i++)
    {
        carpark[premium_parking_row][i].type = PREMIUM;
        carpark[premium_parking_row][i].parking_rate = PREMIUM_PARKING_RATE;
    }
    print_carpark(carpark);
    printf("Add all current cars to the carpark. ");
    printf("How many cars would you like to add? ");
    //
    int init_carnum;
    int init_col, init_row, initial_licence;
    scanf("%d", &init_carnum);
    printf("Where would you like to park each car?\n");
    for (int i = 0; i < init_carnum; i++)
    {
        scanf("%d %d %d", &initial_licence, &init_row, &init_col);
        init_cars(initial_licence, init_row, init_col, carpark, reports);
    }
    print_carpark(carpark);
    printf("The carpark is now ready for business!\n");
    char command = 0;
    do
    {
        printf("Enter a command: ");
        scanf(" %c", &command);
        if (command == 'P')
        {
            print_carpark(carpark);
        }
        else if (command == 'p')
        {
            int licence, row, col, time;
            scanf(" %d %d %d %d", &licence, &row, &col, &time);
            park_car(licence, row, col, time, carpark, reports);
        }
        else if (command == 'o')
        {
            count_cars(carpark);
        }
        else if (command == 'F')
        {
            print_available_spaces(carpark);
        }
        else if (command == 'd')
        {
            apply_discount(carpark);
        }
        else if (command == 'f')
        {
            int licence;
            scanf(" %d", &licence);
            find_car(licence, carpark);
        }
        else if (command == 'l')
        {
            int licence, time_of_leaving;
            scanf(" %d %d", &licence, &time_of_leaving);
            leave_car(licence, time_of_leaving, carpark, reports);
        }
        else if (command == 'e')
        {
            int row, col, build_time, cable_len;
            scanf(" %d %d %d %d", &row, &col, &build_time, &cable_len);
            build_estation(row, col, build_time, cable_len, carpark);
        }
        else if (command == 'c')
        {
            int licence, charge_from, charge_til;
            scanf(" %d %d %d", &licence, &charge_from, &charge_til);
            charging_car(licence, charge_from, charge_til, carpark, reports);
        }
        else if (command == 'm')
        {
            int licence, row, col, move_time;
            scanf(" %d %d %d %d", &licence, &row, &col, &move_time);
            move_car(licence, row, col, move_time, carpark, reports);
        }
        else if (command == 'w')
        {
            int row, column, build_time;
            scanf(" %d %d %d", &row, &column, &build_time);
            build_wash_bay(carpark, build_time, row, column);
        }
        else if (command == 'h')
        {
            int licence;
            scanf(" %d", &licence);
            show_history(licence, reports);
        }
    } while (scanf("%c", &command) >= 0);
    printf("The carpark is now closed. Bye!\n");
    return 0;
}
////////////////////////////////////////////////////////////////////////////////
/////////////////////////////  YOUR FUNCTIONS //////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
int is_car_valid(int row, int col) {
    int is_valid = row < CARPARK_ROWS 
        && row >= 0 
        && col < CARPARK_COLUMNS 
        && col >= 0;
    return is_valid;
}
void init_cars(int licence, int row, int col, struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
               struct report reports[])
{
    if (!is_car_valid(row, col))
    {
        printf("Invalid car space!\n");
    }
    else
    {
        carpark[row][col].licence_plate = licence;
        carpark[row][col].occupied_since = 0;
        add_history(licence, carpark[row][col].type, row, col, 0, 23, reports);
    }
}
////////////////////////////////////////////////////////////////////////////////////////
void park_car_helper2(int* all_empty, struct car_space** carpark, int row, int col) {
    for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 
                && i < CARPARK_ROWS && j >= 0 
                && j < CARPARK_COLUMNS && carpark[i][j].licence_plate != EMPTY) {
                    *all_empty = 0;
                }
            }
        }
        if (!all_empty) {
            printf("This carwash bay cannot be used as one or more of the surrounding spaces are currently occupied. \n");
            return;
        }
}
void park_car_helper(int* all_empty, struct car_space (*carpark)[CARPARK_COLUMNS], int row, int col) {
    if (carpark[row][col].type == CARWASH_BAY) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < CARPARK_ROWS && j >= 0 && j < CARPARK_COLUMNS && carpark[i][j].licence_plate != EMPTY) {
                    *all_empty = 0;
                }
            }
        }
        if (!all_empty) {
            printf("This carwash bay cannot be used as one or more of the surrounding spaces are currently occupied. \n");
            return;
        }
    }
}

void park_car_full_helper(int* full, struct car_space (*carpark)[CARPARK_COLUMNS]) {
        for (int i = 0; i < CARPARK_ROWS; i++)
        {
            for (int j = 0; j < CARPARK_COLUMNS; j++)
            {
                if (!carpark[i][j].bay_vincity && carpark[i][j].licence_plate == EMPTY)
                {
                    *full = 0;
                }
        }
    }
}
void park_car(int licence, int row, int col, int time,
              struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS], struct report reports[])
{
    if (row >= CARPARK_ROWS || row < 0 || col >= CARPARK_COLUMNS || col < 0)
    {
        printf("Invalid car space!\n");
    }
    else if (carpark[row][col].type == ELECTRIC_CHARGING_STATION)
    {
        printf("Cannot park on a space occupied by an electric charging station.\n");
    }
    else if (carpark[row][col].licence_plate != EMPTY)
    {
        printf("Car space is already occupied!\n");
    }
    else if (carpark[row][col].type != CARWASH_BAY &&
             carpark[row][col].bay_vincity)
    {
        int full = 1;
        //
        park_car_full_helper(&full, carpark);
        //
        if (!full)
        {
            printf("Cannot park next to a carwash bay. There are still other spaces available in the carpark.\n");
        }
        else
        {
            carpark[row][col].licence_plate = licence;
            carpark[row][col].occupied_since = time;
            add_history(licence, carpark[row][col].type, row, col, time, 23, reports);
            printf("Car %04d was added into car space (%d, %d) at time %d!\n", licence, row, col, time);
        }
    }
    else
    {
        int all_empty = 1;
        park_car_helper(&all_empty, carpark, row, col);
        add_history(licence, carpark[row][col].type, row, col, time, 23, reports);
        carpark[row][col].licence_plate = licence;
        carpark[row][col].occupied_since = time;
        if (carpark[row][col].type == CARWASH_BAY)
        {
            carpark[row][col].fee += 15;
            carpark[row][col].in_use = 1;
            printf("Car %04d is now using the carwash bay located at (%d, %d).\n", licence, row, col);
        }
        else
        {
            printf("Car %04d was added into car space (%d, %d) at time %d!\n", licence, row, col, time);
        }
    }
}
///////////////////////////////////////////////////////////////////////////////
void count_cars(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    int used_space = 0, empty_space;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (carpark[i][j].licence_plate != EMPTY)
            {
                used_space++;
            }
        }
    }
    empty_space = 48 - used_space;
    printf("There are currently %d cars and %d empty car"
           " spaces in the carpark.\n",
           used_space, empty_space);
}
///////////////////////////////////////////////////////////////////////////////
void print_available_spaces(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    printf("The normal car spaces that are still available are:\n");
    int number_of_cars = 0;
    for (int j = 0; j < CARPARK_ROWS; j++)
    {
        for (int i = 0; i < CARPARK_COLUMNS; i++)
        {
            if (carpark[j][i].type == NORMAL && carpark[j][i].licence_plate == EMPTY)
            {
                printf("(%d, %d): $%d\n", j, i, carpark[j][i].parking_rate);
                number_of_cars++;
            }
        }
    }
    if (number_of_cars == 0)
    {
        printf("NONE\n");
    }
    printf("\n");
    printf("The premium car spaces that are still available are:\n");
    number_of_cars = 0;
    for (int j = 0; j < CARPARK_ROWS; j++)
    {
        for (int i = 0; i < CARPARK_COLUMNS; i++)
        {
            if (carpark[j][i].type == PREMIUM && carpark[j][i].licence_plate == EMPTY)
            {
                printf("(%d, %d): $%d\n", j, i, carpark[j][i].parking_rate);
                number_of_cars++;
            }
        }
    }
    if (number_of_cars == 0)
    {
        printf("NONE\n");
    }
    printf("\n");
}
///////////////////////////////////////////////////////////////////////////////
void apply_discount(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    for (int j = 0; j < CARPARK_ROWS; j++)
    {
        for (int i = 0; i < CARPARK_COLUMNS; i++)
        {
            if (carpark[j][i].licence_plate == EMPTY)
            {
                carpark[j][i].parking_rate *= 0.9;
            }
        }
    }
    printf("A 10%% discount has been applied to all empty spaces!\n");
}
///////////////////////////////////////////////////////////////////////////////
void find_car(int licence, struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    int ccol = -1, crow = -1;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (carpark[i][j].licence_plate == licence)
            {
                crow = i;
                ccol = j;
            }
        }
    }
    if (crow == -1 && ccol == -1)
    {
        printf("No car with licence plate number %d could be found.\n", licence);
    }
    else
    {
        printf("Car %04d is parked in car space (%d, %d).\n", licence, crow, ccol);
    }
}
///////////////////////////////////////////////////////////////////////////////
void leave_car(int licence, int time_of_leaving,
               struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS],
               struct report reports[])
{
    int crow = -1, ccol = -1;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (carpark[i][j].licence_plate == licence)
            {
                crow = i;
                ccol = j;
            }
        }
    }
    if (crow == -1 && ccol == -1)
    {
        printf("No car with licence plate number %d could be found.\n", licence);
    }
    else if (carpark[crow][ccol].occupied_since != EMPTY)
    {
        int exitable = can_exit(crow, ccol, carpark);
        if (!exitable)
        {
            printf("Car %04d is blocked and is unable to exit.\n", licence);
            return;
        }
        int parking_time = time_of_leaving - carpark[crow][ccol].occupied_since;
        if (parking_time <= 0)
        {
            printf("The time of exit provided is invalid.\n");
        }
        else
        {
            if (carpark[crow][ccol].type != CARWASH_BAY)
            {
                carpark[crow][ccol].fee += parking_time * carpark[crow][ccol].parking_rate;
            }
            parking_time += carpark[crow][ccol].stay_time;
            printf("Car %04d was parked for %d hours. The total price for this duration is $%d.\n", licence, parking_time, carpark[crow][ccol].fee);
            add_history(licence, LEAVE, crow, ccol, time_of_leaving, -1, reports);
            carpark[crow][ccol].licence_plate = EMPTY;
            carpark[crow][ccol].occupied_since = EMPTY;
            carpark[crow][ccol].fee = 0;
            if (carpark[crow][ccol].type == NORMAL)
            {
                carpark[crow][ccol].parking_rate = 10;
            }
            else
            {
                carpark[crow][ccol].parking_rate = 50;
            }
        }
    }
}
///////////////////////////////////////////////////////////////////////////////
void build_estation(int row, int col, int build_time, int cable_length,
                    struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    carpark[row][col].type = ELECTRIC_CHARGING_STATION;
    carpark[row][col].cable_length = cable_length;
    carpark[row][col].build_time = build_time;
    printf("Electric charging station has been built on space (%d, %d) at time %d with cable length of %d.\n", row, col, build_time, cable_length);
}
///////////////////////////////////////////////////////////////////////////////
void charging_car_helper(int* free_min_distance
    , int* free_charger_row
    , int* free_charger_column
    , int *busy_min_time
    , int *busy_charger_row
    , int *busy_charger_column
    , int i, int j, int crow, int ccol, int from
    , struct car_space (*carpark)[CARPARK_COLUMNS])
{
     if (carpark[i][j].type == ELECTRIC_CHARGING_STATION){
        double distance = sqrt(pow(i - crow, 2) + pow(j - ccol, 2));
        if (carpark[i][j].charge_till <= from 
        && distance <= carpark[i][j].cable_length 
        && distance < *free_min_distance)
        { // fixed character too long. Now it need a function to fix TOO NESTED IF
            *free_charger_row = i;
            *free_charger_column = j;
            *free_min_distance = distance;
        }
        if (carpark[i][j].charge_till > from 
        && distance <= carpark[i][j].cable_length 
        && carpark[i][j].charge_till < *busy_min_time)
        {
            *busy_charger_row = i;
            *busy_charger_column = j;
            *busy_min_time = carpark[i][j].charge_till;
        }
    }
}

void charging_car(int licence, int from, int till
, struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS]
, struct report reports[])
{
    int ccol = -1, crow = -1;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (carpark[i][j].licence_plate == licence)
            {
                crow = i;
                ccol = j;
            }
        }
    }
    if (crow == -1 && ccol == -1)
    {
        printf("No car with licence plate number %d could be found.\n", licence);
    }
    else
    {
        int free_min_distance = 100, free_charger_row = -1, free_charger_column = -1;
        int busy_min_time = 100, busy_charger_row = -1, busy_charger_column = -1;
        for (int i = 0; i < CARPARK_ROWS; i++)
        {
            for (int j = 0; j < CARPARK_COLUMNS; j++)
            {
                charging_car_helper(&free_min_distance
                , &free_charger_row
                , &free_charger_column
                , &busy_min_time, &busy_charger_row
                , &busy_charger_column, i, j, crow, ccol, from, carpark);
            }
        }
        if (free_charger_row != -1 && free_charger_column != -1)
        {
            carpark[free_charger_row][free_charger_column].charge_from = from;
            carpark[free_charger_row][free_charger_column].charge_till = till;
            add_history(licence, ELECTRIC_CHARGING_STATION, free_charger_row, free_charger_column, from, till, reports);
            // calc charging fee
            carpark[crow][ccol].fee += (till - from) * 7;
            printf("Car %04d is being charged by the charging station located at (%d, %d).\n",
                   licence, free_charger_row, free_charger_column);
        }
        else if (busy_charger_row != -1 && busy_charger_column != -1)
        {
            printf("All nearby charging stations are currently in use. The next free charging station"
                   " is (%d, %d), which will be free at time %d.\n",
                   busy_charger_row, busy_charger_column, busy_min_time);
        }
        else
        {
            printf("Too far away to be charged, move closer to a charging station.\n");
        }
    }
}
void move_car(int licence, int nrow, int ncol, int move_time,
              struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS], struct report reports[])
{
    int ccol = -1, crow = -1;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (carpark[i][j].licence_plate == licence)
            {
                crow = i;
                ccol = j;
            }
        }
    }
    if (crow == -1 && ccol == -1)
    {
        printf("No car with licence plate number %d could be found.\n", licence);
    }
    else if (nrow >= CARPARK_ROWS || nrow < 0 || ncol >= CARPARK_COLUMNS || ncol < 0)
    {
        printf("Car %04d cannot be moved to an invalid car space. \n", carpark[crow][ccol].licence_plate);
    }
    else if (move_time <= carpark[crow][ccol].occupied_since)
    {
        printf("The move time provided is invalid.\n");
    }
    else if (carpark[nrow][ncol].type == ELECTRIC_CHARGING_STATION)
    {
        printf("Cannot park on a space occupied by an electric charging station.\n");
    }
    else if (carpark[nrow][ncol].licence_plate != EMPTY)
    {
        printf("Car %04d could not be moved to space (%d, %d) as Car %04d is already parked there.\n", licence, nrow, ncol, carpark[nrow][ncol].licence_plate);
    }
    else if (carpark[nrow][ncol].type != CARWASH_BAY &&
             carpark[nrow][ncol].bay_vincity)
    {
        move2nearbay(licence, nrow, ncol, move_time, crow, ccol, carpark, reports);
    }
    else if (carpark[nrow][ncol].type == CARWASH_BAY)
    {
        move2washbay(licence, nrow, ncol, move_time, crow, ccol, carpark, reports);
    }
    else
    {
        carpark[nrow][ncol].licence_plate = licence;
        carpark[nrow][ncol].stay_time = carpark[crow][ccol].stay_time + move_time - carpark[crow][ccol].occupied_since;
        carpark[nrow][ncol].fee = carpark[crow][ccol].fee;
        if (carpark[crow][ccol].type != CARWASH_BAY)
        {
            carpark[nrow][ncol].fee = carpark[crow][ccol].fee + (move_time - carpark[crow][ccol].occupied_since) * carpark[crow][ccol].parking_rate;
        }
        carpark[nrow][ncol].occupied_since = move_time;
        add_history(licence, carpark[nrow][ncol].type, nrow, ncol, move_time, 23, reports);
        carpark[crow][ccol].licence_plate = EMPTY;
        carpark[crow][ccol].occupied_since = EMPTY;
        carpark[crow][ccol].fee = 0;
        carpark[crow][ccol].stay_time = 0;
        printf("Car %04d has been moved to space (%d, %d).\n", licence, nrow, ncol);
    }
}

void move2nearbay(int licence, int nrow, int ncol, int move_time, int row, int col,
                  struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS], struct report reports[])
{
    int full = 1;
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            if (!carpark[i][j].bay_vincity && carpark[i][j].licence_plate == EMPTY)
            {
                full = 0;
            }
        }
    }
    if (!full)
    {
        printf("Cannot park next to a carwash bay. There are still other spaces available in the carpark.\n");
    }
    else
    {
        printf("Car %04d has been moved to space (%d, %d).\n", licence, nrow, ncol);
        carpark[nrow][ncol].licence_plate = licence;
        carpark[nrow][ncol].fee = carpark[row][col].fee + (move_time - carpark[row][col].occupied_since) * carpark[row][col].parking_rate;
        carpark[nrow][ncol].occupied_since = move_time;
        add_history(licence, carpark[nrow][ncol].type, nrow, ncol, move_time, 23, reports);
        carpark[row][col].licence_plate = EMPTY;
        carpark[row][col].occupied_since = EMPTY;
        carpark[row][col].fee = 0;
    }
}

void move2washbay(int licence, int nrow, int ncol, int move_time, int row, int col,
                  struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS], struct report reports[])
{
    int all_empty = 1;
    if (carpark[nrow][ncol].type == CARWASH_BAY)
    {
        for (int i = nrow - 1; i <= nrow + 1; i++)
        {
            for (int j = ncol - 1; j <= ncol + 1; j++)
            {
                if (i >= 0 && i < CARPARK_ROWS && j >= 0 && j < CARPARK_COLUMNS && carpark[i][j].licence_plate != EMPTY)
                {
                    all_empty = 0;
                }
            }
        }
        if (!all_empty)
        {
            printf("This carwash bay cannot be used as one or more of the surrounding spaces are currently occupied. \n");
            return;
        }
    }
    carpark[nrow][ncol].licence_plate = licence;
    carpark[nrow][ncol].stay_time = carpark[row][col].stay_time + move_time - carpark[row][col].occupied_since;
    carpark[nrow][ncol].fee = carpark[row][col].fee;
    if (carpark[row][col].type != CARWASH_BAY)
    {
        carpark[nrow][ncol].fee = carpark[row][col].fee + (move_time - carpark[row][col].occupied_since) * carpark[row][col].parking_rate;
    }
    carpark[nrow][ncol].occupied_since = move_time;
    add_history(licence, carpark[nrow][ncol].type, nrow, ncol, move_time, 23, reports);
    carpark[row][col].licence_plate = EMPTY;
    carpark[row][col].occupied_since = EMPTY;
    carpark[row][col].fee = 0;
    carpark[row][col].stay_time = 0;

    carpark[nrow][ncol].fee += 15;
    carpark[nrow][ncol].in_use = 1;
    printf("Car %04d is now using the carwash bay located at (%d, %d).\n", licence, nrow, ncol);
}

int is_not_empty(int i, int j, struct car_space (*carpark)[CARPARK_COLUMNS])
{
    int is_empty = (i >= 0) 
        && (i < CARPARK_ROWS) 
        && (j >= 0) 
        && (j < CARPARK_COLUMNS)
        && (carpark[i][j].licence_plate != EMPTY)
        && (carpark[i][j].type != CARWASH_BAY);
    return is_empty;
}

void build_wash_bay_helper(int row, int column, int build_time, struct car_space (*carpark)[CARPARK_COLUMNS], int *all_empty)
{
    // row 0-7, col 0-5
    for (int i = row - 1; i <= row + 1; i++) {
        for (int j = column - 1; j <= column + 1; j++) {
            if (is_not_empty(i, j, carpark)) {
                *all_empty = 0;
            }
        }
    }
}

void build_wash_bay_helper2(int row, int column, struct car_space (*carpark)[6])
{
    for (int i = row - 1; i <= row + 1; i++)
    {
        for (int j = column - 1; j <= column + 1; j++)
        {
            if (i >= 0 && i < CARPARK_ROWS && j >= 0 && j < CARPARK_COLUMNS)
            {
                carpark[i][j].bay_vincity = 1;
            }
        }
    }
}

void build_wash_bay(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS], int build_time, int row, int column)
{
    int all_empty = 1;
    build_wash_bay_helper(row, column, build_time, carpark, &all_empty);
    if (!all_empty)
    {
        printf("This carwash bay cannot be built as one or more of the surrounding spaces are currently occupied. \n");
    }
    else
    {
        build_wash_bay_helper2(row, column, carpark);
        carpark[row][column].type = CARWASH_BAY;
        carpark[row][column].build_time = build_time;
        carpark[row][column].in_use = 0;
        printf("Carwash bay has been built on space (%d, %d) at time %d.\n", row, column, build_time);
    }
}

void initialise_reports(struct report reports[])
{
    for (int i = 0; i < 10000; i++)
    {
        reports[i].cnt = 1;
        reports[i].parking_history[0].type = EMPTY;
    }
}

void add_history(int license, int type, int row, int col, int from, int till, struct report reports[])
{
    int idx;
    if (type == ELECTRIC_CHARGING_STATION)
    {
        idx = 0;
    }
    else
    {
        idx = reports[license].cnt;
        if (idx > 1)
        {
            reports[license].parking_history[idx - 1].till = from - 1;
        }
        reports[license].cnt = idx + 1;
    }
    reports[license].parking_history[idx].from = from;
    reports[license].parking_history[idx].till = till;
    reports[license].parking_history[idx].type = type;
    reports[license].parking_history[idx].row = row;
    reports[license].parking_history[idx].col = col;
}

void show_history(int license, struct report reports[])
{
    struct report car_report = reports[license];
    struct history phistory;
    printf("Here is the full parking report for car %d:\n", license);
    for (int i = 1; i < car_report.cnt; i++)
    {
        phistory = car_report.parking_history[i];
        if (phistory.type == LEAVE)
        {
            printf("Left the carpark at:\n");
            printf("\ttime %d.\n", phistory.from);
        }
        else if (phistory.type == CARWASH_BAY)
        {
            printf("Used carwash bay in space (%d, %d) at:\n", phistory.row, phistory.col);
        }
        else if (phistory.type == NORMAL)
        {
            printf("Parked in normal space (%d, %d) at:\n", phistory.row, phistory.col);
        }
        else if (phistory.type == PREMIUM)
        {
            printf("Parked in premium space (%d, %d) at:\n", phistory.row, phistory.col);
        }
        int from = phistory.from;
        int till = phistory.type == LEAVE ? -1 : phistory.till;
        for (int j = from; j <= till; j++)
        {
            printf("\ttime %d.\n", j);
        }
    }
    phistory = car_report.parking_history[0];
    if (phistory.type == ELECTRIC_CHARGING_STATION)
    {
        printf("Charged using charger in space (%d, %d) from:\n",
               phistory.row, phistory.col);
        printf("\ttime %d to time %d.\n", phistory.from, phistory.till);
    }
}

void can_exit_helper(int x, int y, int (*visited)[CARPARK_COLUMNS], int (*direction)[2], int (*path)[2], int head, int *tail, int *found, struct car_space (*carpark)[6])
{

    if (x >= 0 && x < CARPARK_ROWS && y >= 0 && y < CARPARK_COLUMNS &&
        carpark[x][y].type != ELECTRIC_CHARGING_STATION &&
        carpark[x][y].type != CARWASH_BAY &&
        carpark[x][y].licence_plate == EMPTY &&
        !visited[x][y])
    {
        if (x == EXIT_ROW && y == EXIT_COL)
        {
            *found = 1;
        }
        visited[x][y] = 1;
        path[*tail][0] = x;
        path[*tail][1] = y;
        (*tail)++;
    }
}
int can_exit(int row, int column, struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    int visited[CARPARK_ROWS][CARPARK_COLUMNS];
    int path[CARPARK_ROWS * CARPARK_COLUMNS + 1][2];
    int head = 0, tail = 1, found = 0;
    int direction[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int i = 0; i < CARPARK_ROWS; i++)
    {
        for (int j = 0; j < CARPARK_COLUMNS; j++)
        {
            visited[i][j] = 0;
        }
    }
    path[0][0] = row;
    path[0][1] = column;
    visited[row][column] = 1;
    while (!found && head < tail)
    {
        int i = path[head][0], j = path[head][1];
        int x, y;
        for (int k = 0; k < 4; k++)
        {
            x = i + direction[k][0];
            y = j + direction[k][1];
            can_exit_helper(x, y, visited, direction, path, head, &tail, &found, carpark);
        }
        head++;
    }
    return found;
}
///////////////////////////////////////////////////////////////////////////////
/////////////////////////// PROVIDED FUNCTIONS  ///////////////////////////////
///////////////////////////////////////////////////////////////////////////////
/**
 * Initialises carpark spaces as NORMAL spaces with NORMAL_PARKING_RATE that are
 * initially EMPTY.
 *
 * Parameters:
 *     carpark - The carpark to initialise.
 * Returns:
 *     Nothing.
 */
void initialise_carpark(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    for (int row = 0; row < CARPARK_ROWS; row++)
    {
        for (int col = 0; col < CARPARK_COLUMNS; col++)
        {
            carpark[row][col].type = NORMAL;
            carpark[row][col].parking_rate = NORMAL_PARKING_RATE;
            carpark[row][col].licence_plate = EMPTY;
            carpark[row][col].occupied_since = EMPTY;
            carpark[row][col].fee = 0;
            carpark[row][col].in_use = 0;
            carpark[row][col].bay_vincity = 0;
            carpark[row][col].stay_time = 0;
            carpark[row][col].cable_length = EMPTY;
            carpark[row][col].charge_from = 0;
            carpark[row][col].charge_till = 0;
            carpark[row][col].build_time = EMPTY;
        }
    }
}
/**
 * Prints all carpark spaces, displaying their type and the licence plate of
 * any cars parked in the carpark.
 *
 * Parameters:
 *     carpark   - The carpark to be printed
 * Returns:
 *     Nothing.
 */
void print_carpark(struct car_space carpark[CARPARK_ROWS][CARPARK_COLUMNS])
{
    printf("-------------------------------------------------------------------\n");
    for (int row = 0; row < CARPARK_ROWS; row++)
    {
        for (int col = 0; col < CARPARK_COLUMNS; col++)
        {
            print_car_space(carpark[row][col]);
        }
        printf("|\n");
        printf("-------------------------------------------------------------------\n");
    }
    printf("\n");
}
/**
 * Prints the type of a particular car space, as well as the licence plate of
 * the car located at that car space.
 *
 * Parameters:
 *     car_space         - The car space to print.
 *
 * Returns:
 *     Nothing.
 */
void print_car_space(struct car_space car_space)
{
    printf("|");
    if (car_space.type == NORMAL)
    {
        printf(" N :");
    }
    else if (car_space.type == PREMIUM)
    {
        printf(" P :");
    }
    else if (car_space.type == CARWASH_BAY)
    {
        printf(" W :");
    }
    else if (car_space.type == ELECTRIC_CHARGING_STATION)
    {
        printf(" CHARGER  ");
    }
    if (car_space.type != ELECTRIC_CHARGING_STATION)
    {
        if (car_space.licence_plate == EMPTY)
        {
            printf("      ");
        }
        else
        {
            printf(" %04d ", car_space.licence_plate);
        }
    }
}

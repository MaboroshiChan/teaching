# you and the graders will be able to compile and run as follows
# to compile task1, type the command: make task1
# to run task1, type the command: make run-task1
# ...and so on for task2 and task3
# Please implement only two tasks.

task1: crypto.o
        clang++ -Wall -o cryptoapp cryptoapp.cpp crypto.o
crypto.o: crypto.cpp crypto.h
        clang++ -Wall -c crypto.cpp

// add another target for your other task

clean:
        rm *.o

cpplint-task1: cryptoapp
        cpplint crypto.h crypto.cpp cryptoapp.cpp

run-task1: cryptoapp
        ./cryptoapp

// add cpplint and run targets for your other task

// your copyright...description....includes...
#include <iostream>
#include <fstream>
#include <string>

void printMenu(); // prints menu when program starts

using namespace std;
int main()
{
    ifstream myfile;

    while (1)
    {
        printMenu();
        int menu;
        cin >> menu;

        fstream source;
        fstream encrypted_file;
        fstream output_file;

        switch (menu)
        {
        case 1:
            /* Encrypt */
            source.open("bonk.txt", ios::in);
            if (source.is_open())
            {
                string line;
                std::ofstream outfile("encryted_file.txt");
                while (getline(source, line))
                { // read data from file object and put it into string.
                    for (char &c : line)
                    { // reference减少拷贝
                        c = c + 1;
                    }
                    /* output file */
                    outfile << line << std::endl;
                }
                outfile.close();
                source.close(); // close the file object.
            }
            break;
        case 2:
            encrypted_file.open("encrypted_file.txt");
            if (encrypted_file.is_open())
            {
                string line;
                while (getline(encrypted_file, line))
                { // read data from file object and put it into string.
                    for (char &c : line)
                    { // reference减少拷贝
                        c = c - 1;
                    }
                    cout << line << "\n";
                }
            }
            break;
        case 3:
            return 0;

        default:
            break;
        }
    }
    cin.get();
    return 0;
}

/**
int main() {


    std::ofstream source;
    source.open("bonk.txt");
    if(source.is_open()){
        // implement your solution...use the functions from the crypto.h header file
        char * line;
        std::cin.getline(source)

    }
    else {
        std::cout << "Cannot open the file" <<"\n";
    }

    std::cin.get();
    return 0;
}
*/

void printMenu()
{
    std::cout << "Welcome to My Cryptographic Techniques"
              << "\n";
    std::cout << "Please enter your selection:\n";
    std::cout << "1. Encrypt\n"
              << "2. Decrypt\n"
              << "3. Exit\n";
}

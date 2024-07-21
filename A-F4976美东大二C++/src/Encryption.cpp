#include<iostream>
#include <fstream>

void entryption(const std::string& source_str, std::string& result);
void decryption(const std::string& source_str, std::string& result);

int main(){

    std::cout << "Welcome to My Cryptographic Techniques" << "\n";
    std::cout << "Please enter your selection:\n";
    std::cout << "1. Encrypt\n" << "2. Decrypt\n" << "3. Exit\n";

    int num;
    std::cin >> num;

    std::string source_str;
    std::string result;

    switch (num)
    {
    case 1:
        /*Encryption code*/
        break;
    case 2:
        /*Decryption code*/
        break;
    case 3:
        /*Exit*/
        return 0;
    
    default:
        break;
    } 

    std::cout << "End of line";
    std::cin.get();
    return 0;
}
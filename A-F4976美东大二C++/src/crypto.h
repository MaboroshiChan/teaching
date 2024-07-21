#include<iostream>
using namespace std;

#ifndef CRYPTO_H
#define CRYPTO_H
// given a char c return the encrypted character
char encrypt(char c);
// given a char c retun the decrypted character
char decrypt(char c);
// given a reference to an open file, return a vectot with the # of characters, words, lines
std::vector<int> stats(ifstream& infile);
#endif

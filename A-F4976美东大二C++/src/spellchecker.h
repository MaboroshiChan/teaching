#include<iostream>


#ifndef SPELLCHECKER_H
#define SPELLCHECKER_H
// given a reference to a file, return a vector with misspelled words
std::vector<std::string> spellcheck(std::ifstream& infile, std::ifstream& dictionary);
// given a reference to a file, return a vector with the # of characters, words, lines
std::vector<int> stats(std::ifstream& infile);
#endif

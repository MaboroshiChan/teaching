// provide implementation to the functions in crypto.h
// no main() in this file

#include "crypto.h"
#include<vector>
#include<fstream>
#include<string>

int count_words(const std::string& text);

std::vector<int> stats(ifstream& infile){
    int charaters = 0;
    int words = 0;
    int lines = 0;
    std::string line;

    while(getline(infile, line)){
        lines++;
        for(auto& c: line){
            charaters++;
            words += count_words(line);
        }
    }

    return {charaters, words, lines};
}
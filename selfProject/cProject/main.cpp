/**
 * letterFrequency: 统计字母的频率
 *
 * wordFrequency: 统单词的频率
 *
 */
#include "export.h"
#include <iostream>
#include <cstring>
#include <iomanip>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<string, ll> PSL;

void outputLetterFrequency(string fileName){
	PCL letter[LEETERSIZE];
	ll sum = letterExport(letter, fileName);	
	cout << "字母出现的频率为:" << endl;
	for(int i = 0; i < LEETERSIZE; i++){
		cout << fixed << setprecision(2) << letter[i].first << ":" << letter[i].second * 100.0 / sum << "%" << endl;
	}
}

bool cmpWords(PSL p1, PSL p2){
	if(p1.second == p2.second)
		return p1.first > p2.first;
	return p1.second > p2.second;
}

template<class T>
void output(T words){
	vector<PSL> vec(words.begin(), words.end());
	sort(vec.begin(), vec.end(), cmpWords);
	for(auto& it : vec){
		cout << it.first << ":" << it.second << endl;
	}
}

void outputCountSortedWords(string fileName){
	MSI words = wordCount(fileName);
	cout << "单词个数为:" << endl;
	output(words);
}

void outputAfterStopWords(string stopFile, string fileName){
	MSI words = wordCount(fileName);
	MSI stopWords = wordCount(stopFile);
	for(auto& it : stopWords){
		words.erase(it.first);
	}
	cout << "单词个数为:" << endl;
	output(words);
}

void outputDirWords(string fileName){
	VS vec = getFileName(fileName);
	MSI words;
	for(auto& it : vec) {
		MSI w = wordCount(fileName + "/" + it);
		for(auto& it : w){
			words[it.first] += it.second;
		}
	}
	cout << "所有文件的单词个数为:" << endl;
	output(words);
}

int main(int argc, char* argv[]) {
    if(strcmp(argv[1], "-c") == 0){
        outputLetterFrequency(argv[2]);
    }
	else if(strcmp(argv[1], "-f") == 0){
		outputCountSortedWords(argv[2]);
	}
	else if(strcmp(argv[1], "-x") == 0 && strcmp(argv[3], "-f") == 0){
		outputAfterStopWords(argv[2], argv[4]);
	}
	else if(strcmp(argv[1], "-d") == 0){
		outputDirWords(argv[2]);
	}
    return 0;
}

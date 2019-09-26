#include "export.h"
#include <cstring>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <dirent.h>
#include <sys/stat.h>
#include <cstdio>
#include <vector>
using namespace std;

bool cmpLetter(PCL p1, PCL p2){
	if(p1.second == p2.second)
		return p1.first < p2.first;
	return p1.second > p2.second;
}

ifstream getInput(string fileName){
    ifstream input(fileName);
	if(!input.is_open()){
		cout << "文件不存在,请重新输入!" << endl;
		exit(-1);
	}
	return input;
}

ll letterExport(PCL* arr, string fileName) {
	ifstream input = getInput(fileName);
	ll sum = 0;
    string s;
    memset(arr, 0, sizeof(arr));
	for(int i = 0; i < LEETERSIZE; i++)
		arr[i].first = char(i + 'a');
    while (input >> s) {
        for (int i = 0; i < s.length(); i++) {
            char c = tolower(s[i]);
            if (c >= 'a' && c <= 'z') {
                int index = c - 'a';
                arr[index].second++;
				sum++;
            }
        }
    }
    input.close();
	sort(arr, arr + LEETERSIZE, cmpLetter);
	return sum;
}

VS getFileName(string file){
	const char* fileName = file.c_str();
	VS vec;
	DIR *directory_pointer;
	struct dirent *entry;
	if((directory_pointer=opendir(fileName))==NULL){
		exit(-1);
	} else {
		while((entry=readdir(directory_pointer))!=NULL){
			if(entry->d_name[0]=='.') continue;
			vec.push_back(entry->d_name);
		}
	}
	return vec;
}

MSI wordCount(string fileName){
	ifstream input = getInput(fileName);;
	MSI word;
	string s;
	string letter;
	while(getline(input, s)){
		for(int i = 0; i < s.length(); i++){
			char c = tolower(s[i]);
			if(c < 'a' || c > 'z'){
				if(letter !="")
					word[letter]++;
				letter = "";
				continue;
			}
			letter += c;
		}
		if(letter != ""){
			word[letter]++;
			letter = "";
		}
	}
	return word;
}


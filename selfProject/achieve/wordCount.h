/**
  输出单个文件中的前 N 个最常出现的英语单词。
  作用：一个用于统计文本文件中的英语单词出现频率的控制台程序单词：以英文字母开头，由英文字母和字母数字符号组成的字符串视为一个单词。单词以分隔符分割且不区分大小写。在输出时，所有单词都用小写字符表示。 
  英文字母：A-Z，a-z字母数字符号：A-Z，a-z，0-9分割符：空格,非字母数字符号 例：good123是一个单词，123good不是一个单词。good，Good和GOOD是同一个单词
  功能列表：
  功能1：wf.exe -f <file>  输出文件中所有不重复的单词，按照出现次数由多到少排列，出现次数同样多的，以字典序排列。
  功能2：wf.exe -d <directory>  指定文件目录，对目录下每一个文件执行  wf.exe -f <file> 的操作。 
  wf.exe -d -s  <directory>  同上， 但是会递归遍历目录下的所有子目录。
  功能3：支持 -n 参数，输出出现次数最多的前 n 个单词，  例如，  -n 10 就是输出最常出现单词的前 10 名。 当没有指明数量的时候，我们默认列出所有单词的频率。
 *
 */

#ifndef _WORDCOUNT_H_
#define _WORDCOUNT_H_

#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

typedef pair<string, int> PSI;
map<string, int> word;

bool cmp1(PSI a, PSI b){
	if(a.second == b.second)
		return a.first < b.first;
	return a.second > b.second;
}

int wordCount(string fileName){
	ifstream input(fileName);
	if(!input.is_open()){
		cout << "文件不存在,请重新输入!" << endl;
		return 0;
	}
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
	}
	if(letter != "")
		word[letter]++;
	vector<PSI> vec(word.begin(), word.end());
	sort(vec.begin(), vec.end(), cmp1);
	cout << "文件中的单词及其个数:" << endl;
	for(auto& it : vec){
		cout << it.first << " " << it.second << endl;
	}
	return 0;
}

#endif

/**
 *
第二步: 
支持 stop words
我们从第一步的结果看出，在一本小说里， 频率出现最高的单词一般都是 "a",  "it", "the", "and", "this", 这些词， 我们并不感兴趣.  我们可以做一个 stop word 文件 （停词表）， 在统计词汇的时候，跳过这些词。  我们把这个文件叫 "stopwords.txt" file. 
功能 4：支持新的命令行参数, 例如：   wf.exe -x <stopwordfile>  -f <file> 
在这一步我们要增加什么回归测试呢？
 *
 */

#ifndef _JUMPOVER_H_
#define _JUMPOVER_H_ 

#include "wordCount.h"

MSI jumpOverWords(string fileName, string stopWordFile){
	MSI word, stopWord;
	word = wordCount(fileName);
	stopWord = wordCount(stopWordFile);
	for(auto& it : stopWord){
		word.erase(it.first);
	}
	return word;
}

void outputAfterStopWords(string stopWordFile, string fileName){
	MSI word;
	word = jumpOverWords(fileName, stopWordFile);
	output(word);
}

#endif

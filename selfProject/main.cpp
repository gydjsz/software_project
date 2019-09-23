/**
 * letterFrequency: 统计字母的频率
 *
 * wordFrequency: 统单词的频率
 *
 */
#include <cstring>
#include "achieve/letterFrequency.h"
#include "achieve/wordCount.h"
#include "achieve/jumpOver.h"

int main(int argc, char* argv[]) {
    if(strcmp(argv[1], "-c") == 0){
        letterFrequency(argv[2]);
    }
	else if(strcmp(argv[1], "-f") == 0){
		countSortedWords(argv[2]);
	}
	else if(strcmp(argv[1], "-x") == 0 && strcmp(argv[3], "-f") == 0){
		outputAfterStopWords(argv[2], argv[4]);
	}
    return 0;
}

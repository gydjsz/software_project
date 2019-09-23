/**
 * letterFrequency: 统计字母的频率
 *
 * wordFrequency: 统单词的频率
 *
 */
#include <cstring>
#include "achieve/letterFrequency.h"
#include "achieve/wordCount.h"

int main(int argc, char* argv[]) {
    if(strcmp(argv[1], "-c") == 0){
        letterFrequency(argv[2]);
    }
	else if(strcmp(argv[1], "-f") == 0){
		wordCount(argv[2]);
	}
    return 0;
}

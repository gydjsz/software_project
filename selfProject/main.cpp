/**
第0步：
输出某个英文文本文件中 26
字母出现的频率，由高到低排列，并显示字母出现的百分比，精确到小数点后面两位。
命令行参数是：
wf.exe -c <file name> 
字母频率 = 这个字母出现的次数 / （所有A-Z，a-z字母出现的总数）
如果两个字母出现的频率一样，那么就按照字典序排列。  如果 S 和 T
出现频率都是 10.21%， 那么， S 要排在T 的前面。 这个程序容易写吧？ 
如果要处理一本大部头小说 （例如 Gone With The Wind),
你的程序效率如何？有没有什么可以优化的地方？
 *
 */

#include <cstring>
#include "wordFrequency.h"

int main(int argc, char* argv[]) {
    if(strcmp(argv[1], "-c") == 0){
        wordFrequency(argv[2]);
    }
    return 0;
}

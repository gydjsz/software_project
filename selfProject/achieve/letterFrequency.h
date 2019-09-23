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

#ifndef _WORLDFREQUENCY_H_
#define _WORLDFREQUENCY_H_

#include <algorithm>
#include <cstring>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

typedef long long ll;
typedef pair<int, ll> PIL;

bool cmp(PIL a, PIL b) {
    if (a.second == b.second)
        return a.first < b.first;
    return a.second > b.second;
}

int letterFrequency(string fileName) {
    ifstream input(fileName);
	if(!input.is_open()){
		cout << "文件不存在,请重新输入!" << endl;
		return 0;
	}
    string s;
    int size = 26;
    ll sum = 0;
    PIL arr[size];
    memset(arr, 0, sizeof(arr));
    while (input >> s) {
        for (int i = 0; i < s.length(); i++) {
            char c = tolower(s[i]);
            if (c >= 'a' && c <= 'z') {
                int index = c - 'a';
                arr[index].first = index;
                arr[index].second++;
                sum++;
            }
        }
    }
    input.close();
    sort(arr, arr + size, cmp);
	cout << "各字母出现的频率为:" << endl;
    for (int i = 0; i < size; i++) {
        if (arr[i].second != 0)
            cout << fixed << setprecision(2) << char(arr[i].first + 'a') << ":"
                 << arr[i].second * 100.0 / sum << "%" << endl;
    }
	return 0;
}
#endif

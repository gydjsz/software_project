#include <algorithm>
#include <cstring>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

#ifndef _WORLDFREQUENCY_H_
#define _WORLDFREQUENCY_H_

typedef long long ll;
typedef pair<int, ll> P;

bool cmp(P a, P b) {
    if (a.second == b.second)
        return a.first < b.first;
    return a.second > b.second;
}

int wordFrequency(string fileName) {
    ifstream input(fileName);
	if(!input.is_open()){
		cout << "文件不存在,请重新输入!" << endl;
		return 0;
	}
    string s;
    int size = 26;
    ll sum = 0;
    P arr[size];
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

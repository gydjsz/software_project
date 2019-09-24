#ifndef _EXPORT_H
#define _EXPORT_H

#include <map>
using namespace std;

typedef long long ll;
typedef pair<char, ll> PCL;
const int LEETERSIZE = 26;
typedef pair<string, int> PSI;
typedef map<string, int> MSI;

ll letterExport(PCL* arr, string fileName);
ifstream getInput(string fileName);
MSI wordCount(string fileName);

#endif

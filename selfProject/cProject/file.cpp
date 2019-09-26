#include <dirent.h>
#include <iostream>
#include <sys/stat.h>
#include <stdio.h>
using namespace std;

bool IsDirectory(string pszFilePath)
{
//	DWORD dwAttr = GetFileAttributes(pszFilePath);
	struct stat s;
	 if(s.st_mode & S_IFDIR)
	//if (dwAttr & FILE_ATTRIBUTE_DIRECTORY)
	{//是目录
		return true;
	}
	else
	{//是文件
		return false;
	}
}

int getFileName(char* fileName){
	DIR *directory_pointer;
	struct dirent *entry;
	if((directory_pointer=opendir(fileName))==NULL){
		printf("Error open\n");
		return 0;
	} else {
		while((entry=readdir(directory_pointer))!=NULL){
			if(entry->d_name[0]=='.') continue;
			// if(IsDirectory(entry->d_name))
				// getFileName(entry->d_name);
			printf("%s\n",entry->d_name);
		}
	}
}


int main(){
	string fileName = "file.cpp";
	// getFileName("test");
	struct stat s;
	if(IsDirectory(fileName))
		cout << "DIR" << endl;
	else
		cout << "FILE" << endl;
	return 0;
}

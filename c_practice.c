
#include <stdio.h>
#include <string.h>

/* 
TO COMPILE: clang c_practice.c -o c_practice
TO RUN: ./c_practice
*/


void reverse(char* str) {
	int i = 0;
	int j = strlen(str) - 1;
	char c;


	while (i < j) {
		char c = str[i];
		str[i] = str[j];
		str[j] = c; 
		j--;
		i++;
	}
}

int main() {
	char str[] = "hello";
	reverse(str);
	printf("reversed string: %s\n", str);
	return 0;
}
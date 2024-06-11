#include <stdio.h>
#include <stdint.h>
#include <string.h>

void main(){

    char local_143 [11];

    uint64_t local_158;
    uint64_t local_150;
    uint64_t local_160;
    uint64_t local_148;


    local_158 = 0x657274;
    local_150 = 0x746e69;
    local_160 = 0x795f61;
    local_148 = 0x6f6e755f;


    strcpy(local_143,(char *)&local_158);
    strcat(local_143,(char *)&local_150);
    strcat(local_143,(char *)&local_160);
    strcat(local_143,(char *)&local_148);


    printf("Key: %s\n",local_143);

}
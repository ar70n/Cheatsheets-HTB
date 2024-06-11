#include <stdio.h>
#include <stdlib.h>

void main()
{
    char param_1[64];
    long uVar1;
    int i;
    for (i = 0; i < 0x40; i++)
    {

        param_1[i++] = 48;

    }

    i=0;        
    
    while (param_1[0] != 0x4c)
    {
        param_1[i]++;
        
    }
    i++;

    while ((*param_1 ^ param_1[1]) != 0x29)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[1] + (int)(char)param_1[2] != 0xd9)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[2] + (int)(char)param_1[3] != 0xe7)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[3] ^ param_1[4]) != 0x30)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[4] + (int)(char)param_1[5] != 0x97)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[5] ^ param_1[6]) != 0x12)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[6] ^ param_1[7]) != 0x3d)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[7] ^ param_1[8]) != 0x4e)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[9] - (int)(char)param_1[8] != 0x44)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[10] - (int)(char)param_1[9] != -0xc)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0xb] - (int)(char)param_1[10] != -0xb)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0xb] ^ param_1[0xc]) != 0x52)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0xc] ^ param_1[0xd]) != 0x5c)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0xe] - (int)(char)param_1[0xd] != -0x3b)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0xe] ^ param_1[0xf]) != 0x52)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0xf] + (int)(char)param_1[0x10] !=
           0xc2)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0x10] + (int)(char)param_1[0x11] !=
           0x93)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x11] + (int)(char)param_1[0x12] != 0xa2)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0x12] ^ param_1[0x13]) != 0x5a)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x13] ^ param_1[0x14]) != 0x58)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x15] -
               (int)(char)param_1[0x14] !=
           -3)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x15] +
               (int)(char)param_1[0x16] !=
           0x9e)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x16] ^ param_1[0x17]) != 4)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x18] -
               (int)(char)param_1[0x17] !=
           0x42)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0x18] ^ param_1[0x19]) != 0x2c)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0x19] +
               (int)(char)param_1[0x1a] !=
           0xd3)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x1b] -
               (int)(char)param_1[0x1a] !=
           -0x44)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0x1b] +
               (int)(char)param_1[0x1c] !=
           0x60)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x1c] ^ param_1[0x1d]) != 0x5c)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0x1d] +
               (int)(char)param_1[0x1e] !=
           0xcb)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x1e] +
               (int)(char)param_1[0x1f] !=
           0xc2)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x1f] ^
            param_1[0x20]) != 0x53)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x20] +
               (int)(char)param_1[0x21] !=
           0x9d)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x21] +
               (int)(char)param_1[0x22] !=
           0xdd)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x22] +
               (int)(char)param_1[0x23] !=
           0xdc)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x24] -
               (int)(char)param_1[0x23] !=
           -0x39)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x24] ^ param_1[0x25]) != 0x47)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x25] ^ param_1[0x26]) != 0x47)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x27] -
               (int)(char)param_1[0x26] !=
           0x31)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x28] -
               (int)(char)param_1[0x27] !=
           -5)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x28] ^
            param_1[0x29]) != 0x6a)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x29] ^ param_1[0x2a]) != 0x40)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x2a] ^ param_1[0x2b]) != 0x16)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x2b] +
               (int)(char)param_1[0x2c] !=
           0xc6)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x2c] +
               (int)(char)param_1[0x2d] !=
           0x96)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x2d] ^ param_1[0x2e]) != 0x40)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x2e] ^ param_1[0x2f]) != 0x46)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x30] -
               (int)(char)param_1[0x2f] !=
           0x31)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x30] ^
            param_1[0x31]) != 0x13)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x31] ^ param_1[0x32]) != 0x19)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x32] +
               (int)(char)param_1[0x33] !=
           0xd8)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x34] -
               (int)(char)param_1[0x33] !=
           0xd)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x35] -
               (int)(char)param_1[0x34] !=
           -0x1a)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x36] -
               (int)(char)param_1[0x35] !=
           0xf)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x36] ^ param_1[0x37]) != 0x5e)
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x37] ^
            param_1[0x38]) != 0x6f)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x39] -
               (int)(char)param_1[0x38] !=
           -0x2c)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x39] +
               (int)(char)param_1[0x3a] !=
           0xa5)
    {
        param_1[i]++;
        
    }
    i++;
    while (param_1[0x3b] != param_1[0x3a])
    {
        param_1[i]++;
        
    }
    i++;
    while ((param_1[0x3b] ^ param_1[0x3c]) !=
           0x42)
    {
        param_1[i]++;
        
    }
    i++;

    while ((param_1[0x3c] ^ param_1[0x3d]) !=
           0x42)
    {
        param_1[i]++;
        
    }
    i++;

    while ((int)(char)param_1[0x3e] -
               (int)(char)param_1[0x3d] !=
           0xb)
    {
        param_1[i]++;
        
    }
    i++;
    while ((int)(char)param_1[0x3e] +
               (int)(char)param_1[0x3f] !=
           0x87)
    {
        param_1[i]++;
        
    }
    i++;

    printf("Password: %s", param_1);

    exit(0);
}


#include <stdint.h>

void main(void)
{
  uint64_t local_a8;
  uint64_t local_a0;
  uint64_t local_98;
  uint64_t local_90;
  uint64_t local_88;
  uint64_t local_80;
  uint64_t local_78;
  uint32_t local_70;
  uint32_t uStack_6c;
  uint32_t uStack_68;
  uint64_t local_64;
  uint32_t local_58[19];
  int local_c;
  
  local_58[0] = 0xc3b4d58c;
  local_58[1] = 0xcb86e483;
  local_58[2] = 0xefb580b9;
  local_58[3] = 0xd4f383ae;
  local_58[4] = 0xdc9f849f;
  local_58[5] = 0x809fc4f0;
  local_58[6] = 0x81b4efa6;
  local_58[7] = 0x849fd5ad;
  local_58[8] = 0x839fd4ae;
  local_58[9] = 0xc2f0d6a6;
  local_58[10] = 0x80b4efb4;
  local_58[11] = 0xdcf0c39f;
  local_58[12] = 0xc29f83b6;
  local_58[13] = 0xc2f3c6f3;
  local_58[14] = 0xd7ae81b3;
  local_58[15] = 0x88f0829f;
  local_58[16] = 0xd2f784a2;
  local_58[17] = 0xd3f686a4;
  local_58[18] = 0xb0c0cdf2;
  local_a8 = 0;
  local_a0 = 0;
  local_98 = 0;
  local_90 = 0;
  local_88 = 0;
  local_80 = 0;
  local_78 = 0;
  local_70 = 0;
  uStack_6c = 0;
  uStack_68 = 0;
  local_64 = 0;
  for (local_c = 0; local_c < 0x13; local_c = local_c + 1) {
    *(uint8_t *)((uint64_t)&local_a8 + (uint64_t)(local_c << 2)) = (uint8_t)local_58[local_c] ^ 0xc0;
    *(uint8_t *)((uint64_t)&local_a8 + (uint64_t)(local_c * 4 + 1)) =
         (uint8_t)((uint32_t)local_58[local_c] >> 8) ^ 0xb0;
    *(uint8_t *)((uint64_t)&local_a8 + (uint64_t)(local_c * 4 + 2)) =
         (uint8_t)((uint32_t)local_58[local_c] >> 0x10) ^ 0xc0;
    *(uint8_t *)((uint64_t)&local_a8 + (uint64_t)(local_c * 4 + 3)) =
         (uint8_t)((uint32_t)local_58[local_c] >> 0x18) ^ 0xb0;
  }

  
  puts((char *)&local_a8);
  return;
}


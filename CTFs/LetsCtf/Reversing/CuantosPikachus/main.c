
undefined8 main(void)

{
  int iVar1;
  basic_ostream *pbVar2;
  basic_ostream<> *this;
  long in_FS_OFFSET;
  undefined2 local_178;
  undefined local_176;
  undefined2 local_175;
  undefined local_173;
  undefined2 local_172;
  undefined local_170;
  undefined2 local_16f;
  undefined local_16d;
  undefined2 local_16c;
  undefined local_16a;
  undefined2 local_169;
  undefined local_167;
  undefined2 local_166;
  undefined local_164;
  undefined2 local_163;
  undefined local_161;
  undefined4 local_160;
  undefined4 local_15c;
  undefined4 local_158;
  undefined4 local_154;
  undefined4 local_150;
  undefined4 local_14c;
  undefined4 local_148;
  undefined local_144;
  char local_143 [11];
  char local_138 [32];
  char local_118 [264];
  long local_10;
  
  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  local_160 = 0x795f61;
  local_178 = 0x3150;
  local_176 = 0;
  local_15c = 0x6e3053;
  local_175 = 0x746e;
  local_173 = 0;
  local_172 = 0x3133;
  local_170 = 0;
  local_158 = 0x657274;
  local_16f = 0x5f34;
  local_16d = 0;
  local_154 = 0x63346b;
  local_148 = 0x6f6e755f;
  local_144 = 0;
  local_150 = 0x746e69;
  banner();
  strcpy(local_143,(char *)&local_158);
  strcat(local_143,(char *)&local_150);
  pbVar2 = std::operator<<((basic_ostream *)std::cout," un pikachu, dos pikachus... ");
  pbVar2 = (basic_ostream *)std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar2,std::endl<>);
  std::operator<<(pbVar2," > ");
  std::operator>>((basic_istream *)std::cin,local_118);
  strcat(local_143,(char *)&local_160);
  strcat(local_143,(char *)&local_148);
  iVar1 = strcmp(local_118,local_143);
  if (iVar1 == 0) {
    local_16c = 0x7254;
    local_16a = 0;
    local_169 = 0x5568;
    local_167 = 0;
    local_14c = 0x5f6e75;
    local_166 = 0x5f79;
    local_164 = 0;
    local_163 = 0x5f73;
    local_161 = 0;
    strcpy(local_138,(char *)&local_16c);
    strcat(local_138,(char *)&local_172);
    strcat(local_138,(char *)&local_175);
    strcat(local_138,(char *)&local_16f);
    strcat(local_138,(char *)&local_166);
    strcat(local_138,(char *)&local_14c);
    strcat(local_138,(char *)&local_178);
    strcat(local_138,(char *)&local_154);
    strcat(local_138,(char *)&local_169);
    strcat(local_138,(char *)&local_163);
    strcat(local_138,(char *)&local_15c);
    pbVar2 = (basic_ostream *)
             std::basic_ostream<>::operator<<((basic_ostream<> *)std::cout,std::endl<>);
    pbVar2 = std::operator<<(pbVar2,"LetsCTF{");
    pbVar2 = std::operator<<(pbVar2,local_138);
    pbVar2 = std::operator<<(pbVar2,"} ");
    this = (basic_ostream<> *)
           std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar2,std::endl<>);
    std::basic_ostream<>::operator<<(this,std::endl<>);
  }
  else {
    pbVar2 = std::operator<<((basic_ostream *)std::cout," No son los pikachu suficientess :( ");
    std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar2,std::endl<>);
  }
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 0;
}



undefined8 main(void)

{
  byte bVar1;
  bool bVar2;
  basic_ostream *pbVar3;
  size_t longitud;
  ulong uVar4;
  byte *pbVar5;
  char *pcVar6;
  basic_string<> local_e8 [32];
  basic_string<> local_c8 [36];
  char input_inicial [10];
  allocator pointer;
  allocator local_99;
  basic_string<> local_98 [47];
  allocator local_69;
  undefined local_68 [8];
  undefined local_60 [8];
  allocator *local_58;
  allocator *local_50;
  allocator *local_48;
  char *local_40;
  char *input_secundario;
  ulong j;
  ulong i;
  int local_1c;
  
  banner();


  pbVar3 = std::operator<<((basic_ostream *)std::cout,"  Deactivation Code ");
  pbVar3 = (basic_ostream *)std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar3,std::endl<>);
  std::operator<<(pbVar3,"  > ");
  std::operator>>((basic_istream *)std::cin,input_inicial);
  longitud = strlen(input_inicial);
  if (longitud != 8) {
    bomb();
  }
  for (local_1c = 0; local_1c < 8; local_1c = local_1c + 1) {
    if (('9' < input_inicial[local_1c]) || (input_inicial[local_1c] < '0')) {
      bomb();
    }
  }
  input_secundario = input_inicial;
  local_48 = &pointer;


                    /* try { // try from 00102693 to 00102697 has its CatchHandler @ 00102992 */
  std::__cxx11::basic_string<>::basic_string<>(local_c8,&DAT_001044c2,&pointer);
  std::__new_allocator<char>::~__new_allocator((__new_allocator<char> *)&pointer);
  i = 0;
  while( true ) {
    uVar4 = std::__cxx11::basic_string<>::length();
    if (uVar4 <= i) break;
    longitud = strlen(input_secundario);
    bVar1 = input_secundario[i % longitud];
                    /* try { // try from 001026ee to 0010276e has its CatchHandler @ 001029ff */
    pbVar5 = (byte *)std::__cxx11::basic_string<>::operator[]((ulong)local_c8);
    *pbVar5 = ~(*pbVar5 ^ bVar1);
    i = i + 1;
  }
  local_40 = (char *)std::__cxx11::basic_string<>::c_str();
  if ((((local_40[1] != 'q') || (local_40[8] != '\x10')) || (local_40[0xb] != '&')) ||
     (local_40[0xe] != '\x05')) {
    bomb();
  }


  local_50 = &local_99;
                    /* try { // try from 00102797 to 0010279b has its CatchHandler @ 001029b0 */
  std::__cxx11::basic_string<>::basic_string<>(local_e8,&DAT_001044d8,&local_99);
  std::__new_allocator<char>::~__new_allocator((__new_allocator<char> *)&local_99);
  j = 0;
  while( true ) {
    uVar4 = std::__cxx11::basic_string<>::length();
    if (uVar4 <= j) break;
    longitud = strlen(local_40);
    bVar1 = local_40[j % longitud]; // esto es lo mismo que local_40[j]
                    /* try { // try from 001027f2 to 001028d3 has its CatchHandler @ 001029eb */
    pbVar5 = (byte *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
    *pbVar5 = ~(*pbVar5 ^ bVar1);
    j = j + 1;
  }
  pcVar6 = (char *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
  if (*pcVar6 == 's') {
    pcVar6 = (char *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
    if (*pcVar6 == 't') {
      pcVar6 = (char *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
      if (*pcVar6 == '4') {
        pcVar6 = (char *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
        if (*pcVar6 == 'e') {
          pcVar6 = (char *)std::__cxx11::basic_string<>::operator[]((ulong)local_e8);
          if (*pcVar6 == 'L') {
            bVar2 = false;
            goto LAB_001028b2;
          }
        }
      }
    }
  }
  bVar2 = true;
LAB_001028b2:
  if (bVar2) {
    bomb();
  }
  pbVar3 = (basic_ostream *)
           std::basic_ostream<>::operator<<((basic_ostream<> *)std::cout,std::endl<>);
  local_58 = &local_69;
  std::__cxx11::basic_string<>::rend();
  std::__cxx11::basic_string<>::rbegin();
                    /* try { // try from 00102923 to 00102927 has its CatchHandler @ 001029d9 */
  std::__cxx11::basic_string<>::basic_string<>
            (local_98,(reverse_iterator)local_60,(reverse_iterator)local_68,&local_69);
                    /* try { // try from 00102935 to 0010294b has its CatchHandler @ 001029c5 */
  pbVar3 = std::operator<<(pbVar3,(basic_string *)local_98);
  std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar3,std::endl<>);
  std::__cxx11::basic_string<>::~basic_string(local_98);
  std::__new_allocator<char>::~__new_allocator((__new_allocator<char> *)&local_69);
  std::__cxx11::basic_string<>::~basic_string(local_e8);
  std::__cxx11::basic_string<>::~basic_string(local_c8);
  return 0;
}


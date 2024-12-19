
undefined8 main(void)

{
  byte bVar1;
  char cVar2;
  size_t longitud;
  byte *pbVar3;
  basic_ostream *pbVar4;
  char *pcVar5;
  ulong uVar6;
  long in_FS_OFFSET;
  allocator local_456;
  char is_email;
  int i;
  ulong local_450;
  basic_string<> local_448 [32];
  char mail [512];
  byte my_serial [4];
  char my_serial_4;
  char my_serial_5;
  char my_serial_6;
  char my_serial_7;
  char my_serial_8;
  char my_serial_9;
  byte my_serial_10;
  char my_serial_11;
  char my_serial_12;
  char my_serial_13;
  char my_serial_14;
  char my_serial_15;
  char my_serial_16;
  char my_serial_17;
  char my_serial_18;
  char my_serial_19;
  char my_serial_20;
  byte my_serial_21;
  char my_serial_22;
  char my_serial_23;
  char my_serial_24;
  char my_serial_25;
  long local_20;
  
  local_20 = *(long *)(in_FS_OFFSET + 0x28);
  printf(" Enter your Mail\n > ");
  std::operator>>((basic_istream *)std::cin,mail);
  printf(" Enter Serial\n > ");
  std::operator>>((basic_istream *)std::cin,(char *)my_serial);
  i = 0;
  while( true ) {
    uVar6 = (ulong)i;
    longitud = strlen(mail);
    if (longitud <= uVar6) break;
    if (mail[i] == '@') {
      is_email = '\x01';
    }
    i = i + 1;
  }
  if (is_email == '\x01') {
    longitud = strlen(mail);
    if (3 < longitud) goto LAB_00102670;
  }
  salida();
LAB_00102670:
  longitud = strlen((char *)my_serial);
  if (longitud < 0x19) {
    salida();
  }
  if (((my_serial_5 != '-') && (my_serial_11 != '-')) && (my_serial_18 != '-')) {
    salida();
  }
  if (my_serial[0] != my_serial_10) {
    salida();
  }
  if (my_serial[1] != 'z') {
    salida();
  }
  if (my_serial[3] != 'y') {
    salida();
  }
  if (my_serial_25 != '\0') {
    salida();
  }
  if (my_serial[2] != 'e') {
    salida();
  }
  if ((int)my_serial_4 != my_serial_17 + 2) {
    salida();
  }
  if (my_serial_6 != 'd') {
    salida();
  }
  if (my_serial_7 != 'r') {
    salida();
  }
  if (my_serial_8 != my_serial_22) {
    salida();
  }
  if (my_serial_9 != 'L') {
    salida();
  }
  cVar2 = check(my_serial_5,my_serial_12,9);
  if (cVar2 != '\x01') {
    salida();
  }
  if ((int)my_serial_23 != my_serial_17 + 1) {
    salida();
  }
  if (my_serial_13 != 't') {
    salida();
  }
  if (my_serial_14 != 'f') {
    salida();
  }
  cVar2 = check(my_serial_15,my_serial_16,-0x86);
  if (cVar2 != '\x01') {
    salida();
  }
  if (my_serial_21 != 0x54) {
    salida();
  }
  if (my_serial_16 != 'H') {
    salida();
  }
  if (my_serial_20 != 'u') {
    salida();
  }
  if (my_serial_17 != '5') {
    salida();
  }
  if (my_serial_19 != 'p') {
    salida();
  }
  if (my_serial_22 != 'F') {
    salida();
  }
  if (my_serial_10 != my_serial_21) {
    salida();
  }
  cVar2 = check(my_serial_24,my_serial_20,-0x3d);
  if (cVar2 != '\x01') {
    salida();
  }
  std::allocator<char>::allocator();
                    /* try { // try from 001028d9 to 001028dd has its CatchHandler @ 00102a07 */
  std::__cxx11::basic_string<>::basic_string<>(local_448,&DAT_0010304e,&local_456);
  std::allocator<char>::~allocator((allocator<char> *)&local_456);
  local_450 = 0;
  while( true ) {
    uVar6 = std::__cxx11::basic_string<>::length();
    if (uVar6 <= local_450) break;
    longitud = strlen((char *)my_serial);
    bVar1 = my_serial[local_450 % longitud];
                    /* try { // try from 0010293d to 001029e1 has its CatchHandler @ 00102a28 */
    pbVar3 = (byte *)std::__cxx11::basic_string<>::operator[]((ulong)local_448);
    *pbVar3 = ~(*pbVar3 ^ bVar1);
    local_450 = local_450 + 1;
  }
  pbVar4 = (basic_ostream *)
           std::basic_ostream<>::operator<<((basic_ostream<> *)std::cout,std::endl<>);
  pbVar4 = std::operator<<(pbVar4,"  LetsCTF{");
  pcVar5 = (char *)std::__cxx11::basic_string<>::c_str();
  pbVar4 = std::operator<<(pbVar4,pcVar5);
  pbVar4 = std::operator<<(pbVar4,"}");
  std::basic_ostream<>::operator<<((basic_ostream<> *)pbVar4,std::endl<>);
  std::__cxx11::basic_string<>::~basic_string(local_448);
  if (local_20 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return 0;
}


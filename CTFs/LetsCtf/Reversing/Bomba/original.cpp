//Codigo descompilado con Ghidra modificado para que compile y replique la bomba original

#include <iostream>
#include <cstring>
#include <unistd.h>

using namespace std;

void banner ()
{
	printf("\n             .--``..---.           \n");
	printf("         .````--:ohdmNms/`         \n");
	printf("          -:/+++/-.:smNd+          \n");
	printf("       ```..--:ohmNNdhh.           \n");
	printf("     `-. `.``.-+sosshd.         :. \n");
	printf("   -os--/sosdmmNNMMNy         .+// \n");
	printf("  :h+.+hNNMMMNNNMMNm/      `/yNN.` \n");
	printf(" .do/oNNMMMMMmohs+:`    .+hNMMMM-` \n");
	printf(" `yohNhNNNMh-           dosNMMMmo- \n");
	printf("  -mN+hMMMy             .smNMNdd/+`\n");
	printf("   yN.hMMh               +NMMNmhds:\n");
	printf("   +N//m+                 .osshyho \n");
	printf("  ..smhh                           \n");
	printf("   ::oNmy-                         \n");
	printf("      .//yhs/:`                    \n");
	printf("          :ymNN/                   \n");
	printf("         .-+shdho.                 \n");
	printf("             `.--..`               \n");
	printf("   _______ ___ \n");
	printf("  | 7 | 8 | 9 |\n");
	printf("  |___|___|___|\n");
	printf("  | 4 | 5 | 6 |\n");
	printf("  |___|___|___|\n");
	printf("  | 1 | 2 | 3 |\n");
	printf("  |___|___|___|\n");
	printf("  | 0 | enter |\n");
	printf("  |___|_______|\n");
}
void bomb()
{
	printf("B");
	for (int i=0; i<60; i++) {
		printf("O");
		fflush(stdout);
		usleep(5000);
	}
	printf("M!\n");
	printf("       _.-^^---....,,--       \n");
	printf("   _--                  --_   \n");
	printf("  <                        >) \n");
	printf("  |                         | \n");
	printf("   \\._                   _./  \n");
	printf("      ```--. . , ; .--'''     \n");
	printf("            | |   |           \n");
	printf("         .-=||  | |=-.        \n");
	printf("         `-=#$%&%$#=-'        \n");
	printf("            | ;  :|           \n");
	printf("   _____.,-#%&$@%#&#~,._____  \n");
	exit(0);
}
int main(int argc, char **argv)
{
	char input[10];
	banner();
	cout << "  Deactivation Code " << endl << "  > ";
	cin >> input;

	if (strlen(input) !=8) bomb();
	for (int i = 0; i < 8; i++) {
		if (input[i] > 57 || input[i] < 48) bomb();
	}

	char * password1 = input;
	std::string decode1 =  "\xfa\xba\xfe\xbd\xe9\xc2\x82\xce\xd8\xe3\xfe\xeb\xf8\xc1\xc2\x82\xba\xfe";
	for ( size_t i=0 ; i < decode1.length() ; i++ ) {
		decode1[i] ^= ~password1[i % strlen(password1)];
		}

	const char * password2 = decode1.c_str();
	if (password2[1]!='q' || password2[8]!='\x10' || password2[11]!='&' || password2[14]!='\x05') bomb();

	std::string decode2 = "\xb0\xec\xa3\xbf\xb2\xae\xc8\xce\x83\xb4\xbb\xb7\x9e\x96\xc9\x81\xb9\xbc\xfc\xb9\xad\xbb\xe3\x95\xc1\xbc\xbb\x94\xbd\xad\xa4\xbe";
	for ( size_t i=0 ; i < decode2.length() ; i++ ) {
		decode2[i] ^= ~password2[i % strlen(password2)];
		}

	if (decode2[28]!='s' || decode2[29]!='t' || decode2[7]!='4' || decode2[30]!='e' || decode2[31]!='L') bomb();
	std::cout << std::endl   << string(decode2.rbegin(), decode2.rend()) << std::endl;
	return 0;
}

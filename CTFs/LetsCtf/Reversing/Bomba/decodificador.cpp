#include <iostream>
#include <string>
#include <cstring>

void bomb() {
    std::cout << "Bomb exploded!" << std::endl;
    exit(1);
}

int main() {
	std::string decode1 =  "\xfa\xba\xfe\xbd\xe9\xc2\x82\xce\xd8\xe3\xfe\xeb\xf8\xc1\xc2\x82\xba\xfe";
	std::string decode2 = "\xb0\xec\xa3\xbf\xb2\xae\xc8\xce\x83\xb4\xbb\xb7\x9e\x96\xc9\x81\xb9\xbc\xfc\xb9\xad\xbb\xe3\x95\xc1\xbc\xbb\x94\xbd\xad\xa4\xbe";

    for (int i = 0; i < 100000000; i++) {
        std::string password1 = std::to_string(i);
        password1 = std::string(8 - password1.length(), '0') + password1;  // Pad with zeros

        std::string temp1 = decode1;
        for (size_t j = 0; j < temp1.length(); j++) {
            temp1[j] ^= ~password1[j % password1.length()];
        }

    	if (temp1[1]!='q' || temp1[8]!='\x10' || temp1[11]!='&' || temp1[14]!='\x05') continue;
    
        const char* password2 = temp1.c_str();
        std::string temp2 = decode2;
        for (size_t j = 0; j < temp2.length(); j++) {
            temp2[j] ^= ~password2[j % strlen(password2)];
        }

    	if (temp2[28]!='s' || temp2[29]!='t' || temp2[7]!='4' || temp2[30]!='e' || temp2[31]!='L') continue;



        std::cout << "Found valid input: " << password1 << std::endl;
        break;
    }

    return 0;
}
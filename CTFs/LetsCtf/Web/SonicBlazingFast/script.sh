	echo "\nPILLAMOS LA COOKIE:" 
	cookie=$(curl -i -s  -X POST -d "shellmates=" http://chllngs.letsctf.com:41513/index.php | grep 'PHPSESSID' | cut -d '=' -f2 | cut -d ';' -f1 )

	echo "\n$cookie \n"


	echo "PILLAMOS EL GET-FLAG\n" 

	decoded=$(curl -s -i 'http://chllngs.letsctf.com:41513/index.php' --compressed -X POST --data-raw 'shellmates=' -b "PHPSESSID=$cookie" | tee output | grep '==' | cut -d ' ' -f2 | base64 --decode 2>/dev/null)

	echo "EL GET-FLAG DECODIFICADO:\n"  
	echo "$decoded"
	echo "\nENVIAMOS EL GET-FLAG DECODIFICADO\n"  


 	curl -i -X POST -d "shellmates=$decoded" -b "PHPSESSID=$cookie"   http://chllngs.letsctf.com:41513/index.php > output

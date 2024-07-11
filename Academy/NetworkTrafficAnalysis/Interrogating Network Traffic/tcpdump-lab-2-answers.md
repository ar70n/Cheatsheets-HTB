answers

What type of traffic do we see? 
- DNS, HTTP, and HTTPS
Common protocols:

Ports utilized:
- 53, 80, 443,

Are you noticing any common connections between a server and host? If so, who? 
172.16.146.2 and most everyone else.
What are the client and server port numbers used in first full TCP three-way handshake?
client: 43806
server: 80

Who are the servers in these conversations? How do you know?
- 13.35.106.128, 72.21.91.29, 207.244.88.140, 95.216.26.30, 172.217.164.74, 64.233.177.100, and several more.
Who are the receiving hosts?
- 172.16.146.2
What is the timestamp of the first established conversation in the pcap file?
May 11, 2021 11:34:01.237834000 EDT
What is the IP address/s of apache.org from the dns server responses?
- 95.216.26.30, 207.244.88.140 
What protocol is being utilized in that first conversation? (name/#)
- HTTP
Who is the DNS server for this segment?
172.16.146.1
What domain name/s were requested in the pcap file?
- apache.org,  and several more.
What type of DNS Records did you see?
A records, AAAA records, and CNAME records.
Who requests an A record for apache.org? (hostname or ip)
  172.16.146.2
What information does an A record provide?
- it provides a translation from URL to hostname / IP.  
  
Who is the responding DNS server in the pcap? (hostname or ip)  
- 172.16.146.1
What are the most common HTTP request methods from this PCAP?
- POST 
What is the most common HTTP response from this PCAP?
200 OK.
Can you determine what application is running the webserver? 
- from tcpdump its hard, you have to look at the specific responses. it is an apache server..



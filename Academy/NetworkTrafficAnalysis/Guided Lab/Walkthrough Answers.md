# Walkthrough Answers

This task was best completed using the PCAP file provided for the lesson. 

1. what is the issue?
   1. Suspicious traffic coming from within the network. 
2. define our scope and the goal (what are we looking for? which time period?)
   1. target: traffic is originating from 10.129.43.4
   2. when: within the last 48 hours. Capture traffic to determine if it is still happening.
   3. supporting info: file: NTA-guided.pcap
3. define our target(s) (net / host(s) / protocol)
   1. scope: 10.129.43.4 and anyone with a connection to it. Unknown protocol over port 4444.
4. capture network traffic
   1. plug into a link that has access to the 10.129.43.0/24 network to capture live traffic attempting to see if anything is happening. 
   2. We have been given a PCAP with historic data that contains some of the suspect traffic. We will examine this to analyze the issue.
5. identification of required network traffic components (filtering)
   1. First thing we will do is filter out anything that does not have a connection to 10.129.43.4, since this is our primary suspicious target for the moment.

![image](/storage/modules/81/guided-conversations.png)

After checking out the conversations plugin pictured above, we can see there are only three conversations captured in this pcap file and they all pertain to our suspicious host. Next we will look at the `protocol hierarchy` plugin to see what our traffic is. 

![image](/storage/modules/81/guided-proto.png)

We can see here that this PCAP is mostly TCP traffic, with a bit of UDP traffic. Since there is less UDP than TCP traffic, lets look into that first. 

   2. Once we have filtered out the noise, its time to dig for anything unusual. We are going to filter out everything but `UDP traffic first.

![image](/storage/modules/81/guided-udp.png)

When filtering on just UDP traffic we only see 9 packets. Four arp packets, four Network Address Translation `NAT`, and one Simple Sevice Discovery Protocol `SSDP` packet. We can determine based on their packet types and information they contain that this traffic is normal network traffic and nothing to be concerned with. 

   3. Now lets move on to looking at `TCP` traffic. We should have quite a bit more here to sift through. We are going to utilize the display filter `!udp && !arp`. This will clear out anything we have already analized. 

![image](/storage/modules/81/guided-tcp.png)

   4. Now that we have cleared our view a bit, we can see the remaining packets are all TCP, and all appear to be the same conversation between hosts `10.129.43.4` and `10.129.43.29`. We can determine this since we can see the session establishment via three-way handshake at packet 3 and the same ports are used through the rest of the packets in the output below.
![image](/storage/modules/81/guided-handshake.png)   
   
   5. What does appear interesting is that we do not see a TCP session teardown in this PCAP file. This could mean the session was still active and not terminated. We believe this to be true since we do not see any Reset packets either.
   6. We can also examine the conversation by following the `TCP stream` from packet 3 to determine what it encompases.
   
![image](/storage/modules/81/guided-stream.png)

Now that we followed the TCP stream, we should have alarm bells ringing for us. We can see this entire conversation between the two hosts in plain text and it appears that someone was performing several different actions on the host.

   7. Looking at the image above, it appears that someone is performing basic reconisance of the host. They are issuing commands like `whoami`, `ipconfig`, `dir`. It would appear they are trying to get a lay of the land and figure out what user they landed as on the host. `highlighted in orange in the image above`
   8. What is truly alarming is now we can see someone made the account `hacker` and assigned it to the `administrators group` on this host. Either this is a joke by a poor administrator.. Or someone has infiltrated the corporate infrastructure. 
6. note taking / mind mapping of the found results.  
   1. Annotating everything we do, see, or find throughout the investigation is crucial. If needed, make a picture to depict the flow of actions.
   2. Using this example workflow, we have already documented our actions and have included screenshots of everything we included for analysis. These will help influience the decision made for a response.
7. summary of the analysis (what did we find?)
   1. Based on our analysis we determined a malicious actor has infiltrated at least one host on network. HOst 10.129.43.29. is showing signs of someone executing commands on it to include user creation and assigning local administrator permissions via the `net` commands. It would look like the actor was using Bob's host to perform said actions. Since Bob was previously under investigation for the exfil of corporate secrets and disguising it as web traffic, I think it is safe to say the issue has spread further. The screenshots included with this document show the flow of traffic and commands utilized.
   2. It is our opinion that a full Incident Response `IR` procedure be enacted to ensure it is stopped from spreading further and we can dedicate resource to clearing the malicious pressence and cleaning the effected hosts.

## Summary
After you performed your analysis of the actions taken, the IR team determined that 
The actor got lazy and decided to utilize a netcat shell and directly interact with bobs host while gathering more information on Mr. Ben's. While doing so, he utilized RDP from Bob's host to another windows desktop in the envionment to try and establish another foothold. Bob's host has been quarantined and incident response has been initiated to determine what all was taken, and what other potential hosts were compromised. Great job spotting the intrusion.
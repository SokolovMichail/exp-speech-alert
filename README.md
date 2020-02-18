# exp-speech-alert
Experimental app designed to spell out server names and error messages

HOW TO USE:

1) Compile everything into .jar

2) Get .jar on a server and run it.

3) In order to voice a message : run a get request onto the following URL: server:port//generatemessage/{message}&{path}, where a message is a message you need to voice, and path is a resource path where to save a voiced file(.wav).

4) In order to voice a server name : run a get request onto the following URL: server:port//generateservername/{server_name}&{path}, where a server_name is what you need to voice, and path is a resource path where to save a voiced file(.wav).

5) In order to voice a server name in a military style(eg: if you have a server name of twosns, you will receive "Tango Whiskey Oskar Sierra November Sierra", see NATO Phonetical alphabet for better understanding) : run a get request onto the following URL: server:port//generateservernamemil/{server_name}&{path}, where a server_name is what you need to voice, and path is a resource path where to save a voiced file(.wav).

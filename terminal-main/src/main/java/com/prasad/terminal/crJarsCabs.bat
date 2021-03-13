c:
cd \bipin\srcjava
del terminal.jar
del termlite.jar

REM ===  JAR files for Netscape Navigator
c:\JBuilder3\java\bin\jar.exe cvf terminal.jar prasad\terminal\*.class prasad\util\Base64.class prasad\util\GridBagConstraints2.class  prasad\util\ColorPicker*.class prasad\util\ColorSwatch*.class prasad\util\FontPicker*.class pvTreeJ\*.class com\ibm\network\ftp\*.class com\ibm\network\ftp\event\*.class com\ibm\network\ftp\protocol\*.class com\oroinc\text\regex\*.class com\oroinc\text\regex\*.u mpTOOLS\mpEDIT\*.class mpTOOLS\mpEDIT\strings.properties 
c:\JBuilder3\java\bin\jar.exe cvf termlite.jar prasad\terminal\*.class prasad\util\Base64.class prasad\util\GridBagConstraints2.class  prasad\util\ColorPicker*.class prasad\util\ColorSwatch*.class prasad\util\FontPicker*.class 

REM ================== Sign termlite.jar 
mkdir c:\bipin\srcjava\signterm
cd c:\bipin\srcjava\signterm
c:\JBuilder3\java\bin\jar.exe -xvf ..\termlite.jar
cd ..
del termlite.jar
"C:\Program Files\Netscape\signtool13WIN95\signtool" -k "Prasad & Associates Ltd.'s VeriSign, Inc. ID" -d"C:\Program Files\Netscape\Users\bipin_prasad" -Z termlite.jar  signterm
del signterm\prasad\terminal\*.class
del signterm\prasad\util\*.class
del signterm\pvTreeJ\*.class 
del signterm\com\ibm\network\ftp\*.class 
del signterm\com\ibm\network\ftp\event\*.class 
del signterm\com\ibm\network\ftp\protocol\*.class 
del signterm\com\oroinc\text\regex\*.class 
del signterm\com\oroinc\text\regex\*.u 
del signterm\mpTOOLS\mpEDIT\*.class
del signterm\mpTOOLS\mpEDIT\*.properties
del signterm\META-INF\*.mf
del signterm\META-INF\zigbert.*
rmdir signterm\prasad\terminal
rmdir signterm\prasad\util
rmdir signterm\prasad
rmdir signterm\pvTreeJ
rmdir signterm\com\ibm\network\ftp\protocol
rmdir signterm\com\ibm\network\ftp\event
rmdir signterm\com\ibm\network\ftp
rmdir signterm\com\ibm\network
rmdir signterm\com\ibm
rmdir signterm\com\oroinc\text\regex
rmdir signterm\com\oroinc\text
rmdir signterm\com\oroinc
rmdir signterm\com
rmdir signterm\mpTOOLS\mpEDIT
rmdir signterm\mpTOOLS
rmdir signterm\META-INF
rmdir signterm

REM ================== Sign terminal.jar 

mkdir c:\bipin\srcjava\signterm
cd c:\bipin\srcjava\signterm

c:\JBuilder3\java\bin\jar.exe -xvf ..\terminal.jar
cd ..
del terminal.jar
"C:\Program Files\Netscape\signtool13WIN95\signtool" -k "Prasad & Associates Ltd.'s VeriSign, Inc. ID" -d"C:\Program Files\Netscape\Users\bipin_prasad" -Z terminal.jar  signterm
del signterm\prasad\terminal\*.class
del signterm\prasad\util\*.class
del signterm\pvTreeJ\*.class 
del signterm\com\ibm\network\ftp\*.class 
del signterm\com\ibm\network\ftp\event\*.class 
del signterm\com\ibm\network\ftp\protocol\*.class 
del signterm\com\oroinc\text\regex\*.class 
del signterm\com\oroinc\text\regex\*.u 
del signterm\mpTOOLS\mpEDIT\*.class
del signterm\mpTOOLS\mpEDIT\*.properties
del signterm\META-INF\*.mf
del signterm\META-INF\zigbert.*
rmdir signterm\prasad\terminal
rmdir signterm\prasad\util
rmdir signterm\prasad
rmdir signterm\pvTreeJ
rmdir signterm\com\ibm\network\ftp\protocol
rmdir signterm\com\ibm\network\ftp\event
rmdir signterm\com\ibm\network\ftp
rmdir signterm\com\ibm\network
rmdir signterm\com\ibm
rmdir signterm\com\oroinc\text\regex
rmdir signterm\com\oroinc\text
rmdir signterm\com\oroinc
rmdir signterm\com
rmdir signterm\mpTOOLS\mpEDIT
rmdir signterm\mpTOOLS
rmdir signterm\META-INF
rmdir signterm


REM +++++++++++++++++++++++++ Microsoft CAB files

c:
cd \bipin\srcjava
del terminal.cab
del termlite.cab

REM ===  CAB files for Microsoft IE
"C:\Program Files\Microsoft SDK for Java 4.0\bin\CABARC.EXE" -r -p N terminal.cab prasad\terminal\*.class prasad\util\Base64.class prasad\util\GridBagConstraints2.class  prasad\util\ColorPicker*.class prasad\util\ColorSwatch*.class prasad\util\FontPicker*.class + pvTreeJ\*.class + com\ibm\network\ftp\*.class com\ibm\network\ftp\event\*.class com\ibm\network\ftp\protocol\*.class com\oroinc\text\regex\*.class com\oroinc\text\regex\*.u mpTOOLS\mpEDIT\*.class mpTOOLS\mpEDIT\strings.properties 
"C:\Program Files\Microsoft SDK for Java 4.0\bin\CABARC.EXE" -r -p N termlite.cab prasad\terminal\*.class prasad\util\Base64.class prasad\util\GridBagConstraints2.class  prasad\util\ColorPicker*.class prasad\util\ColorSwatch*.class prasad\util\FontPicker*.class 

REM === Sign the CAB files password is ysystbf!
cd "C:\Program Files\Microsoft SDK for Java 4.0\Bin"
SignCode -spc c:\bipin\keys\prasadcert.spc -v c:\bipin\keys\prasadkey.pvk -n "Terminal" -$ commercial -t http://timestamp.verisign.com/scripts/timstamp.dll c:\bipin\srcjava\terminal.cab
SignCode -spc c:\bipin\keys\prasadcert.spc -v c:\bipin\keys\prasadkey.pvk -n "TermLite" -$ commercial -t http://timestamp.verisign.com/scripts/timstamp.dll c:\bipin\srcjava\termlite.cab

REM === Create ZIP Files
cd \bipin\srcjava
copy terminal.jar prasad\terminal
copy termlite.jar prasad\terminal
copy terminal.cab prasad\terminal
copy termlite.cab prasad\terminal
cd prasad
"c:\Program Files\winzip\winzip"

rem @echo off
rem Erzeugt einen Keystore in welchem in Java der RSA-Key geladen wird (package convertedKey).
rem Hier wird nur ein Zertifikat basierend auf demselben key in den keystore geladen. 
setlocal
set keystore=keyStore2.jks
set dname="CN=Johann Weiser, OU=HIT, O=TGM, L=WIEN, ST=WIEN, C=AT"
set sshConfig="
set ktexe="C:\Program Files\Java\jre1.8.0_31\bin\keytool.exe"
set PATH=C:\cygwin64\bin;C:\cygwin64\sbin;%PATH%
set mybash=C:\cygwin64\bin\bash.exe
del id_rsa.pem
del %keystore%
del id_rsa.pkcs8
%mybash% -c "openssl req -new -x509 -key id_rsa -out id_rsa.pem -days 10000 -batch"
%mybash% -c "openssl pkcs8 -topk8 -inform PEM -outform DER -in id_rsa -nocrypt >id_rsa.pkcs8"
rem %ktexe% -validity 10000 -import -v -alias xxx1 -trustcacerts -file id_rsa.pem -keystore %keystore% -keypass weiser -storepass weiser -dname %dname% 
%ktexe% -validity 10000 -import -v -alias xxx1  -file id_rsa.pem -keystore %keystore% -keypass weiser -storepass weiser -dname %dname% -noprompt
%ktexe% -list -v  -storepass weiser -keystore %keystore%
rem %ktexe% -list -rfc  -storepass weiser -keystore %keystore%
endlocal

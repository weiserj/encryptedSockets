@echo off
rem Hier wird ein Keystore mit einem neu erzeugeten Key erzeugt. 
rem Dieser Keystore wird in Java geladen (package encrypted).
setlocal
set ktexe="C:\Program Files\Java\jre1.8.0_31\bin\keytool.exe"
set keystore=keyStore1.jks
set dname="CN=Johann Weiser, OU=HIT, O=TGM, L=WIEN, ST=WIEN, C=AT"
del %keystore%
%ktexe% -genkeypair -keysize 2048 -keyalg RSA -alias weiser2 -keypass weiser -storepass weiser -validity 10000 -keystore %keystore% -dname %dname%
%ktexe% -list -v  -storepass weiser -keystore %keystore%
endlocal

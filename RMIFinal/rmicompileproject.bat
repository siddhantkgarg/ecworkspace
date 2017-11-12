rem Script to compile common packages to client and server.

@echo off
set CLASSPATH=

SET RMIFOLDER="C:\Users\sigarg\ecworkspace\RMIFinal"
SET RMIHOST="MININT-SRV47SP.fareast.corp.microsoft.com"
rem client

cd %RMIFOLDER%\RMICommon\src

IF EXIST %RMIFOLDER%\commonbuild (
rmdir /s %RMIFOLDER%\commonbuild
echo "client build folder deleted"
)
mkdir %RMIFOLDER%\commonbuild
echo "compiling code to commonbuild folder ..."
 javac -d %RMIFOLDER%\commonbuild  Model/*.java Util/*.java Interface/*.java
cd %RMIFOLDER%\commonbuild
 echo "Creating jar ... "
jar cvf commonServer.jar *


rem Server 
cd %RMIFOLDER%\RMICourseSystem\src
echo hello
IF EXIST %RMIFOLDER%\build (
rmdir /s %RMIFOLDER%\build
echo "build folder deleted"
)
mkdir %RMIFOLDER%\build
echo "compiling code to build folder ..."
 javac -cp ".;%RMIFOLDER%\commonbuild\commonServer.jar" -d %RMIFOLDER%\build   Servants/*.java Server/*.java
cd %RMIFOLDER%\build
 echo "Creating jar ... "
jar cvf Server123.jar *
echo "Running Java Command ..."
java -cp %RMIFOLDER%\build\;%RMIFOLDER%\build\Server123.jar;%RMIFOLDER%\commonbuild\commonServer.jar; -Djava.rmi.server.codebase=file:\\\%RMIFOLDER%\build\Server123.jar -Djava.rmi.server.hostname="%RMIHOST%" -Djava.security.policy=file:\\\%RMIFOLDER%\RMICourseSystem\src\server.policy Server.Server







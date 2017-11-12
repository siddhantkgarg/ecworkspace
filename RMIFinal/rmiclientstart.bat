@echo off
set CLASSPATH=

SET RMIFOLDER="C:\Users\sigarg\ecworkspace\RMIFinal"
SET RMISERVER="MININT-SRV47SP.fareast.corp.microsoft.com"
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
jar cvf common.jar *








cd %RMIFOLDER%\RMIClient\src

IF EXIST %RMIFOLDER%\clientbuild (
rmdir /s %RMIFOLDER%\clientbuild
echo "client build folder deleted"
)
mkdir %RMIFOLDER%\clientbuild
echo "compiling code to clientbuild folder ..."
 javac -cp ".;%RMIFOLDER%\commonbuild\common.jar" -d %RMIFOLDER%\clientbuild   *.java
cd %RMIFOLDER%\clientbuild
echo "Creating jar ... "
jar cvf client.jar *
echo "Running java command..."
java -cp "%RMIFOLDER%\clientbuild;%RMIFOLDER%\clientbuild\client.jar;%RMIFOLDER%\commonbuild\common.jar" -Djava.rmi.server.codebase=file:\\\\%RMIFOLDER%\clientbuild\client.jar -Djava.security.policy=file:\\\\%RMIFOLDER%\RMIClient\src\client.policy   Client MININT-SRV47SP.fareast.corp.microsoft.com


rem -Djava.security.debug=access,failure



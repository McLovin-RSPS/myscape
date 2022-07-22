@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Valacial-Source startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and VALACIAL_SOURCE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Valacial-Source.jar;%APP_HOME%\lib\javacord-api-3.0.4.jar;%APP_HOME%\lib\teamgames-api-v1.jar;%APP_HOME%\lib\JDA-4.0.0_39-withDependencies.jar;%APP_HOME%\lib\everythingrs-api.jar;%APP_HOME%\lib\json-simple-1.1.1.jar;%APP_HOME%\lib\mvgate3.jar;%APP_HOME%\lib\Discord4j-2.9.2.jar;%APP_HOME%\lib\fastutil-8.2.3.jar;%APP_HOME%\lib\commons-validator-1.4.0.jar;%APP_HOME%\lib\jcabi-jdbc-0.16.jar;%APP_HOME%\lib\fast-classpath-scanner-2.7.4.jar;%APP_HOME%\lib\toml4j-0.7.2.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\reflections-0.9.11.jar;%APP_HOME%\lib\guava-28.2-jre.jar;%APP_HOME%\lib\jsoup-1.10.3.jar;%APP_HOME%\lib\commons-compress-1.14.jar;%APP_HOME%\lib\mysql-connector-java-5.1.44.jar;%APP_HOME%\lib\HikariCP-2.7.2.jar;%APP_HOME%\lib\postgresql-42.1.4.jar;%APP_HOME%\lib\ant-1.10.2.jar;%APP_HOME%\lib\commons-dbcp2-2.1.jar;%APP_HOME%\lib\netty-3.2.10.Final.jar;%APP_HOME%\lib\javacord-api-3.0.4.jar;%APP_HOME%\lib\fluent-hc-4.5.12.jar;%APP_HOME%\lib\jbcrypt-0.4.jar;%APP_HOME%\lib\com.springsource.org.apache.log4j-1.2.16.jar;%APP_HOME%\lib\jcabi-aspects-0.22.2.jar;%APP_HOME%\lib\jcabi-log-0.15.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\httpmime-4.5.3.jar;%APP_HOME%\lib\httpclient-4.5.12.jar;%APP_HOME%\lib\httpcore-4.4.13.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\websocket-client-9.4.3.v20170317.jar;%APP_HOME%\lib\typetools-0.4.8.jar;%APP_HOME%\lib\commons-lang3-3.5.jar;%APP_HOME%\lib\jackson-module-afterburner-2.8.7.jar;%APP_HOME%\lib\jna-4.3.0.jar;%APP_HOME%\lib\mp3spi-1.9.5-2.jar;%APP_HOME%\lib\jorbis-0.0.17.jar;%APP_HOME%\lib\jflac-1.3.jar;%APP_HOME%\lib\tritonus-share-0.3.7-3.jar;%APP_HOME%\lib\tritonus-dsp-0.3.6.jar;%APP_HOME%\lib\emoji-java-3.2.0.jar;%APP_HOME%\lib\koloboke-impl-common-jdk8-1.0.0.jar;%APP_HOME%\lib\commons-beanutils-1.8.3.jar;%APP_HOME%\lib\commons-digester-1.8.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\aspectjrt-1.8.7.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-2.10.0.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\ant-launcher-1.10.2.jar;%APP_HOME%\lib\commons-pool2-2.3.jar;%APP_HOME%\lib\javassist-3.21.0-GA.jar;%APP_HOME%\lib\jetty-client-9.4.3.v20170317.jar;%APP_HOME%\lib\websocket-common-9.4.3.v20170317.jar;%APP_HOME%\lib\jetty-http-9.4.3.v20170317.jar;%APP_HOME%\lib\jetty-io-9.4.3.v20170317.jar;%APP_HOME%\lib\jetty-util-9.4.3.v20170317.jar;%APP_HOME%\lib\jackson-databind-2.8.7.jar;%APP_HOME%\lib\jackson-core-2.8.7.jar;%APP_HOME%\lib\jlayer-1.0.1-2.jar;%APP_HOME%\lib\json-20140107.jar;%APP_HOME%\lib\koloboke-api-jdk8-1.0.0.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\websocket-api-9.4.3.v20170317.jar;%APP_HOME%\lib\jackson-annotations-2.8.0.jar;%APP_HOME%\lib\junit-3.8.2.jar

@rem Execute Valacial-Source
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VALACIAL_SOURCE_OPTS%  -classpath "%CLASSPATH%" com.arlania.GameServer %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VALACIAL_SOURCE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VALACIAL_SOURCE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

%%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a %%a  
cls 
@echo off 
color F0
:start 
cls 
echo *************************************************************
echo *                                                           *
echo *    ��      ��      ��      ��      ��      ռ      ��     * 
echo *                                                           * 
echo *************************************************************
:a
set port=
echo.&set /p port=������˿ںţ�
cls
if not defined port goto :a
for /f "tokens=2,4,5" %%a in ('netstat -ano^|find /i ":%port% "') do if not "%%c"=="" (set pid=%%c) else (set pid=%%b)
for /f "tokens=1" %%a in ('tasklist /fi "pid eq %pid%"') do set prog=%%a
echo �˿ںţ�%port%
echo PID��%pid%
echo ���̣�%prog%
set n=
echo.&set /p n=��Ҫ��ֹ���̣��������Ӧ��PID��
if defined n (echo.&taskkill /f /fi "pid eq %pid%")
echo.&echo ��������˳�
pause>nul
exit
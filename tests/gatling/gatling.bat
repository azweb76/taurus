echo off
echo "Fake gatling output"
set QT=0
for /f %%i in ('dir /b %2\*.scala') do set /a QT+=1
if %QT% gtr 1 set /p temp="Choose a simulation number:"
echo "started..."
@echo off
set /p launchOptions=Launch options: 
echo.

java -jar ColorPicker.jar %launchOptions%

pause
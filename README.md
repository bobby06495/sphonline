# sphonline
A test project by SPH 
Hello,

This project is developed with SPH online test and has preconditions this document will help you to execute the program


To Run the web based test 

Steps:

1. open the /SPHOnlineTest/Resources/config.properties
2. set the browser name chrome or firefox
	note : (this test would run on MAC, Windows platforms and only on firefox and chrome browsers)
3. Navigate to DriverScript  class in com.sph.WebOnline package and run the file as Java application.
4. Console will print the results and save the screen shots in screen shots folder with test case name

To Run the Mobile based test (Andoird only)

Assumption : ANDROID SDK Tools are installed
			 ANDROID_HOME is set in environment variable(EV)
			 ADB path is also set in EV
			 Appium (command line version)is installed along with NPM 

Steps:

1. open the /SPHOnlineTest/Resources/config.properties
2. set the vaule androiddeviceudid
(to get the UDID of android device)

open terminal - 
run adb devices
copy the deiice ID : eg : ZY2233QP42

3. set devicename - anything
4. windowsAppiumJSpath = is the appium.js path for(MAC its already set in the code) 
5. After 1-4 steps are completed, Navigate to MobileExecutionClass  class in com.sph.mobileOnline package and run the file as Java application.
6. Console will run the logs of appium sever hence please obeserve the mobile device for execution
7. Screen shots will be saved with device name in ScreenShots folder

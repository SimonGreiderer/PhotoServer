# PhotoServer guidance (by Hattinger04)

## Creating keys:
there are two different "key" files (one in the root and the other one in the raspberry_service folder)
- **keysServer.py**: 
Create new file in "_PhotoServer/_ " and put in following values: 
  - admin_username = "your_username" [String]
  - admin_password = "your_password" [String]
  - mqtt_ip = "your_ip" [String]
  - mqtt_port = port_number [int]
  - ex_02_subscription_key = "key_analysis" [String]
  - ex_03_subscription_key = "key_recognition" [String]
  - ex_04_subscription_key = "key_face" [String]
  - ex_04_endpoint = "azure_endpoint" [String]
- **keysRaspberry.py**:
Create new file in "_PhotoServer/raspberry_service_" and put in following values:
  - admin_username = "your_username" [String]
  - admin_password = "your_password" [String]
  - mqtt_ip = "your_ip" [String]
  - mqtt_port = port_number [int]
  - hostHome = "your_ip_webserver_rest" [String] => Your IP at Home
  - hostSchool = "your_ip_webserver_rest" [String] => Your IP at School
<br> 

## Starting mosquitto: 
- overwrite mosquitto.conf with mosquitto.conf in mqtt_test
- execute mosquitto_passwd.exe -c <pw.txt> <username> 
<br> (if you change name of pw.txt, you have to change it in mosquitto.conf too)
- execute cmd as admin
- "net start mosquitto" or "mosquitto.exe -v -c mosquitto.conf" (if first is not working, try second)

## Starting client on RaspberryPi:
- install all needed library's (pip install paho-mqtt, picamera, ...)
- change hosts value if needed (school or home)
- upload raspberry_service folder to raspberrypi (e.g. by WinSCP)
- run script: 
    - sudo python3 client_request.py 
    - or sudo /usr/bin/python client_request.py

## Starting client in Java:
Info: Import JavaService Project as a Maven project!
- First, go to PropertiesConfig
  - Change the member variable createProperties to true
  - At the button of the page (createDefaultProperties()), you have to put in your values
  - run main in PropertiesConfig 
  - a keys.txt should have been created in src/main/resources
  - set createProperties to false
- After that, you can start the 3 applications in the java packages

## Starting Server: 
Start server.py

## Using services:
Use services from azure: 
- create azure account
- create services: 
  - face-recognition
  - OCR
  - image analysis
- put keys in keysServer.py file
- create put REST-request like in the service_test/request.py (put own images in img folder) 
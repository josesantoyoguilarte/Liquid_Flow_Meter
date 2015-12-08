#Final version of Server 
#This Program Run on RPi(2), establishes a connection with RPi(1) using
#UDP IP protocol. Controls alcohol valve and beverage valve through prosses creation 

# author: Jose A Santoyo
#date Dec 5th 2015
import threading
import os
import socket
import sys
import time
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
GPIO.setup(17,GPIO.OUT, pull_up_down=GPIO.PUD_UP)
GPIO.setup(22,GPIO.OUT, pull_up_down=GPIO.PUD_UP)
   
class alcohol:
      port = 42000
      maXcount = 0.0
      count = 1.0
      pin = 22
class beverage:
      port = 43000
      maXcount = 0.0
      count = 1.0
      pin = 17     

def DispenserAlcohol(struct):
      
     
     HOST = '' 
     PORT = struct.port


     try :
          s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
          print ('Socket has been created')
          s.settimeout(6000)
          
     except socket.error:
          print ('Failed to create socket. Error Code : Message')
          sys.exit()

     try :
          s.bind((HOST, PORT))

          print ('Socket has been binded to', PORT)
     except socket.error:
           print ('Failed to create socket. Error Code : Message')
           sys.exit()
           
           

#................................................


  
     python_object = {'total_amount_dispensed':0,
                      'limit':100000, #1 L
                      'remaining':100000
                      }
     while 1:
    
            data, adrr = s.recvfrom(1024)

            json_string = json.dumps(python_object)

            new_python_object = json.loads(json_string)
            tmpT = new_python_object['total_amount_dispensed']
            tmplimit = new_python_object['limit']
            tmpremaining = new_python_object['remaining']
            
            StrToInt = float(data)
            new_python_object['remaining'] = tmpremaining-StrToInt 
            new_python_object['total_amount_dispensed'] = tmpT + StrToInt
            if tmplimit < tmpT
              reply = 'Amount exceed '
              s.sendto(reply, adrr)
              break

            with open('data.json', 'w') as outfile:
                json.dump(json_string, outfile)# saves new contenct on json file
            struct.maXcount = StrToInt/2.25
           
            waitTime = struct.maXcount
     
      
            
            reply = 'Dispencing '+ data + 'ml' #acknowledge data was recieved
            s.sendto(reply, adrr)
            
            
            print ('Requested ' + data + ' ml of Alcohol from address' + str(adrr)  + '')
            print ('Dispencing: ' + data + 'ml')
            
            
            #Control Valve Setup
            #Supply 5v at pin 
            GPIO.output(struct.pin,1)
            while struct.count <= struct.maXcount:
				        
				time.sleep(0.24)     
		 
				struct.count = struct.count + 1
				
				
            struct.maXcount = 0.0  #reset maXcount
            struct.count = 1.0  #reset counter
            GPIO.output(struct.pin,0)#Turn off valve reading done
            
            print ('Request ' + data + ' ml of ALCOHOL dispensed, ENJOY IT!!!')
				 
            

            
              
              


     s.close()

     
def DispenserBeverage(struct):
      
     
     HOST = '' 
     PORT = struct.port


     try :
          s1 = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
          print ('Socket has been created')
          s1.settimeout(600)#set time out while testing
          
     except socket.error:
          print ('Failed to create socket. Error Code : Message')
          sys.exit()

     try :
          s1.bind((HOST, PORT))

          print ('Socket has been binded to', PORT)
     except socket.error:
           print ('Failed to create socket. Error Code : Message')
           sys.exit()
           
           
#................................................

     newpid = os.fork()
     if newpid == 0:
         DispenserAlcohol(alcohol)
     else:
       
             python_object = {'total_amount_dispensed':0,
                              'limit':100000, #1 L
                              'remaining':100000
                             }
	     while 1:
	    
	            data, adrr = s1.recvfrom(1024)
	    
	            json_string = json.dumps(python_object)

                    new_python_object = json.loads(json_string)
                    tmpT = new_python_object['total_amount_dispensed']
                    tmplimit = new_python_object['limit']
                    tmpremaining = new_python_object['remaining']
                        
                    StrToInt = float(data)
                    new_python_object['remaining'] = tmpremaining-StrToInt 
                    new_python_object['total_amount_dispensed'] = tmpT + StrToInt
                    if tmplimit < tmpT
                       reply = 'Amount exceed '
                       s.sendto(reply, adrr)
                    break

                    with open('data.json', 'w') as outfile:
                         json.dump(json_string, outfile)# saves new contenct on json file
	            
	            struct.maXcount = StrToInt/2.25
	           
	            waitTime = struct.maXcount
	     
	      
	            
	            reply = 'Dispencing '+ data + 'ml' #acknowledge data was recieved
	            s1.sendto(reply, adrr)
	     
	            print ('Requested ' + data + ' ml of BEVERAGE from address' + str(adrr)  + '')
	            print ('Dispencing: ' + data + 'ml')
	            
	            
	            #Control Valve Setup
	            #Supply 5v at pin 
	            GPIO.output(struct.pin,1)
	            while struct.count <= struct.maXcount:
					
					         
					time.sleep(0.29)     
			 
			 
					struct.count = struct.count + 1
					
					
	            struct.maXcount = 0.0  #reset maXcount
	            struct.count = 1.0  #reset counter
	            GPIO.output(struct.pin,0)#Turn off valve reading done
	            
	            print ('Request ' + data + ' ml of Beverage dispensed, ENJOY IT!!!')
		
	
	     s1.close()



DispenserBeverage(beverage)



#End 






     

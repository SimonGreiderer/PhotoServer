package io.github.hattinger04.subscribe;
/**
 * 
 * @author Hattinger04
 *
 */
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import io.github.hattinger04.PropertiesConfig;


// TODO: write request to server to get photo!
public class SubscribeGetPhoto {

	private static String username;
	private static String password;
	private static String broker;
	private static String channelSubscribe;
	private final static String clientId = "JavaServiceSubscribeGetPhoto";
	
	private static PropertiesConfig propertiesConfig; 

	
	public static void main(String[] args) throws IOException {
		propertiesConfig = new PropertiesConfig(); 
		propertiesConfig.loadProperties();
		MemoryPersistence persistence = new MemoryPersistence();

		username = propertiesConfig.getProperty("username"); 
		password = propertiesConfig.getProperty("password"); 
		broker = String.format("tcp://%s:%s", propertiesConfig.getProperty("host"), propertiesConfig.getProperty("port"));
		channelSubscribe = propertiesConfig.getProperty("channelSubscribe"); 
		
		try {
			final MqttClient client = new MqttClient(broker, clientId, persistence);
			MqttCallback callback = new MqttCallback() { //Wird aufgerufen, wenn z.B. eine Nachricht reinkommt

				public void connectionLost(Throwable cause) {
					System.out.println("Connection lost");

				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.printf("Got massage %s in channel %s.%n", message, topic);
					if(message.toString().equals("foto_taken")) {
					}
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("Delivery Complete");
				}
			};

			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName(username);
			connOpts.setPassword(password.toCharArray());

			client.setCallback(callback);
			System.out.println("Connecting to broker: " + broker);
			client.connect(connOpts);
			client.subscribe(channelSubscribe);
			System.out.println("Connected and listening....");
			System.in.read(); //Die Anwendung soll warten bis eine Taste gedrückt wird
			client.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch (MqttException me) {
			me.printStackTrace();
		}

	}

}

package home;

/**
 * Producer Consumer example to demonstrate Threads through Reading and Writing
 * Code has two threads that produce message and write message
 * Both read() and write() methods are synchronized because when a thread is running
 * of these methods you don't want another thread to run it too. The reader thread should
 * not run while the writer is running and vice versa
 */

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Message message = new Message();
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start(); //On initial execution of code as it is at this stage
												   //only the first message is displayed and the application
												   //appears frozen and you have to manually terminate the application
												   //The problem is once one of the thread starts looping, the other one
												   //can't change the value of empty because the thread is blocked as only
												   //one synchronized method can run at a time so the looping thread is holding
												   //is holding the lock for the message object while the other one is blocked waiting
												   //for the other thread to release the lock - this is called a deadlock

	}

}

class Message{
	
	private String message;
	private boolean empty = true;//true when there is no message to read
	
	//read() method used by Consumer to read the message
	//Code wants Consumer to read each message before we write another one
	//Loops until there is a message to read, sets the flag to true to indicate
	//we've read the message and that we've returned the message to the caller
	//in this case the Consumer
	public synchronized String read() {
		while (empty) {
			
			
		}
		empty = true;
		return message;
	}
	
	//write(String message) method used by Producer to write the message
	//Checks to see if the message is empty, if it isn't we loop until the message is empty
	//When we exit the loop we set message to false and at that point we then write the message
	public synchronized void write(String message) {
		while (!empty) {
						
		}
		empty = false;
		this.message = message;
	}
}

/*
 * Writer or Producer class to actually write the messages
 */
class Writer implements Runnable {
	private Message message;
	
	public Writer(Message message) {
		this.message = message;
	}
	
	public void run() {
		String messages[] = {
				"Java Developer",
				"C# Developer",
				"NodeJs Developer",
				"Python Developer"
		};
		
		Random random = new Random();//Random class instance for random delay
		
		//Loops through for all messages based on the length of the array
		for (int i = 0; i < messages.length; i++) {
			message.write(messages[i]);
			try {
				Thread.sleep(random.nextInt(2000));
			} catch (InterruptedException e) {
				
			}
		}
		message.write("Finished");
	}
}

/*
 * Reader or Consumer class
 * run() method loops through the messages received looking for the last message"Finished"
 * processes each message printing it out to the console
 * waiting for the return from the random instance up to 2 seconds and then exiting after printing
 * out all messages
 */
class Reader implements Runnable{
	private Message message;
	
	public Reader(Message message) { //Receives and saves message object in out field
		this.message = message;
	}
	
	public void run() { //Reads message and prints it out and delays for a random period of time
		Random random = new Random();
		for (String latestMessage = message.read(); !latestMessage.equals("Finished");
				latestMessage = message.read()) {
			System.out.println(latestMessage);
			try {
				Thread.sleep(random.nextInt(2000));//sleeps for 2 seconds
			} catch (InterruptedException e) {
				
			}
		}
			
			
		
	}
}

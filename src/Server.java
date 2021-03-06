import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.swing.JFrame;

public class Server
{
	static int serverPortText=5565;
	static int serverPortAudio=50005;
    static Server serverObj;
    static String serverIpAddress;
    static int serverSessionId; // sessionID auto - generated by the server
    static ServerSocket serverSocketText;
    static Socket clientText; // socket for client
    
    static ServerSocket serverSocketAudio;
    static Socket clientAudio; // socket for client
    
    Server() // constructor
	{
    	/*
    	 * the constructor of the server class is basically doing 2 functions
    	 * 1) obtaining the ip address of the pc
    	 * 2) generating a 4 digit session id
    	 * 
    	 */
        serverObj=this;
        InetAddress i = null;
        OsCheck.OSType ostype=OsCheck.getOperatingSystemType();
        
        switch (ostype) {
	    case Windows:
			    	try {
						serverIpAddress=InetAddress.getLocalHost().getHostAddress();
					} catch (UnknownHostException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			    	System.out.println("windows");
			    	break;
	    case MacOS:
	    			System.out.println("MacOS");
	    			break;
	    case Linux: 
			    	Enumeration e = null;
			        try {
			            e = NetworkInterface.getNetworkInterfaces();
			        } catch (SocketException e1) {
			            // TODO Auto-generated catch block
			            e1.printStackTrace();
			        }
			        /* extracting the server ip address */
			        NetworkInterface n = (NetworkInterface) e.nextElement();
			        Enumeration ee = n.getInetAddresses();
			         i = (InetAddress) ee.nextElement();
			        i = (InetAddress) ee.nextElement();
			        serverIpAddress=""+i;
			    	System.out.println("Linux");
			    	break;
	    case Other:
	    			break;
        }
        
        
        char ip[]=serverIpAddress.toCharArray();
        if(ip[0]=='/')
        	serverIpAddress=new String(ip,1,serverIpAddress.length()-1);
        
        /*
         * the following while loop ensures that the server session id will be 
         * of 4 digits
         * 
         * now the server session id will be consisting exactly 4 digits
         */
        
        do
        {
        	serverSessionId=(int)(Math.random()*10000);
        }while(serverSessionId/1000==0);

       
    }
    
   /* public static void main(String args[])
    {
    	mainExecution("","","","");
    }*/
    
    public static void mainExecution(String professorName,String departmentName,String subjectName,String topicName) throws IOException
    {

    	/********************************************************************/
    	new Student();
		ServerFrame sf=new ServerFrame(professorName,departmentName,subjectName,topicName);
		sf.setVisible(true);
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	/********************************************************************/
		
		
		/* for testing...
		 * creating various student objects 
		 * these students have audio and text doubts
		 */
		/*new Student("Rakshit","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","DDA line algorithm","audio");
    	new Student("Kavleen","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exceptional Handling","finally block","audio");
    	new Student("Lavish","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","TOC","Finite Automata","audio");
		 */
     	/*new Student("Rakshit Kothari","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Kavleen Kalra","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Nonujeet","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");	
		*//*new Student("Lavish Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Rakshit","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Kavleen","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Nonu","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Shanky","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Mohit","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Ankit","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Rahul","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Lavish Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Rakshit Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Kavleen Kalra","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Jasmeet","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Sameer","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Shanky","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Nehal Mehta","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Kritik Jaroli","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Vatsal","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Ujjawal","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Priyank","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Pooja Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Deepak","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Saket","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Kush Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Love Kothari","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
    	new Student("Naveen","147","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Computer Graphics","","audio");
		
    	new Student("Lavish Kothari","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
     	new Student("Rakshit Kothari","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Kavleen Kalra","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Nonujeet","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Love Kothari","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Kush Kothari","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Deepak","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Pooja","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("abc agrawal","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("xyz sharma","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("pqr sharma","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
    	new Student("Lavish","159","123.123.123","","/home/lavish/Server_ClassRoom_Interaction/Server_ClassRoom_Interaction/Images/a.jpg","Exception","please explain the null pointer exception","text");
		*/
		new Server();
		
    	sf.ipLabel.setText("  IP Address : "+serverIpAddress+"  ");
		sf.sessionIdLabel.setText("  Session ID : "+serverSessionId+"  ");
		
		ServerFrame.ipAddressString="  IP Address : "+serverIpAddress+"  ";
		ServerFrame.sessionIdString="  Session ID : "+serverSessionId+"  ";
		
		
		sf.ipLabel.setText("  IP Address : "+serverIpAddress+"  ");
		sf.sessionIdLabel.setText("  Session ID : "+serverSessionId+"  ");
		
		/*
		 * now starting the audio and the text thread
		 */
		/*
		 * here I wish to initialise all the threads 
		 * so that when professor choose a new server option for 
		 * a different subject all the threads are stoppped
		 * 
		 * so this stopping all the threads may pose a problem
		 * while new server is created.
		 * 
		 * so the initialisation of all the threads is necessary here
		 * 
		 * IMPORTANT
		 */
		
		AudioThread.th=new Thread();
		AudioThread1.th=new Thread();
		LoginThreadAudio.th=new Thread();
		LoginThreadText.th=new Thread();
		NotificationToSpeak.th=new Thread();
		NotifyAllClients.th=new Thread();
		TextThread.th=new Thread();
		/*
		 * 
		 */
		new TextThread(serverObj); // code for text message sending
		new AudioThread1(serverObj); // code for listening for the info regarding the audio part of app.
		new AudioThread(); // code only for audio streaming
		//new LoginThreadAudio(serverObj);
    }
}
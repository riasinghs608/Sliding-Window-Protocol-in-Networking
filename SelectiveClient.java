
package selectiveclient;

/**
 *
 * @author pc
 */
import java.net.*; 
import java.io.*;
import java.util.*;
import java.util.Random;

public class SelectiveClient {

  
    static Socket connection;

    public static void main(String a[]) throws Exception
    {
        Socket s=new Socket("localhost",3333);  
	DataInputStream din=new DataInputStream(s.getInputStream());  
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
	
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter window size : ");
        int n=sc.nextInt();
        dout.write(n);
        int window=n;
        int arr[]=new int[n];
        Random r=new Random();
        
        for(int i=0;i<n;i++)
        {
            arr[i]=r.nextInt(window);
            System.out.println("Sending frames "+(i+1)+" with data "+arr[i]+" to the server"); 
            dout.write(arr[i]);
            dout.flush();
        }
      
        System.out.println("\nWas there any error ? \nWaiting for server to respond :: ");
        int ch=din.readInt();
        
        if(ch==0)
        {
            System.out.println("Server Responed some error\n\nResending frame");
            String str="enter the frame which was not received : ";
            dout.writeUTF(str);

            int lastFrame=din.read();

            System.out.println("Sending Frame "+(lastFrame+1)+" again");
            dout.write(arr[lastFrame]);

            System.out.println("Successful Transmission");
        }
        else
        {
            System.out.println("Server responded no error\n\nAll frames are recieved successfully");
        }
    }
}
    


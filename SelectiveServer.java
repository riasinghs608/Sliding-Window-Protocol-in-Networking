
package selectiveserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author pc
 */
public class SelectiveServer {


    public static void main(String[] args) throws Exception
    {
        ServerSocket ss=new ServerSocket(3333);  
	Socket s=ss.accept();  
	DataInputStream din=new DataInputStream(s.getInputStream());  
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        Scanner sc=new Scanner(System.in);
     
        int window=din.read();
        int arr[]=new int[window];
       
        for(int i=0;i<window;i++)
        {
            arr[i]=din.read();
            System.out.println("Recieving frame " +(i+1));
        }
   
        System.out.println("Were all the frames recieved (Enter 1 for YES and 0 for NO) : ");
        int ch=sc.nextInt();
        dout.writeInt(ch);
        
        if(ch==0)
        {
            System.out.println("Some Error occurred while transmission!!");
            String str=din.readUTF();
            System.out.println("Client asks "+str);
            int frame=sc.nextInt();
            frame=frame-1;
            dout.write(frame);

            arr[frame]=-1;
            
            for(int j=0;j<window;j++)
            {
                System.out.println("Data of frame "+(j+1)+" is :"+arr[j]);
            }
            
            int lastFrame=din.read();
            
            System.out.println("\nFrame number "+(frame+1)+" with data "+lastFrame+" is recieved again");

            System.out.println("All frames are recieved sucessfully");
        }
        else
        {
            System.out.println("Transmission Successful");
        }
        
        
        
      
    }
    
}

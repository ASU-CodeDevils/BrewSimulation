package testing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


/**
 * This came from a multithreaded chat server. We don't necessarily need threads, but given the nature of how 
 * the game will be asynchronous having a separate thread to call each method to the data logic might not be a bad thing. 
 */
public class testsoc {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 9001;

    /**
     * The set of all names of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static HashSet<String> names = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    /**
     * The appplication main method, which just listens on a port and
     * spawns handler threads.
     */
    public String javaflip(String toflip){
    	String flipped = "";
    	for(int x = toflip.length()-1;x>-1;x--)
    	{
    		flipped += toflip.charAt(x);
    	}
    	return(flipped);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }
       
        public String reflectflip(String meth, String para) throws Exception, SecurityException{
        	 String aClass;
             String aMethod;
             // we assume that called methods have no argument
             Class params[] = new Class[1];
             params[0] = String.class;
             Object paramsObj[] = {};
        	aClass  = "testing.testsoc";
            aMethod = meth;
            System.out.println(aMethod);
            System.out.println(para);
            // get the Class
            Class<?> thisClass = Class.forName(aClass);
            // get an instance
            Object iClass = thisClass.newInstance();
            // get the method
            Method thisMethod = thisClass.getDeclaredMethod(aMethod, params);
            // call the method
            String flipped = thisMethod.invoke(iClass, para).toString();
            return(flipped);
        }

        /**
         * Services this thread's client by repeatedly requesting a
         * screen name until a unique one has been submitted, then
         * acknowledges the name and registers the output stream for
         * the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(),"UTF-8"));
                out = new PrintWriter(socket.getOutputStream(), true);

               
              
                while (true) {
                   
                    System.out.println("Are we Here?");
                    name = in.readLine();
                    System.out.println("Are we Here?");
                    System.out.println(name);
                    if (name == null) {
                        return;
                    }
                    else
                    {
                    	String meth = "";
                    	int start = name.indexOf("(");
                    	int last = name.indexOf(")");
                    	meth = name.substring(0,start);
                    	String para = "";
                    	para = name.substring(start+1,last);
                    	String flipped = reflectflip(meth,para);
                    	out.println("done("+flipped+ ")");
                    }
                
                    
                }

               
               
            } catch (IOException e) {
                System.out.println(e);
            } catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

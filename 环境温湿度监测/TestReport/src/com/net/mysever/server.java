package com.net.mysever;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class server {
	ServerSocket server=null;
	Socket client=null;
	public server(){
		try{
		    server=new ServerSocket(8888);
		    System.out.println("等待链接。。。");
		    while(true){
			    client=server.accept();
			    System.out.println("链接成功！！！");
			    new Thread(new clientThread(client)).start();
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

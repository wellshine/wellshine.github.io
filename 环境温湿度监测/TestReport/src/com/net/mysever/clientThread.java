package com.net.mysever;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import letmesleep.uicp.cn.dao.proxy.NodeDaoProxy;

import beans.Node;

public class clientThread implements Runnable {
	private Socket client;
	private boolean c = true;
	public clientThread(Socket client){
		this.client=client;
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag=true;
			while(flag){
				String str=in.readLine();
				System.out.println("接收数据："+str);
				
				String []handle=str.split("_");
				if(handle.length<4){
					System.out.println("拆分结果："+handle.length+"");
					continue;
				}
					
				for(int i=0;i<3;i++){
					if(!handle[i].matches("\\d+")){
						c=false;
						System.out.println("数据异常："+str);
					}
				}
				if(c){
				System.out.println("保存数据："+str);
				Node data=new Node();
				data.setNumid(Integer.parseInt(handle[0]));
//				data.setConcentration(Float.parseFloat(handle[1]));
				data.setHumidity(Float.parseFloat(handle[1]));
				data.setTemperature(Integer.parseInt(handle[2]));
				//data.setDate(handle[4]);
				
				NodeDaoProxy pro = new NodeDaoProxy();
				pro.addNodeData(data);
				}
				c =true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(client!=null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
				
	}

}

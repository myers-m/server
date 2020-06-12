package servermain;

import java.io.IOException;

import server.server;
import shuju.shuju;

/*
 * the class to create server
 */
public class main {
	
public static void main(String args[]) throws IOException {
	shuju.Myserver=new server(8080);
	shuju.Myserver.create();
}
}
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;
import java.util.Scanner;

import server.Films;

public class Client {
	
	public static final int PORT = 2020;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {
		
		
		
		
		//Encapsulamos la IP y el puerto al que nos vamos a conectar
		//InetSocketAddress directionServer = new InetSocketAddress(IP_SERVER, PORT);
		
		// Block try with resources
		try (Scanner sc = new Scanner(System.in)) {
			
			int option;
			do {
				Socket socketToServer = new Socket(IP_SERVER, PORT);
			BufferedReader rd = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
			PrintWriter wr = new PrintWriter(socketToServer.getOutputStream(), true);
			
			clientMenu();
			
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1: 
				System.out.println("--- Consultar película por ID ---");
				System.out.println("Introduzca el ID: ");
				
				int id = sc.nextInt();
				
				wr.println(option);
				wr.println(id);
				
				List<Films> filmsbyId = ClientThread.readFilms(rd);
				ClientThread.showFilms(filmsbyId);
				break;
			
			case 2:
				System.out.println("--- Consultar película por título ---");
				System.out.println("Introduzca el título: ");
				
				String title = sc.nextLine();
				
				wr.println(option);
				wr.println(title);
				
				List<Films> filmsByTitle = ClientThread.readFilms(rd);
				ClientThread.showFilms(filmsByTitle);
				break;
				
			case 3:
				System.out.println("--- Consultar película por Director ---");
				System.out.println("Introduzca el nombre del director: ");
				
				String director = sc.nextLine();
				
				wr.println(option);
				wr.println(director.equalsIgnoreCase(director));
				
				
				List<Films> filmsByDirector = ClientThread.readFilms(rd);
				ClientThread.showFilms(filmsByDirector);
				break;
				
			case 4:
				System.out.println("--- Añadir nueva película ---");
				System.out.println("Por favor, introduzca los siguientes datos: ");
				System.out.println("ID: : ");
				int newID = sc.nextInt();
				//sc.nextLine();
				System.out.println("Título: ");
				String newTitle = sc.nextLine();
				System.out.println("Director: ");
				String newDirector = sc.nextLine();
				System.out.println("Precio: ");
				Double newPrice = sc.nextDouble();
				
				wr.println(option);
				wr.println(newID);
				wr.println(newTitle);
				wr.println(newDirector);
				wr.println(newPrice);
				
				System.out.println("La película ha sido añadida.");
				break;
				
			case 5:
				System.out.println("--- Salir ---");
				System.out.println("Hasta pronto! ");
				
			default:
				System.out.println("Por favor, introduzca una opción válida: ");
				break;
				
			} 
			}while(option !=5);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public static void clientMenu() {
			System.out.println("    APLICACIÓN CLIENTE    ");
			System.out.println("_____________________________");
			System.out.println("Por favor, elija la opción que desee(Introducir número 1-5): ");
			System.out.println("1.- Consultar pelicula por ID");
			System.out.println("2.- Consultar película por título");
			System.out.println("3.- Consultar película por director");
			System.out.println("4.- Añadir película");
			System.out.println("5.- Salir");
		
		

	}

}


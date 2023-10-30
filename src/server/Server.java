package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.ClientThread;

public class Server {
	public static final int PORT = 2020;
	public static final String IP_SERVER= "localhost";
	
public static void main(String[] args) {
		InetSocketAddress direction= new InetSocketAddress(PORT);
		Server server = new Server();
		
		int nClient = 0;
		// Fallo no arranca el servidor
		// Solo arranca cuando se inicia desde el cliente
		try(ServerSocket serverSocket = new ServerSocket(PORT)) {
			//serverSocket.bind(IP_SERVER);
			System.out.println("Iniciando servidor...");
			
			while(true) {
				Socket socketToClient = serverSocket.accept();
				System.out.println("Client conected: " + socketToClient.getInetAddress());
				System.out.println(++nClient);
				Thread clientThread = new Thread(new ClientThread(socketToClient, server));
				
				clientThread.start();
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		}


	private List<Films> film;
	
	public  Server() {
		
		// Añadimos un array con varias peliculas para que el cliente pueda consultar
		film = new ArrayList<>();
		
		film.add(new Films(1, "El origen del planeta de los simios", "Rupert Wyatt", 25.5));
		film.add(new Films(2, "Ice age", "Chris Wedge", 8.25));
		film.add(new Films(3, "Matrix Revolutions", "Lana Wachowski", 20.0));
		film.add(new Films(4, "Matrix Resurrection", "Lana Wachowski", 25.5));
		film.add(new Films(5, "Los vengadores: Endgame", "Joe Russo", 17.5));
		film.add(new Films(6, "Titanic", "James Cameron", 3.5));
		film.add(new Films(7, "1917", "Sam Mendes", 12.5));
		film.add(new Films(8, "Interstellar", "Christopher Nolan", 15.0));
		film.add(new Films(9, "Guardianes de la galaxia: vol 3", "James Gunn", 18.20));
	}

		// Creamos los métodos que va a tener disponible el cliente en el menu
		
		public List<Films> consultFilmById(int id){
			List<Films> result = new ArrayList<>();
			
			for (Films film : film) {
				if(film.getId() == id) {
					result.add(film);
				}
			}
			return result; 
		}
		
		public List<Films> consultFilmByTitle(String title) {
			List<Films> result = new ArrayList<>();
			
			for(Films film : film) {
				if(film.getTitle().equals(title)) {
					result.add(film);
				}
			}
			return result;
		}
		
		public List<Films> consultFilmByDirector(String director) {
			List<Films> result = new ArrayList<>();
			
			for(Films film : film) {
				if(film.getDirector().equals(director)) {
					result.add(film);
				}
			}
			return result;
		}
		
		public synchronized void addFilm(Films films) {
			film.add(films);
		}
		
		
}

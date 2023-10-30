package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.Films;
import server.Server;

public class ClientThread implements Runnable{
	
	InputStreamReader in = null;
	
	private Socket socketToServer;
	private Server server;
	int nClient=0;

	
	public ClientThread(Socket socketToServer, Server server) {
		nClient++;
		this.socketToServer = socketToServer;
		this.server = server;
		
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		 try (ServerSocket serverSocket = new ServerSocket();
				 
			BufferedReader rd = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
	          PrintWriter wr = new PrintWriter(socketToServer.getOutputStream(), true)) {
	        
			 // Aqui da fallo (Llega como string y no lo lee?)
			 
			int option = Integer.parseInt(rd.readLine());
			
			switch (option) {
			
            case 1:
                int id = Integer.parseInt(rd.readLine());
                List<Films> filmsById = server.consultFilmById(id);
                sendFilms(wr, filmsById);
                break;
                
            case 2:
                String title = rd.readLine();
                List<Films> filmsByTitle = server.consultFilmByTitle(title);
                sendFilms(wr, filmsByTitle);
                break;
                
            case 3:
                String director = rd.readLine();
                List<Films> filmsByDirector = server.consultFilmByDirector(director);
                sendFilms(wr, filmsByDirector);
                break;
                
            case 4:
                int newId = Integer.parseInt(rd.readLine());
               sc.nextLine();
                String titleNewFilm = rd.readLine();
                String directorNewFilm = rd.readLine();
                double priceNewFilm = Double.parseDouble(rd.readLine());
                Films newFilm = new Films(newId, titleNewFilm, directorNewFilm, priceNewFilm);
                server.addFilm(newFilm);
                break;
                
            case 5: 
            	System.out.println("--- Salir ---");
				System.out.println("Hasta pronto! ");
            default:
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
	
	
	
	
		private void sendFilms(PrintWriter wr, List<Films> films) {
			wr.println(films.size());
			
			for(Films film : films) {
				wr.println(film.getId());
				wr.println(film.getTitle());
				wr.println(film.getDirector());
				wr.println(film.getPrice());
			}
		}
	
		public static List<Films> readFilms(BufferedReader rd) throws IOException {
			
			int nFilms = Integer.parseInt(rd.readLine());
			List<Films> films = new ArrayList<>();
			
			for (int i = 0; i < nFilms; i++) {
				int id = Integer.parseInt(rd.readLine());
				String title = rd.readLine();
				String director = rd.readLine();
				double price = Double.parseDouble(rd.readLine());
				films.add(new Films(id, title, director, price));
				
			}
			return films;
		}
	
		public static void showFilms (List<Films> films) {
			if(films.isEmpty()) {
				System.out.println("No se ha encontrado ninguna película");
			} else {
				System.out.println("Listado de peliculas: ");
				for(Films film : films) {
					System.out.println("ID: " + film.getId());
					System.out.println("Titulo: " + film.getTitle());
					System.out.println("Director: " + film.getDirector());
					System.out.println("Precio: " + film.getPrice());
					System.out.println("_____________________________________");
					
				}
			}
		}
	
	
	
	}



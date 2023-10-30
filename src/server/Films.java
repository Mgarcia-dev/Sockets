package server;


	// Creamos el objeto peliculas con sus atributos, constructor
	// y los getters, puesto que solo vamos a consultar por sus atributos
	
public class Films {
	
	// Variables de las peliculas
	private int id;
	private String title;
	private String director;
	private double price;
	
	// Constructor
	public Films(int id, String nameFilm, String director, double price) {
		super();
		this.id = id;
		this.title = nameFilm;
		this.director = director;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public double getPrice() {
		return price;
	}

	

	

	



}
	
	
public class BookVO {
	private String genre;
	private String name;
	private int stock;
	private boolean rental;
	private String writer;
	private int recommand = 0;
	
	
	public boolean isRental() {
		return rental;
	}
	public void setRental(boolean rental) {
		this.rental = rental;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getRecommand() {
		return recommand;
	}
	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}
}
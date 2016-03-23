package quill.simple.builder;

public class Director {
	private Builder builder;

	public Director(Builder builder){
		this.builder = builder;
	}
	
	public void buildComputer(String board,String display){
		builder.buildBoard(board);
		builder.buildDisplay(display);
		builder.buildOS();
	}
}

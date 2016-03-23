package quill.design.builder;

public abstract class Computer {
	private String board;
	private String display;
	protected String os;

	public void setBoard(String board){
		this.board = board;
	}
	public void setDisplay(String display){
		this.display = display;
		
	}
	public abstract void setOS();
	@Override
	public String toString() {
		return "Computer [board=" + board + ", display=" + display +", os="+os+ "]";
	}
	
}

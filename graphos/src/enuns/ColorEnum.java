package enuns;
import java.awt.Color;

public enum ColorEnum {
	WHITE(Color.WHITE), 
	GRAY(Color.GRAY), 
	BLACK(Color.BLACK), 
	RED(Color.RED),
	PINK(Color.PINK),
	ORANGE(Color.ORANGE),
	YELLOW(Color.YELLOW),
	GREEN(Color.GREEN),
	BLUE(Color.BLUE);
	
	private Color color; 
	
	private ColorEnum(Color color) {
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public static Color getColor(ColorEnum c){
		for (ColorEnum color: ColorEnum.values()){
			if (color == c){
				return c.getColor();
			}
		}
		return ColorEnum.BLACK.getColor();
	}
}

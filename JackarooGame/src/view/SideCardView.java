package view;

import model.card.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SideCardView extends StackPane{
	private Card card;
	public SideCardView(Card card){
		this.card=card;
		Image image=new Image("/view/BackSides.png"); //image is needed (same image for all)
		ImageView imageview= new ImageView(image);
		imageview.setFitHeight(60); 
		imageview.setFitWidth(90); // height and width to be declared
	}
}

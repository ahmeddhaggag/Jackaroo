package view;

import model.card.Card;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class TopCardView extends StackPane{
	private Card card;
	public TopCardView(Card card){
		this.card=card;
		Image image=new Image("/view/BackTop.png"); //image is needed (same image for all)
		ImageView imageview= new ImageView(image);
		imageview.setFitHeight(90); 
		imageview.setFitWidth(60); // height and width to be declared
	}
}

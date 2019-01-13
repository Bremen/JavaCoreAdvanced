package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    @FXML
    ScrollPane scrollPane;

    @FXML
    TextField textField;

    @FXML
    Button btn;

    @FXML RadioButton me;
    @FXML RadioButton alterEgo;

    @FXML
    VBox vBoxChat;


    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    String myStyle = "-fx-fill: #4F8A10;-fx-font-weight:bold;";
    String alterEgoStyle = "-fx-fill: RED;-fx-font-weight:bold;";

    public void sendMsg() {
        if (!textField.getText().isEmpty()){
            String memberName = (me.isSelected()) ? me.getText() : alterEgo.getText();

            Date date = Calendar.getInstance().getTime();
            String strDate = dateFormat.format(date);

            String nameDate = memberName + "( " + strDate + "): \n";

            Text t1 = new Text();
            t1.setStyle(me.isSelected() ? myStyle : alterEgoStyle);
            t1.setText(nameDate);
            Text t2 = new Text();
            t2.setStyle("-fx-fill: BLACK;-fx-font-weight:normal;");
            t2.setText(textField.getText() + "\n");

            HBox hBox = new HBox();

            TextFlow textFlow = new TextFlow(t1,t2);
            textFlow.setTextAlignment(me.isSelected() ? TextAlignment.RIGHT : TextAlignment.LEFT);

            hBox.getChildren().add(textFlow);
            hBox.setAlignment(me.isSelected() ? Pos.BASELINE_RIGHT : Pos.BASELINE_LEFT);

            vBoxChat.getChildren().add(hBox);

            textField.clear();
            textField.requestFocus();
            scrollPane.setFitToHeight(true);
            scrollPane.setVvalue(1);
        }
    }

    public void changeChatMemberDesign(){
//        scrollPane.setVvalue(me.isSelected() ? Pos.BASELINE_RIGHT : Pos.BASELINE_LEFT
    }
}

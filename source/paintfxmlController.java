package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class paintfxmlController implements Initializable {
    
    @FXML
    private ColorPicker colorpicker;
    
    
    @FXML Canvas canvas;
    

    @FXML
    private Slider sliderSize;
    
    boolean brushSelected = false;
    boolean eraserSelected = false;
    
    GraphicsContext brushTool;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        brushTool = canvas.getGraphicsContext2D();
        brushTool.setFill(Color.WHITE);
        brushTool.fillRect(2000, 2000, 1000, 1000);
        
        canvas.setOnMouseDragged(e -> {
            double size = sliderSize.getValue();
            double x = e.getX() - size / 2; // Coordinates of mouse
            double y = e.getY() - size / 2;
            
            
            if(brushSelected){
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRoundRect(x, y, size, size, size, size);   
            }
            
            if(eraserSelected) {
                //brushTool.setFill(colorpicker.getValue());
                brushTool.clearRect(x, y, size, size);
            }
        });
        
    }    
    
    
    @FXML
    public void brushSelected(ActionEvent e) {
        brushSelected = true;
        eraserSelected = false;
    }
    
    @FXML
    public void eraserSelected(ActionEvent e) {
        eraserSelected = true;
        brushSelected = false;
    }
    
    @FXML
    public void saveSelected(ActionEvent e) {
        
    }
    
    @FXML
    public void newSelected(ActionEvent e) {
        TextField getCanvasWidth = new TextField();
        getCanvasWidth.setPromptText("Width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);
        
        TextField getCanvasHeight = new TextField();
        getCanvasHeight.setPromptText("Height");
        getCanvasHeight.setPrefHeight(150);
        getCanvasHeight.setAlignment(Pos.CENTER);
        
        Button createButton = new Button();
        createButton.setText("Create Canvas");
        
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getCanvasWidth, getCanvasHeight, createButton);
        
        Stage createStage = new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(vBox);
        
        Scene canvasScene = new Scene(root);
        createStage.setTitle("Create Canvas");
        createStage.setScene(canvasScene);
        createStage.show();
        
        double canvasWidthRecieved = Double.parseDouble(getCanvasWidth.getText());
        double canvasHeightRecieved = Double.parseDouble(getCanvasHeight.getText());
        canvas = new Canvas();
        canvas.setWidth(canvasWidthRecieved);
        canvas.setHeight(canvasHeightRecieved);
        vBox.getChildren().add(canvas);
        createStage.close();
    }
}

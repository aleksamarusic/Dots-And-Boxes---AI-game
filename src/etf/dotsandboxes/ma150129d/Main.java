package etf.dotsandboxes.ma150129d;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 730;
    private static final int DEFAULT_SPACING = 10;

    private static final int GROUP_HEADER_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_HEADER_HEIGHT = 80;

    private static final int GROUP_TABLE_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_TABLE_HEIGHT = 200;
    private static final int CANVAS_WIDTH = GROUP_TABLE_WIDTH - 2 * DEFAULT_SPACING;
    private static final int CANVAS_HEIGHT = 120;
    private static final int ERASER = 5;

    private static final int GROUP_PARAMETERS_WIDTH = WINDOW_WIDTH;
    private static final int PARAM_SPIN_WIDTH = 100;
    private static final int GROUP_PARAM_1_HEIGHT = 80;
    private static final int GROUP_PARAM_1_WIDTH = (GROUP_PARAMETERS_WIDTH - 4 * DEFAULT_SPACING) / 3;
    private static final int GROUP_PARAMETERS_HEIGHT = 2 * DEFAULT_SPACING + GROUP_PARAM_1_HEIGHT;

    private static final int GROUP_IMAGES_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_IMAGES_HEIGHT = WINDOW_HEIGHT - GROUP_HEADER_HEIGHT - GROUP_TABLE_HEIGHT - GROUP_PARAMETERS_HEIGHT + DEFAULT_SPACING;
    private static final int BTN_NEW_IMAGE_WIDTH = 150;
    private static final int BTN_NEW_IMAGE_HEIGHT = 40;
    private static final int GROUP_IMAGE_LIST_WIDTH = GROUP_IMAGES_WIDTH - 2 * DEFAULT_SPACING;
    private static final int GROUP_IMAGE_LIST_HEIGHT = GROUP_IMAGES_HEIGHT - 3 * DEFAULT_SPACING - BTN_NEW_IMAGE_HEIGHT;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = IMAGE_WIDTH;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group groupHeader = new Group();
        {
            Rectangle border = new Rectangle(1, 1, GROUP_HEADER_WIDTH - 2, GROUP_HEADER_HEIGHT - 2);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);

            comboLessions = new ComboBox<>();
            comboLessions.setTranslateX(DEFAULT_SPACING);
            comboLessions.setTranslateY(2 * DEFAULT_SPACING);
            comboLessions.setMinWidth(200);
            comboLessions.setMinHeight(BTN_NEW_IMAGE_HEIGHT);
            comboLessions.valueProperty().addListener((ov, t, t1) -> { if (t1 != null) { saveBukvar(t1); } });
            Button btnSave = new Button();
            btnSave.setText("Сачувај");
            btnSave.setMaxWidth(120);
            btnSave.setMinHeight(BTN_NEW_IMAGE_HEIGHT);
            btnSave.setTranslateX(230);
            btnSave.setTranslateY(2 * DEFAULT_SPACING);
            btnSave.setOnAction(event -> saveBukvar(null));
            Button btnDelete = new Button();
            btnDelete.setText("Обриши");
            btnDelete.setMaxWidth(120);
            btnDelete.setMinHeight(BTN_NEW_IMAGE_HEIGHT);
            btnDelete.setTranslateX(330);
            btnDelete.setTranslateY(2 * DEFAULT_SPACING);
            btnDelete.setOnAction((ActionEvent event) -> {
                if (bukvar.deleteLession(selectedLession.name)) {
                    refreshLessions();
                    comboLessions.setValue(bukvar.defaultLessionName);
                }
            });
            Button btnDefault = new Button();
            btnDefault.setText("Подразумевана");
            btnDefault.setMaxWidth(150);
            btnDefault.setMinHeight(BTN_NEW_IMAGE_HEIGHT);
            btnDefault.setTranslateX(430);
            btnDefault.setTranslateY(2 * DEFAULT_SPACING);
            btnDefault.setOnAction((ActionEvent event) -> bukvar.defaultLessionName = selectedLession.name);
            fieldNewName = new TextField("");
            fieldNewName.fontProperty().set(Font.font(STYLESHEET_CASPIAN, 20));
            fieldNewName.setTranslateX(680);
            fieldNewName.setTranslateY(2 * DEFAULT_SPACING);
            fieldNewName.setMaxWidth(150);
            Button btnNewLesson = new Button();
            btnNewLesson.setText("Нова лекција");
            btnNewLesson.setMaxWidth(150);
            btnNewLesson.setMinHeight(BTN_NEW_IMAGE_HEIGHT);
            btnNewLesson.setTranslateX(850);
            btnNewLesson.setTranslateY(2 * DEFAULT_SPACING);
            btnNewLesson.setOnAction((ActionEvent event) -> {
                String name = fieldNewName.getText();
                if (name.length() > 0 && !bukvar.lessions.containsKey(name)) {
                    bukvar.lessions.put(name, new Lession(name, blankTable()));
                    refreshLessions();
                    comboLessions.setValue(name);
                    fieldNewName.setText("");
                }
            });

            groupHeader.getChildren().addAll(border, comboLessions, btnSave, btnDelete, btnDefault, fieldNewName, btnNewLesson);
        }

        Group root = new Group();
        //root.getChildren().addAll(groupHeader, groupTable, groupImages, groupParams);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle("Dots And Boxes game");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

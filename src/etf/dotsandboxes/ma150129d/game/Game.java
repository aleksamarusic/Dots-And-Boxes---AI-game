package etf.dotsandboxes.ma150129d.game;

import etf.dotsandboxes.ma150129d.structs.Settings;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class  Game extends Application {

    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 730;
    private static final int DEFAULT_SPACING = 10;

    private static final int GROUP_HEADER_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_HEADER_HEIGHT = 80;

    private static final int COMBOBOX_WIDTH = 200;
    private static final int COMBOBOX_HEIGHT = GROUP_HEADER_HEIGHT / 2 - DEFAULT_SPACING;

    private static final int PARAM_SPIN_WIDTH = 100;
    private static final int GROUP_SPINNER_HEIGHT = GROUP_HEADER_HEIGHT - 2 * DEFAULT_SPACING;
    private static final int GROUP_SPINNER_WIDTH = 190;

    private static final int BTN_WIDTH = 150;
    private static final int BTN_HEIGHT = GROUP_HEADER_HEIGHT / 2 - DEFAULT_SPACING;

    private static final int GROUP_IN_GAME_PARAMS_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_IN_GAME_PARAMS_HEIGHT = 80;

    private static final int GROUP_GAME_BOARD_WIDTH = WINDOW_WIDTH;
    private static final int GROUP_GAME_BOARD_HEIGHT = WINDOW_HEIGHT - GROUP_HEADER_HEIGHT - GROUP_IN_GAME_PARAMS_HEIGHT + DEFAULT_SPACING / 2;

    private static final Map<String, Integer> colors = new HashMap<String, Integer>(){{
        put("Human", 0);
        put("Computer - easy", 1);
        put("Computer - medium", 2);
        put("Computer - hard", 3);
    }};

    private Stage stage;

    private ComboBox<String> playerOne;
    private ComboBox<String> playerTwo;

    private Spinner<Integer> treeDepthSpinner;
    private Spinner<Integer> numRowsSpinner;
    private Spinner<Integer> numColsSpinner;

    private Settings settings;

    private void initialize() {
        settings = null;

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group groupHeader = new Group();
        {
            groupHeader.setTranslateX(DEFAULT_SPACING / 2);

            Rectangle groupHeaderBorder = new Rectangle(1, 1, GROUP_HEADER_WIDTH - 2, GROUP_HEADER_HEIGHT - 2);
            groupHeaderBorder.setFill(Color.TRANSPARENT);
            groupHeaderBorder.setStroke(Color.BLACK);

            playerOne = new ComboBox();
            {
                playerOne.setTranslateX(DEFAULT_SPACING);
                playerOne.setTranslateY(DEFAULT_SPACING / 2);
                playerOne.setMinWidth(COMBOBOX_WIDTH);
                playerOne.setMaxWidth(COMBOBOX_WIDTH);
                playerOne.setMinHeight(15);
                playerOne.setMaxHeight(COMBOBOX_HEIGHT);
                playerOne.getItems().addAll(
                        "Human",
                        "Computer - easy",
                        "Computer - medium",
                        "Computer - hard"
                );
                playerOne.setPromptText("Choose player 1 (red)");
                playerOne.valueProperty().addListener((ov, t, t1) -> {
                });
            }

            playerTwo = new ComboBox();
            {
                playerTwo.setTranslateX(DEFAULT_SPACING);
                playerTwo.setTranslateY(GROUP_HEADER_HEIGHT / 2 + DEFAULT_SPACING / 2);
                playerTwo.setMinWidth(COMBOBOX_WIDTH);
                playerTwo.setMaxWidth(COMBOBOX_WIDTH);
                playerTwo.setMinHeight(15);
                playerTwo.setMaxHeight(COMBOBOX_HEIGHT);
                playerTwo.getItems().addAll(
                        "Human",
                        "Computer - easy",
                        "Computer - medium",
                        "Computer - hard"
                );
                playerTwo.setPromptText("Choose player 2 (blue)");
                playerTwo.valueProperty().addListener((ov, t, t1) -> {
                });
            }


            Group groupTreeDepth = new Group();
            {
                groupTreeDepth.setTranslateX(COMBOBOX_WIDTH + 2 * DEFAULT_SPACING);
                groupTreeDepth.setTranslateY(DEFAULT_SPACING);
                Rectangle borderTreeDepth = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderTreeDepth.setFill(Color.TRANSPARENT);
                borderTreeDepth.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Tree depth:");
                treeDepthSpinner = new Spinner<>(3, 15, 5);
                treeDepthSpinner.setTranslateX(DEFAULT_SPACING);
                treeDepthSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                treeDepthSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                treeDepthSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                treeDepthSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupTreeDepth.getChildren().addAll(borderTreeDepth, text, treeDepthSpinner);
            }

            Group groupNumRows = new Group();
            {
                groupNumRows.setTranslateX(COMBOBOX_WIDTH + GROUP_SPINNER_WIDTH + 3 * DEFAULT_SPACING);
                groupNumRows.setTranslateY(DEFAULT_SPACING);
                Rectangle borderNumRows = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderNumRows.setFill(Color.TRANSPARENT);
                borderNumRows.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Number of rows:");
                numRowsSpinner = new Spinner<>(1, 20, 5);
                numRowsSpinner.setTranslateX(DEFAULT_SPACING);
                numRowsSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                numRowsSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                numRowsSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                numRowsSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupNumRows.getChildren().addAll(borderNumRows, text, numRowsSpinner);
            }

            Group groupNumCols = new Group();
            {
                groupNumCols.setTranslateX(COMBOBOX_WIDTH + 2 * GROUP_SPINNER_WIDTH + 4 * DEFAULT_SPACING);
                groupNumCols.setTranslateY(DEFAULT_SPACING);
                Rectangle borderNumCols = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderNumCols.setFill(Color.TRANSPARENT);
                borderNumCols.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Number of columns:");
                numColsSpinner = new Spinner<>(1, 20, 5);
                numColsSpinner.setTranslateX(DEFAULT_SPACING);
                numColsSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                numColsSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                numColsSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                numColsSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupNumCols.getChildren().addAll(borderNumCols, text, numColsSpinner);
            }

            Button startGameBtn = new Button();
            {
                startGameBtn.setText("Start game");
                startGameBtn.setMinWidth(BTN_WIDTH);
                startGameBtn.setMaxWidth(BTN_WIDTH);
                startGameBtn.setMinHeight(15);
                startGameBtn.setMaxHeight(GROUP_HEADER_HEIGHT / 2 - DEFAULT_SPACING);
                startGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
                startGameBtn.setTranslateY(DEFAULT_SPACING / 2);
                startGameBtn.setOnAction((ActionEvent event) -> {
                });
            }

            Button loadGameBtn = new Button();
            {
                loadGameBtn.setText("Load game");
                loadGameBtn.setMinWidth(BTN_WIDTH);
                loadGameBtn.setMaxWidth(BTN_WIDTH);
                loadGameBtn.setMinHeight(15);
                loadGameBtn.setMaxHeight(BTN_HEIGHT);
                loadGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
                loadGameBtn.setTranslateY(GROUP_HEADER_HEIGHT / 2 + DEFAULT_SPACING / 2);
                loadGameBtn.setOnAction((ActionEvent event) -> {
                });
            }

            groupHeader.getChildren().addAll(groupHeaderBorder, playerOne, playerTwo, groupTreeDepth, groupNumRows, groupNumCols, loadGameBtn, startGameBtn);
        }

        Group groupInGameParams = new Group();
        {
            groupInGameParams.setTranslateY(GROUP_HEADER_HEIGHT);
            groupInGameParams.setTranslateX(DEFAULT_SPACING / 2);

            Rectangle groupHeaderBorder = new Rectangle(1, 1, GROUP_IN_GAME_PARAMS_WIDTH - 2, GROUP_IN_GAME_PARAMS_HEIGHT - 2);
            groupHeaderBorder.setFill(Color.TRANSPARENT);
            groupHeaderBorder.setStroke(Color.BLACK);

            playerOne = new ComboBox();
            {
                playerOne.setTranslateX(DEFAULT_SPACING);
                playerOne.setTranslateY(DEFAULT_SPACING / 2);
                playerOne.setMinWidth(COMBOBOX_WIDTH);
                playerOne.setMaxWidth(COMBOBOX_WIDTH);
                playerOne.setMinHeight(15);
                playerOne.setMaxHeight(COMBOBOX_HEIGHT);
                playerOne.getItems().addAll(
                        "Human",
                        "Computer - easy",
                        "Computer - medium",
                        "Computer - hard"
                );
                playerOne.setPromptText("Choose player 1 (red)");
                playerOne.valueProperty().addListener((ov, t, t1) -> {
                });
            }

            playerTwo = new ComboBox();
            {
                playerTwo.setTranslateX(DEFAULT_SPACING);
                playerTwo.setTranslateY(GROUP_HEADER_HEIGHT / 2 + DEFAULT_SPACING / 2);
                playerTwo.setMinWidth(COMBOBOX_WIDTH);
                playerTwo.setMaxWidth(COMBOBOX_WIDTH);
                playerTwo.setMinHeight(15);
                playerTwo.setMaxHeight(COMBOBOX_HEIGHT);
                playerTwo.getItems().addAll(
                        "Human",
                        "Computer - easy",
                        "Computer - medium",
                        "Computer - hard"
                );
                playerTwo.setPromptText("Choose player 2 (blue)");
                playerTwo.valueProperty().addListener((ov, t, t1) -> {
                });
            }


            Group groupTreeDepth = new Group();
            {
                groupTreeDepth.setTranslateX(COMBOBOX_WIDTH + 2 * DEFAULT_SPACING);
                groupTreeDepth.setTranslateY(DEFAULT_SPACING);
                Rectangle borderTreeDepth = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderTreeDepth.setFill(Color.TRANSPARENT);
                borderTreeDepth.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Tree depth:");
                treeDepthSpinner = new Spinner<>(3, 15, 5);
                treeDepthSpinner.setTranslateX(DEFAULT_SPACING);
                treeDepthSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                treeDepthSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                treeDepthSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                treeDepthSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupTreeDepth.getChildren().addAll(borderTreeDepth, text, treeDepthSpinner);
            }

            Group groupNumRows = new Group();
            {
                groupNumRows.setTranslateX(COMBOBOX_WIDTH + GROUP_SPINNER_WIDTH + 3 * DEFAULT_SPACING);
                groupNumRows.setTranslateY(DEFAULT_SPACING);
                Rectangle borderNumRows = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderNumRows.setFill(Color.TRANSPARENT);
                borderNumRows.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Number of rows:");
                numRowsSpinner = new Spinner<>(1, 20, 5);
                numRowsSpinner.setTranslateX(DEFAULT_SPACING);
                numRowsSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                numRowsSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                numRowsSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                numRowsSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupNumRows.getChildren().addAll(borderNumRows, text, numRowsSpinner);
            }

            Group groupNumCols = new Group();
            {
                groupNumCols.setTranslateX(COMBOBOX_WIDTH + 2 * GROUP_SPINNER_WIDTH + 4 * DEFAULT_SPACING);
                groupNumCols.setTranslateY(DEFAULT_SPACING);
                Rectangle borderNumCols = new Rectangle(1, 1, GROUP_SPINNER_WIDTH - 2, GROUP_SPINNER_HEIGHT - 2);
                borderNumCols.setFill(Color.TRANSPARENT);
                borderNumCols.setStroke(Color.BLACK);
                Text text = new Text(DEFAULT_SPACING, 2 * DEFAULT_SPACING, "Number of columns:");
                numColsSpinner = new Spinner<>(1, 20, 5);
                numColsSpinner.setTranslateX(DEFAULT_SPACING);
                numColsSpinner.setTranslateY(5 * DEFAULT_SPACING / 2);
                numColsSpinner.setMaxWidth(PARAM_SPIN_WIDTH);
                numColsSpinner.setMaxHeight(GROUP_SPINNER_HEIGHT - DEFAULT_SPACING);
                numColsSpinner.valueProperty().addListener((ov, t, t1) -> {  });
                groupNumCols.getChildren().addAll(borderNumCols, text, numColsSpinner);
            }

            Button startGameBtn = new Button();
            startGameBtn.setText("Start game");
            startGameBtn.setMinWidth(BTN_WIDTH);
            startGameBtn.setMaxWidth(BTN_WIDTH);
            startGameBtn.setMinHeight(15);
            startGameBtn.setMaxHeight(GROUP_HEADER_HEIGHT / 2 - DEFAULT_SPACING);
            startGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
            startGameBtn.setTranslateY(DEFAULT_SPACING / 2);
            startGameBtn.setOnAction((ActionEvent event) -> {     });

            Button loadGameBtn = new Button();
            loadGameBtn.setText("Load game");
            loadGameBtn.setMinWidth(BTN_WIDTH);
            loadGameBtn.setMaxWidth(BTN_WIDTH);
            loadGameBtn.setMinHeight(15);
            loadGameBtn.setMaxHeight(BTN_HEIGHT);
            loadGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
            loadGameBtn.setTranslateY(GROUP_HEADER_HEIGHT / 2 + DEFAULT_SPACING / 2);
            loadGameBtn.setOnAction((ActionEvent event) -> {     });

            groupInGameParams.getChildren().addAll(groupHeaderBorder, playerOne, playerTwo, groupTreeDepth, groupNumRows, groupNumCols, loadGameBtn, startGameBtn);
        }

        Group groupGameBoard = new Group();
        {
            groupGameBoard.setTranslateY(GROUP_HEADER_HEIGHT + GROUP_IN_GAME_PARAMS_HEIGHT);
            groupGameBoard.setTranslateX(DEFAULT_SPACING / 2);

            Rectangle groupHeaderBorder = new Rectangle(1, 1, GROUP_GAME_BOARD_WIDTH - 2, GROUP_GAME_BOARD_HEIGHT - 2);
            groupHeaderBorder.setFill(Color.TRANSPARENT);
            groupHeaderBorder.setStroke(Color.BLACK);

            groupGameBoard.getChildren().addAll(groupHeaderBorder);
        }

        Group root = new Group();
        root.getChildren().addAll(groupHeader, groupInGameParams, groupGameBoard);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        settings = null;


        primaryStage.setTitle("Dots And Boxes game");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

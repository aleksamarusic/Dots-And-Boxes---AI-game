package etf.dotsandboxes.ma150129d.game;

import etf.dotsandboxes.ma150129d.bots.Player;
import etf.dotsandboxes.ma150129d.structs.Settings;
import etf.dotsandboxes.ma150129d.structs.State;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final Map<String, Integer> pla = new HashMap<String, Integer>(){{
        put("Human", 0);
        put("Computer - easy", 1);
        put("Computer - medium", 2);
        put("Computer - hard", 3);
    }};

    private Stage stage;

    private Group groupHeader;
    private Group groupInGameParams;
    private Group groupGameBoard;

    private ComboBox<String> playerOne;
    private ComboBox<String> playerTwo;

    private Spinner<Integer> treeDepthSpinner;
    private Spinner<Integer> numRowsSpinner;
    private Spinner<Integer> numColsSpinner;

    private Settings settings;
    private List<Circle> circles = new ArrayList<>();
    private List<Rectangle> rectangles = new ArrayList<>();
    private List<Rectangle> squares = new ArrayList<>();
    private Player[] players = new Player[2];
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public List<Rectangle> getSquares() {
        return squares;
    }

    public void setSquares(List<Rectangle> squares) {
        this.squares = squares;
    }


    private void fillGameBoard(int m, int n) {
        int radius = 10;
        groupGameBoard.getChildren().removeAll(circles);
        groupGameBoard.getChildren().removeAll(rectangles);
        groupGameBoard.getChildren().removeAll(squares);
        circles.clear();
        int xpos, ypos = DEFAULT_SPACING + radius;
        for (int i = 0; i < m; i++) {
            xpos = DEFAULT_SPACING + radius;
            for (int j = 0; j < n; j++) {
                if (i < m - 1 && j < n - 1) {
                    Rectangle square = new Rectangle(xpos, ypos, 5 * radius, 5 * radius);
                    square.setFill(Color.TRANSPARENT);
                    squares.add(square);
                }
                if (j < n - 1) {
                    Rectangle rectangle = new Rectangle(xpos, ypos - radius, 5*radius, 2*radius);
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent t) {
                            rectangle.setFill(Color.RED);
                        }
                    });
                    rectangles.add(rectangle);
                }
                if (i < m - 1) {
                    Rectangle rectangle = new Rectangle(xpos - radius, ypos, 2 * radius, 5 * radius);
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent t) {
                            rectangle.setFill(Color.BLUE);
                        }
                    });
                    rectangles.add(rectangle);
                }
                Circle circle = new Circle(xpos, ypos, radius);
                circle.setFill(Color.GRAY);
                circles.add(circle);
                xpos += 5*radius;
            }
            ypos += 5*radius;
        }
        groupGameBoard.getChildren().addAll(squares);
        groupGameBoard.getChildren().addAll(rectangles);
        groupGameBoard.getChildren().addAll(circles);
    }

    private void initialize() {
        groupHeader.setDisable(false);
        groupInGameParams.setDisable(true);
        groupGameBoard.setDisable(true);
        players[0] = players[1] = null;
        playerOne.getSelectionModel().select(1);
        playerOne.getSelectionModel().select(1);
        /*settings.setNumberOfRows(numRowsSpinner.getValue());
        settings.setNumberOfCols(numColsSpinner.getValue());
        settings.setTreeDepth(treeDepthSpinner.getValue());
        settings.setPlayerOne(pla.get(playerOne.getValue()));
        settings.setPlayerTwo(pla.get(playerTwo.getValue()));*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        groupHeader = new Group();
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
                    settings.setPlayerOne(pla.get(t1));
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
                    settings.setPlayerTwo(pla.get(t1));
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
                treeDepthSpinner.valueProperty().addListener((ov, t, t1) -> {
                    settings.setTreeDepth(t1);
                });
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
                numRowsSpinner.valueProperty().addListener((ov, t, t1) -> {
                    settings.setNumberOfRows(t1);
                });
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
                numColsSpinner.valueProperty().addListener((ov, t, t1) -> {
                    settings.setNumberOfCols(t1);
                });
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
                    //podesiti pocetno stanje
                    fillGameBoard(settings.getNumberOfRows(), settings.getNumberOfCols());
                    state = new State(settings.getNumberOfRows(), settings.getNumberOfCols());
                    groupHeader.setDisable(true);
                    groupInGameParams.setDisable(false);
                    groupGameBoard.setDisable(false);
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
                    //funkcija koja ucitava iz fajla i popunjava settings i state
                    fillGameBoard(settings.getNumberOfRows(), settings.getNumberOfCols());
                    groupHeader.setDisable(true);
                    groupInGameParams.setDisable(false);
                    groupGameBoard.setDisable(false);
                });
            }

            groupHeader.getChildren().addAll(groupHeaderBorder, playerOne, playerTwo, groupTreeDepth,
                    groupNumRows, groupNumCols, loadGameBtn, startGameBtn);
        }

        groupInGameParams = new Group();
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
                playerTwo.setTranslateY((GROUP_HEADER_HEIGHT / 2) + (DEFAULT_SPACING / 2));
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

            Button endGameBtn = new Button();
            endGameBtn.setText("End game");
            endGameBtn.setMinWidth(BTN_WIDTH);
            endGameBtn.setMaxWidth(BTN_WIDTH);
            endGameBtn.setMinHeight(15);
            endGameBtn.setMaxHeight(GROUP_HEADER_HEIGHT / 2 - DEFAULT_SPACING);
            endGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
            endGameBtn.setTranslateY(DEFAULT_SPACING / 2);
            endGameBtn.setOnAction((ActionEvent event) -> {
                groupHeader.setDisable(false);
                groupInGameParams.setDisable(true);
                groupGameBoard.setDisable(true);
            });

            Button saveGameBtn = new Button();
            saveGameBtn.setText("Save game");
            saveGameBtn.setMinWidth(BTN_WIDTH);
            saveGameBtn.setMaxWidth(BTN_WIDTH);
            saveGameBtn.setMinHeight(15);
            saveGameBtn.setMaxHeight(BTN_HEIGHT);
            saveGameBtn.setTranslateX(WINDOW_WIDTH - BTN_WIDTH - DEFAULT_SPACING);
            saveGameBtn.setTranslateY(GROUP_HEADER_HEIGHT / 2 + DEFAULT_SPACING / 2);
            saveGameBtn.setOnAction((ActionEvent event) -> {
                //funkcija koja cuva trenutno stanje i settings u fajl
                groupHeader.setDisable(false);
                groupInGameParams.setDisable(true);
                groupGameBoard.setDisable(true);
            });

            groupInGameParams.getChildren().addAll(groupHeaderBorder, playerOne, playerTwo, groupTreeDepth, groupNumRows, groupNumCols, saveGameBtn, endGameBtn);
        }

        groupGameBoard = new Group();
        {
            groupGameBoard.setTranslateY(GROUP_HEADER_HEIGHT + GROUP_IN_GAME_PARAMS_HEIGHT);
            groupGameBoard.setTranslateX(DEFAULT_SPACING / 2);

            Rectangle groupGameBoardBorder = new Rectangle(1, 1, GROUP_GAME_BOARD_WIDTH - 2, GROUP_GAME_BOARD_HEIGHT - 2);
            groupGameBoardBorder.setFill(Color.TRANSPARENT);
            groupGameBoardBorder.setStroke(Color.BLACK);

            groupGameBoard.getChildren().addAll(groupGameBoardBorder);
        }

        Group root = new Group();
        root.getChildren().addAll(groupHeader, groupInGameParams, groupGameBoard);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        initialize();


        primaryStage.setTitle("Dots And Boxes game");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

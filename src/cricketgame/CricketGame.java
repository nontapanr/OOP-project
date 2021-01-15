/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import java.awt.Scrollbar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author PatNum
 */
public class CricketGame extends Application {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    static CountryList c_list;
    static ICCTestRankings ICCRank;
    static MatchList matchListObject;

    private Stage window;
    private Stage addNewPlayer_window;
    private Stage addNewCountry_window;
    private Stage playerReport_window;
    private int windowHeight = 600;
    private int windowWidth = 1000;

    HBox layoutTop;
    //  HBox layoutBottom;
    VBox layoutLeft;
    VBox layoutRight;

    //VBox countryTable ;
    ScrollPane layoutCenter;
    ScrollPane layoutMatches;
    ScrollPane layoutICC;
    ScrollPane layoutReport;
    ScrollPane layoutMatchHistory;
    StackPane stackPane;
    StackPane defaultStackPane;
    StackPane addNewPlayerStackPane;
    StackPane addNewCountryStackPane;
    StackPane playerReportStackPane;
    GridPane addNewPlayerGridPane;
    GridPane addNewCountryGridPane;
    GridPane defaultGridPane;
    BorderPane layoutScreen;

    Scene mainScene;
    Scene defaultScene;
    Scene addNewPlayerScene;
    Scene addNewCountryScene;
    Scene playerReportScene;

    Button displayBtn;
    Button iccBtn;
    Button matchBtn;
    Button newRandomMatchBtn;
    Button randomAResultBtn;
    Button matchhistoryBtn;

    Button addcountryBtn;
    Button addInPlayerBtn;
    Button addInCountryBtn;
    Button defaultGoBtn;

    TextField ratingInput;
    TextField pNameInput;
    TextField ageInput;
    TextField posInput;
    TextField countryNameInput;
    TextField matchInput;
    TextField ratingCountryInput;
    Label warnTextAddCountry = new Label();
    Label warnTextAddPlayer = new Label();
    ComboBox<String> positionList;
    ComboBox<String> defaultCountryList;
    static int buttonWidth = 250;
    static int buttonHeight = 40;
    static String fontPathBold = "file:resources/fonts/RobotoCondensed-Bold.ttf";
    static String fontPathRegular = "file:resources/fonts/RobotoCondensed-Regular.ttf";
    static String styleinCountryAndPlayer = " -fx-background-color :#69dce7 ;";
    static String styleICC = " -fx-background-color :#36e9bb;";
    static String styleMatches = " -fx-background-color :#5eeb91;";
    static String styleMatchResults = " -fx-background-color :#8eea5f; ";

    static String backgroundCountryAndPlayer = " -fx-background-color :#c3f1f5 ;";
    static String backgroundICC = " -fx-background-color :#aef6e3;";
    static String backgroundMatches = " -fx-background-color :#bef7d3;";
    static String backgroundMatchResults = " -fx-background-color :#d1f6bf; ";

    public static void main(String[] args) throws FileNotFoundException, Exception {
        //Pat edited
//If delete .dat files uncomment this and then comment them.
//     c_list.write_file();
//     matchListObject.write_file

        launch(args);

        c_list.sort_alphabetical_CI();//sort countrylist
        matchListObject.write_file();//write file
        c_list.write_file();//write file

        System.exit(0);//close program
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
  
        c_list = new CountryList("playerStorage.dat", "Admin.txt", "database.txt");//Create countryList  for storage country //collect data from file
        ICCRank = new ICCTestRankings(c_list);
        matchListObject = new MatchList(c_list, "matchStorage.dat", "Admin_matchViewer.txt");

//    c_list.write_file();
//       matchListObject.write_file();
        //read file 
        c_list.read_file();
        matchListObject.read_file();
        c_list.updateRatingPoint();
        initializeTransientModifier();

        window = primaryStage;
        window.setTitle("Cricket Game");

        ////////////////////////// Can't resize
        window.setResizable(false);

        //--------------------------------BUTTON CREATE-------------------------//
        displayBtn = new Button("Countries & Players");

        displayBtn.setPrefSize(buttonWidth, buttonHeight);
        iccBtn = new Button("ICC Test Ranking");
        iccBtn.setPrefSize(buttonWidth+1, buttonHeight);
        matchBtn = new Button("Matches");
        matchBtn.setPrefSize(buttonWidth, buttonHeight);
        newRandomMatchBtn = new Button("New Match");
        newRandomMatchBtn.setPrefSize(buttonWidth - 120, buttonHeight - 20);

        randomAResultBtn = new Button("Random a Result");
        randomAResultBtn.setPrefSize(buttonWidth - 120, buttonHeight - 20);

        matchhistoryBtn = new Button("Match Results");
        matchhistoryBtn.setPrefSize(buttonWidth, buttonHeight);

        addcountryBtn = new Button("Add New Country");
        addcountryBtn.setPrefSize(buttonWidth - 60, buttonHeight);

        //------------------------- Button Color & Font---------------------------------//
//       
//        displayBtn.setStyle(" -fx-background-color :#6785CE; -fx-font : 15pt \"RobotoCondensed-Bold\";");
//        iccBtn.setStyle("-fx-background-color : #50AFD9; -fx-font : 16pt \"RobotoCondensed-Bold\";");
//        matchBtn.setStyle("-fx-background-color :  #85D9B9; -fx-font : 16pt \"RobotoCondensed-Bold\";");
//        matchhistoryBtn.setStyle("-fx-background-color :  #B9F19C; -fx-font : 16pt \"RobotoCondensed-Bold\";");
//
//        addcountryBtn.setStyle(" -fx-background-color : #85D9B9; -fx-text-fill: BLACK;"+"-fx-background-radius: 20px;"); 
//        addcountryBtn.setFont(Font.loadFont("file:resources/fonts/RobotoCondensed-Bold.ttf",20));
//        newRandomMatchBtn.setStyle(" -fx-background-color :  #85D9B9; -fx-font : 16pt \"RobotoCondensed-Bold\";"+"-fx-background-radius: 20px;");
//        randomAResultBtn.setStyle(" -fx-background-color :  #B9F19C; -fx-font : 16pt \"RobotoCondensed-Bold\";"+"-fx-background-radius: 20px;");
//
        //     displayBtn.setStyle(styleinCountryAndPlayer);
//           String styleinCountryAndPlayer=" -fx-background-color :#69dce7 ;"+"fx-background-radius: -1px; ";
//        String styleICC=" -fx-background-color :#36e9bb;"+"fx-background-radius: -1px; ";
//         String styleMatches=" -fx-background-color :#5eeb91;"+ "fx-background-radius: -1px;";
//         String styleMatchResults=" -fx-background-color :#8eea5f; "+"fx-background-radius:-1px;";
        displayBtn.setStyle("-fx-background-color : #53cfdb;"+"-fx-background-radius : 0px;"+"-fx-underline: true;");
        iccBtn.setStyle(" -fx-background-color :#36e9bb;"+"-fx-background-radius: 0px;");
        matchBtn.setStyle(" -fx-background-color :#5eeb91;-fx-background-radius: 0px;");
        matchhistoryBtn.setStyle(" -fx-background-color :#8eea5f;-fx-background-radius: 0px;");

        addcountryBtn.setStyle(" -fx-background-color : #85D9B9; -fx-text-fill: BLACK;" + "-fx-background-radius: 20px;");

        // addcountryBtn.setAlignment();
        newRandomMatchBtn.setStyle(" -fx-background-color :  #C70039 ; " + "-fx-background-radius: 20px;" + "-fx-text-fill: White;");
        randomAResultBtn.setStyle(" -fx-background-color : #C70039 ; " + "-fx-background-radius: 20px;" + "-fx-text-fill: White;");

        displayBtn.setFont(Font.loadFont(fontPathBold, 20));
        iccBtn.setFont(Font.loadFont(fontPathBold, 20));
        matchBtn.setFont(Font.loadFont(fontPathBold, 20));
        matchhistoryBtn.setFont(Font.loadFont(fontPathBold, 20));
        newRandomMatchBtn.setFont(Font.loadFont(fontPathBold, 14));
        randomAResultBtn.setFont(Font.loadFont(fontPathBold, 14));
        addcountryBtn.setFont(Font.loadFont(fontPathBold, 20));

        matchhistoryBtn.setOnAction((ActionEvent t) -> {
            iccBtn.setStyle("-fx-background-color :#36e9bb;"+" -fx-background-radius: 0px;");
            matchBtn.setStyle("-fx-background-color :#5eeb91; -fx-background-radius: 0px;");
            displayBtn.setStyle("-fx-background-color :#69dce7 ; -fx-background-radius: 0px;");
            matchhistoryBtn.setStyle("-fx-background-color : #80e04f;"+
                            "-fx-background-radius : 0px;"+
                            "-fx-underline: true;"); //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            
            initializeMatchHistory();
            //       layoutBottom.getChildren().remove(addcountryBtn);
//            layoutBottom.getChildren().remove(newRandomMatchBtn);
//            layoutBottom.getChildren().remove(randomAResultBtn);
            layoutScreen.setCenter(layoutMatchHistory);
        });
        matchBtn.setOnAction((ActionEvent a) -> {
            displayBtn.setStyle(" -fx-background-color :#69dce7 ;-fx-background-radius: 0px;");
            iccBtn.setStyle(" -fx-background-color : #36e9bb;-fx-background-radius: 0px;");
            matchhistoryBtn.setStyle(" -fx-background-color :#8eea5f;-fx-background-radius: 0px;");
            matchBtn.setStyle("-fx-background-color : #49e380;"+
                            "-fx-background-radius : 0px;"+
                            "-fx-underline: true;"); //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            
            // layoutBottom.getChildren().remove(addcountryBtn);
            layoutScreen.setCenter(layoutMatches);
//            if (layoutBottom.getChildren().contains(newRandomMatchBtn) == false && layoutBottom.getChildren().contains(randomAResultBtn) == false) {
////                layoutBottom.getChildren().add(newRandomMatchBtn);
////                layoutBottom.getChildren().add(randomAResultBtn);
//            }

//            layoutBottom.getChildren().remove(addcountryBtn);
//            if (matchListObject.getNewMatchLists().size() <= 0) {
//                //nullNewMatchesScene();
//            } else {
            initializeMatches();
//            }

        });
        newRandomMatchBtn.setOnAction((ActionEvent) -> {
            matchListObject.randomNewMatch();

            matchListObject.write_file();

//            if (matchListObject.getNewMatchLists().size() <= 0) {
//              nullNewMatchesScene();
//            } else {
            initializeMatches();
//            }

        });

        randomAResultBtn.setOnAction((ActionEvent) -> {

            matchListObject.getNewMatchLists().clear();
            matchListObject.getMatchReportLists().clear();
            matchListObject.read_file();
            matchListObject.randomWinnerAndAddReports(c_list);
            matchListObject.write_file();

//            if (matchListObject.getNewMatchLists().size() <= 0) {
//               // nullNewMatchesScene();
//            } else {
            initializeMatches();
//            }

        });

        iccBtn.setOnAction((ActionEvent) -> {
            displayBtn.setStyle(" -fx-background-color :#69dce7 ;-fx-background-radius: 0px;");
            matchBtn.setStyle(" -fx-background-color :#5eeb91;-fx-background-radius: 0px;");
            matchhistoryBtn.setStyle(" -fx-background-color :#8eea5f;-fx-background-radius: 0px;");
            iccBtn.setStyle("-fx-background-color : #26e0b1;"+
                            "-fx-background-radius : 0px;"+
                            "-fx-underline: true;"); //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            layoutScreen.setCenter(layoutICC);
            //       layoutBottom.getChildren().remove(addcountryBtn);
//            layoutBottom.getChildren().remove(newRandomMatchBtn);
//            layoutBottom.getChildren().remove(randomAResultBtn);
            initializeICCRanking();

        });
        warnTextAddCountry = new Label();
        addcountryBtn.setOnAction((ActionEvent t) -> {

            addInCountryBtn.setOnAction((ActionEvent y) -> {

                if (!(isStringIntNumericOnAddCountry(countryNameInput.getText())) && countryNameInput.getText() != null && Integer.parseInt(matchInput.getText()) == (int) Integer.parseInt(matchInput.getText())) {

                    c_list.add_country(new Country(countryNameInput.getText(), Integer.parseInt(matchInput.getText())));
                    c_list.sort_alphabetical_CI();
                    initializeBtnOfCountryList();
                    //initializeTable();
                    UpdateDefaultComboBox();

                    countryNameInput.setText(null);
                    matchInput.setText(null);
                    warnTextAddCountry.setText("");
                    addNewCountry_window.close();

                }
            });

            addNewCountry_window.setOnCloseRequest(e -> {
                countryNameInput.setText(null);
                matchInput.setText(null);
                warnTextAddCountry.setText("");
            });

            addNewCountry_window.show();
        });

        //--------------------------------BUTTON CREATE-------------------------//
        //--------------------------------HBOX-----------------------------------//
        layoutTop = new HBox();
        //  layoutBottom = new HBox();
        layoutTop.getChildren().addAll(displayBtn, iccBtn, matchBtn, matchhistoryBtn);
        layoutTop.setSpacing(0);
        layoutTop.setAlignment(Pos.CENTER);
        // layoutBottom.setAlignment(Pos.CENTER);
        // layoutBottom.setSpacing(10);
        //--------------------------------HBOX-----------------------------------//
        //--------------------------------VBOX-----------------------------------//

        layoutLeft = new VBox();
        layoutRight = new VBox();
        // countryTable =new VBox();
        //--------------------------------VBOX-----------------------------------//
        //--------------------------------Scroll Pane----------------------------//
        layoutCenter = new ScrollPane();
        layoutICC = new ScrollPane();
        layoutMatches = new ScrollPane();
        layoutReport = new ScrollPane();
        layoutMatchHistory = new ScrollPane();
        //initializeTable();
        // layoutCenter.setContent(countryTable);

        //--------------------------------Scroll Pane----------------------------//
        //-------------------------------MainScreen BorderPane-------------------//
        layoutScreen = new BorderPane();
        stackPane = new StackPane();
//        BorderPane.setMargin(layoutTop, new Insets(0));
//        BorderPane.setMargin(layoutLeft, new Insets(43));
//        BorderPane.setMargin(layoutRight, new Insets(43));
//        BorderPane.setMargin(layoutBottom, new Insets(15));
        BorderPane.setMargin(layoutTop, new Insets(0));
        BorderPane.setMargin(layoutLeft, new Insets(0));
        BorderPane.setMargin(layoutRight, new Insets(0));
        //  BorderPane.setMargin(layoutBottom, new Insets(0));

        //BorderPane.setMargin(layoutCenter,Insets.EMPTY);
        //----------------------------- LOGO ----------------//++++++++++++++++++++++++++++++++++++++++++
        Image icon = new Image("file:resources/image/Phol.png");
        ImageView iconImageView = new ImageView(icon);
        iconImageView.setFitHeight(400);
        iconImageView.setFitWidth(400);
        iconImageView.setX(windowWidth / 2 - 400);
        iconImageView.setY(windowHeight / 2 - 200);

        Group root = new Group(iconImageView);

        layoutScreen.getChildren().add(root);

        displayBtn.setOnAction((ActionEvent t) -> {
            iccBtn.setStyle(" -fx-background-color :#36e9bb;"+"-fx-background-radius: 0px;");
            matchBtn.setStyle(" -fx-background-color :#5eeb91;-fx-background-radius: 0px;");
            matchhistoryBtn.setStyle(" -fx-background-color :#8eea5f;-fx-background-radius: 0px;");
            displayBtn.setStyle("-fx-background-color : #53cfdb;"+
                            "-fx-background-radius : 0px;"+
                            "-fx-underline: true;"); //+++++++++++++++++++++++++++++++++++++++++++++++
            c_list.sort_alphabetical_CI();
            layoutScreen.setCenter(defaultGridPane);
//            layoutBottom.getChildren().remove(newRandomMatchBtn);
//            layoutBottom.getChildren().remove(randomAResultBtn);
//            if (layoutBottom.getChildren().contains(addcountryBtn) == false) {
//                layoutBottom.getChildren().add(addcountryBtn);
//            }
            UpdateDefaultComboBox();
            //layoutBottom.getChildren().addAll(addcountryBtn);
        });
        //layoutBottom.getChildren().addAll(addcountryBtn);
        layoutScreen.setTop(layoutTop);
        layoutScreen.setLeft(layoutLeft);
        layoutScreen.setRight(layoutRight);
        // layoutScreen.setBottom(layoutBottom);
        stackPane.getChildren().add(layoutScreen);
        //-------------------------------MainScreen BorderPane-------------------//
        //-------------------------------addNewPlayer Stage//Scene----------------//
        addNewPlayerStackPane = new StackPane();
        addNewPlayerGridPane = new GridPane();
        addNewPlayerGridPane.setPadding(new Insets(10, 10, 10, 10));
        addNewPlayerGridPane.setVgap(8);
        addNewPlayerGridPane.setHgap(10);
        //Name Label
        Label pNameLabel = new Label("Player Name:");
        GridPane.setConstraints(pNameLabel, 0, 0);
        //Player Name Input
        pNameInput = new TextField();
        pNameInput.setPrefWidth(150);
        pNameInput.setPromptText("Name");
        GridPane.setConstraints(pNameInput, 1, 0);
        //Player Age Label
        Label ageLabel = new Label("Age:");
        GridPane.setConstraints(ageLabel, 0, 1);
        //age Input
        ageInput = new TextField();
        ageInput.setPromptText("Age");
        ageInput.setPrefWidth(150);
        GridPane.setConstraints(ageInput, 1, 1);
        //Rating Label
        Label ratingLabel = new Label("Rating:");
        GridPane.setConstraints(ratingLabel, 0, 2);

        //Rating Input
        ratingInput = new TextField();
        ratingInput.setPrefWidth(150);
        ratingInput.setPromptText("Rating");
        GridPane.setConstraints(ratingInput, 1, 2);
        //Position Label
        Label posLabel = new Label("Position:");
        GridPane.setConstraints(posLabel, 0, 3);
        //PositionInput

        positionList = new ComboBox<>();
        positionList.getItems().addAll(
                "WicketKeeper",
                "Slip",
                "Gully",
                "Point",
                "Cover",
                "Mid-off",
                "Bowler",
                "Mid-on",
                "Mid-wicket",
                "Fine leg",
                "Third man"
        );
        GridPane.setConstraints(positionList, 1, 3);
        //Button
        addInPlayerBtn = new Button("Add");
        GridPane.setConstraints(addInPlayerBtn, 1, 5);

        GridPane.setConstraints(warnTextAddPlayer, 1, 4);

        addInPlayerBtn.setStyle(styleinCountryAndPlayer + "-fx-background-radius: 20px;");
        addInPlayerBtn.setFont(Font.loadFont(fontPathBold, 14));
        pNameLabel.setFont(Font.loadFont(fontPathBold, 14));
        ageLabel.setFont(Font.loadFont(fontPathBold, 14));
        ratingLabel.setFont(Font.loadFont(fontPathBold, 14));
        posLabel.setFont(Font.loadFont(fontPathBold, 14));
        pNameInput.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
        ageInput.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
        ratingInput.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");    
        positionList.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
        
        addNewPlayerGridPane.setStyle(backgroundCountryAndPlayer);
        addNewPlayerGridPane.getChildren().addAll(pNameLabel, pNameInput, ageLabel, ageInput, ratingLabel, ratingInput, addInPlayerBtn, posLabel, positionList, warnTextAddPlayer);
        addNewPlayerStackPane.getChildren().add(addNewPlayerGridPane);
        //-------------------------------addNewPlayer Stage//Scene----------------//
//        //-------------------------------addNewCountry Stage//Scene-------------------------------///
        addNewCountryStackPane = new StackPane();
        addNewCountryGridPane = new GridPane();
        addNewCountryGridPane.setPadding(new Insets(10, 10, 10, 10));
        addNewCountryGridPane.setVgap(8);
        addNewCountryGridPane.setHgap(10);
        //CountryName Label
        Label countryNameLabel = new Label("Country Name:");
        GridPane.setConstraints(countryNameLabel, 0, 0);
        //CountryName Input
        countryNameInput = new TextField();
        countryNameInput.setPromptText("Country Name");
        GridPane.setConstraints(countryNameInput, 1, 0);
        //MatchName Label
        Label matchLabel = new Label("Matches:");
        GridPane.setConstraints(matchLabel, 0, 1);
        //MatchName Input
        matchInput = new TextField();
        matchInput.setPromptText("Matches");
        GridPane.setConstraints(matchInput, 1, 1);
           countryNameInput.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
        matchInput.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
 
        
        
        
        //Add button
        addInCountryBtn = new Button("Add");
        GridPane.setConstraints(addInCountryBtn, 1, 3);

        GridPane.setConstraints(warnTextAddCountry, 1, 2);

        countryNameLabel.setFont(Font.loadFont(fontPathBold, 14));
        matchLabel.setFont(Font.loadFont(fontPathBold, 14));
        addInCountryBtn.setStyle(styleinCountryAndPlayer + "-fx-background-radius: 20px;");
        addInCountryBtn.setFont(Font.loadFont(fontPathBold, 14));
        addNewCountryGridPane.getChildren().addAll(countryNameLabel, countryNameInput, matchLabel, matchInput,
                warnTextAddCountry, addInCountryBtn);
        addNewCountryGridPane.setStyle(backgroundCountryAndPlayer);
        addNewCountryStackPane.getChildren().add(addNewCountryGridPane);

//        //-------------------------------addNewCountry Stage//Scene-------------------------------///
        //-------------------------------Default add Player and Country---------------------------///
        //Use same stackpane as  Mainscreen
        defaultGridPane = new GridPane();
        //defaultGridPane.setGridLinesVisible(true);
        defaultGridPane.setPadding(new Insets(100, 150, 150, 250));

        defaultGridPane.setVgap(8);
        defaultGridPane.setHgap(16);

        //defaultCountryLabel
        VBox countrySelect = new VBox();
        
        HBox labelAndComboSet = new HBox();
        HBox nextAndAddNewCountry = new HBox();

        Label defaultCountryLabel = new Label("Country:");
  
        defaultCountryLabel.setFont(Font.loadFont(fontPathBold, 25));
   
       
        defaultCountryLabel.setMinWidth(70);

        // GridPane.setConstraints(defaultCountryLabel, 15, 15);
        //DefaultCountry Combobox
        defaultCountryList = new ComboBox<>();
      defaultCountryList.setStyle( "-fx-background-color :White ;-fx-background-radius: 0px;");
        defaultCountryList.setPrefWidth(200);
        for (Country instance : c_list.getCountryList()) {
            defaultCountryList.getItems().add(
                    instance.name
            );
        }

        // GridPane.setConstraints(defaultCountryList, 16, 15);
        //defaultGo button
        defaultGoBtn = new Button("Next");
        defaultGoBtn.setPrefWidth(100);
        defaultGoBtn.setStyle(" -fx-background-color : #85D9B9; -fx-text-fill: black;" + "-fx-background-radius: 20px;");

        defaultGoBtn.setFont(Font.loadFont(fontPathBold, 20));

        Label warnTextSearchCountry = new Label();

        defaultGoBtn.setOnAction((ActionEvent t) -> {
            String lastcountry = defaultCountryList.getValue();
            if (lastcountry != null) {

                warnTextSearchCountry.setText("");
                layoutScreen.setCenter(layoutCenter);
                initializeTable(lastcountry);

                c_list.getCountryList().get(c_list.findCountry(defaultCountryList.getValue())).addNewplayerBtn.setOnAction((ActionEvent y) -> {

                    addNewPlayer_window.show();

                    addInPlayerBtn.setOnAction((ActionEvent u) -> {
                        System.out.println(!(isStringIntNumericOnAddPlayer(pNameInput.getText())));
                        if (Integer.parseInt(ratingInput.getText()) == (int) Integer.parseInt(ratingInput.getText()) && !(isStringIntNumericOnAddPlayer(pNameInput.getText())) && Integer.parseInt(ageInput.getText()) == (int) Integer.parseInt(ageInput.getText()) && ageInput.getText() != null && pNameInput.getText() != null && ratingInput.getText() != null && positionList.getValue() != null) {

                            c_list.add_player(lastcountry, new Player(Integer.parseInt(ratingInput.getText()), pNameInput.getText(),
                                    Integer.parseInt(ageInput.getText()), positionList.getValue()));
                            c_list.updateRatingPoint();
                            initializeTable(lastcountry);

                            for (Player instance : c_list.getCountryList().get(c_list.findCountry(lastcountry)).playerList) {
                                instance.delBtn.setOnAction((ActionEvent k) -> {
                                    c_list.remove_player(lastcountry, instance.name);
                                    c_list.updateRatingPoint();
                                    initializeTable(lastcountry);
                                });
                                instance.statisticBtn.setOnAction((ActionEvent k) -> {
                                    initializePlayerReport(lastcountry, instance.name);
                                    playerReport_window.show();
                                });
                            }
                            warnTextAddPlayer.setText("");
                            ratingInput.setText(null);
                            pNameInput.setText(null);
                            ageInput.setText(null);
                            positionList.setValue(null);
                            addNewPlayer_window.close();
                        }

                    });
                    addNewPlayer_window.setOnCloseRequest(e -> {
                        ratingInput.setText(null);
                        pNameInput.setText(null);
                        ageInput.setText(null);
                        positionList.setValue(null);
                        warnTextAddPlayer.setText("");

                    });

                });
                for (Player instance : c_list.getCountryList().get(c_list.findCountry(lastcountry)).playerList) {
                    instance.delBtn.setOnAction((ActionEvent k) -> {
                        c_list.remove_player(lastcountry, instance.name);
                        c_list.updateRatingPoint();
                        initializeTable(lastcountry);
                    });
                    instance.statisticBtn.setOnAction((ActionEvent k) -> {
                        initializePlayerReport(lastcountry, instance.name);
                        playerReport_window.show();
                    });
                }

                c_list.getCountryList().get(c_list.findCountry(lastcountry)).deleteCountryBtn.setOnAction((ActionEvent y) -> {
                    c_list.del_country(lastcountry);
                    layoutScreen.setCenter(defaultGridPane);
                    UpdateDefaultComboBox();
                });
            } else {
                warnTextSearchCountry.setText("PLEASE CHOOSE A COUNTRY!");

                warnTextSearchCountry.setStyle("-fx-text-fill:RED");
                warnTextSearchCountry.setFont(Font.loadFont(fontPathBold, 20));
            }
        });

        // GridPane.setConstraints(defaultGoBtn, 16, 16);
//GridPane.setConstraints(addcountryBtn, 17, 16);
        //  GridPane.setConstraints(warnTextSearchCountry, 16, 17);
        
        labelAndComboSet.getChildren().addAll(defaultCountryLabel, defaultCountryList);

        labelAndComboSet.setSpacing(10);

        nextAndAddNewCountry.getChildren().addAll(defaultGoBtn, addcountryBtn);
        nextAndAddNewCountry.setSpacing(10);

        countrySelect.getChildren().addAll(labelAndComboSet, nextAndAddNewCountry, warnTextSearchCountry);
        countrySelect.setSpacing(10);

        GridPane.setConstraints(countrySelect, 15, 15);

        defaultGridPane.getChildren().addAll(countrySelect);
        // defaultGridPane.setStyle(backgroundCountryAndPlayer);

        c_list.sort_alphabetical_CI();
        layoutScreen.setCenter(defaultGridPane);
//
//        if (layoutBottom.getChildren().contains(addcountryBtn) == false) {
//            layoutBottom.getChildren().add(addcountryBtn);
//        }
        UpdateDefaultComboBox();

        //-------------------------------Default add Player and Country---------------------------///
        //-------------------------------playerReport---------------------------------------------//
        playerReportStackPane = new StackPane();
        playerReportStackPane.setAlignment(Pos.CENTER);

        playerReportStackPane.getChildren().add(layoutReport);
        //-------------------------------playerReport---------------------------------------------//
        mainScene = new Scene(stackPane, windowWidth, windowHeight);
        window.setScene(mainScene);
        addNewPlayerScene = new Scene(addNewPlayerStackPane, 300, 225);
        addNewPlayer_window = new Stage();
        addNewPlayer_window.setTitle("Add New Player");
        addNewPlayer_window.setScene(addNewPlayerScene);
        addNewCountryScene = new Scene(addNewCountryStackPane, 300, 150);
        addNewCountry_window = new Stage();
        addNewCountry_window.setTitle("Add New Country");
        addNewCountry_window.setScene(addNewCountryScene);

        playerReportScene = new Scene(playerReportStackPane, 710, 425);

        playerReport_window = new Stage();
        playerReport_window.setTitle("Player Reports");
        playerReport_window.setScene(playerReportScene);
               window.getIcons().add(new Image("file:resources/image/icon.png"));
   addNewPlayer_window.getIcons().add(new Image("file:resources/image/icon.png"));
    addNewCountry_window.getIcons().add(new Image("file:resources/image/icon.png"));
     playerReport_window.getIcons().add(new Image("file:resources/image/icon.png"));

        window.show();

        // *****-------------------------------- Background Style---------------------------------------//
        stackPane.setStyle(backgroundCountryAndPlayer);

        addNewPlayerStackPane.setStyle(backgroundCountryAndPlayer);
        addNewCountryStackPane.setStyle(backgroundCountryAndPlayer);
        playerReportStackPane.setStyle(backgroundCountryAndPlayer);

        //-------------- Fix Size----------------------------------------------//
        addNewPlayer_window.setResizable(false);
        addNewCountry_window.setResizable(false);
        playerReport_window.setResizable(false);
    }

    public void initializeMatchHistory() {
        VBox matchhistoryBox = new VBox();
        VBox topBox = new VBox();
       
        matchhistoryBox.setSpacing(10);
        matchhistoryBox.setPadding(new Insets(10, 5, 10, 15));
        matchhistoryBox.setAlignment(Pos.TOP_CENTER);

        Label recentlyMatch = new Label("10 Recent Matches: " + matchListObject.getMatchReportLists().size());
        recentlyMatch.setFont(Font.loadFont(fontPathBold, 30));
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.getChildren().add(recentlyMatch);
        topBox.setStyle(styleMatchResults);
        matchhistoryBox.getChildren().add(topBox);

        for (int i = matchListObject.getMatchReportLists().size() - 1; i >= 0; i--) {
            HBox tableBox = new HBox();
            tableBox.setSpacing(20);
            TableView teamOneTable = new TableView();
            TableView teamTwoTable = new TableView();
            teamOneTable.setMaxHeight(310);
            teamOneTable.setMinWidth(385);
            teamTwoTable.setMaxHeight(310);
            teamTwoTable.setMinWidth(385);

            teamOneTable.setStyle("-fx-background-color: transparent");
            teamTwoTable.setStyle("-fx-background-color: transparent");

            ObservableList<Player> dataTeamOne = FXCollections.observableArrayList(matchListObject
                    .getMatchReportLists().get(i).getTeamMatch().get(0).getPlayerTeam());
            ObservableList<Player> dataTeamTwo = FXCollections.observableArrayList(matchListObject
                    .getMatchReportLists().get(i).getTeamMatch().get(1).getPlayerTeam());
            //Label match detail
            Label matchDetail = new Label(matchListObject.getMatchReportLists().get(i).getDetail());
            matchDetail.setFont(Font.loadFont(fontPathBold, 26));
            //Label endmatch detail
            Label endmatchDetail = new Label(matchListObject.getMatchReportLists().get(i).getEnd_matches());
            endmatchDetail.setFont(Font.loadFont(fontPathBold, 26));
            
            VBox labelHead=new VBox(matchDetail,endmatchDetail);
            labelHead.setStyle("-fx-background-color:#afef8f;");
          
            labelHead.setAlignment(Pos.CENTER);
            //Column
            TableColumn teamOneName = new TableColumn("Name");
            teamOneName.setMinWidth(128);
            teamOneName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            TableColumn teamOnePos = new TableColumn("Fielding");
            teamOnePos.setMinWidth(128);
            teamOnePos.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            TableColumn teamOneRating = new TableColumn("Rating");
            teamOneRating.setMinWidth(128);
            teamOneRating.setStyle("-fx-alignment: CENTER;");
            teamOneRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));

            TableColumn teamTwoName = new TableColumn("Name");
            teamTwoName.setMinWidth(128);
            teamTwoName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            TableColumn teamTwoPos = new TableColumn("Fielding");
            teamTwoPos.setMinWidth(128);
            teamTwoPos.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            TableColumn teamTwoRating = new TableColumn("Rating");
            teamTwoRating.setMinWidth(128);
            teamTwoRating.setStyle("-fx-alignment: CENTER;");
            teamTwoRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));
            //Set item
            teamOneTable.setItems(dataTeamOne);
            teamOneTable.getColumns().addAll(teamOneName, teamOnePos, teamOneRating);
            teamTwoTable.setItems(dataTeamTwo);
            teamTwoTable.getColumns().addAll(teamTwoName, teamTwoPos, teamTwoRating);

            tableBox.getChildren().addAll(teamOneTable, teamTwoTable);
            tableBox.setAlignment(Pos.CENTER);
            
            
            matchhistoryBox.getChildren().addAll(labelHead, tableBox);
    
        }
                matchhistoryBox.setStyle(backgroundMatchResults);
            matchhistoryBox.setMinSize(windowWidth - 15, windowHeight);

        layoutMatchHistory.setContent(matchhistoryBox);

        layoutMatchHistory.setStyle("-fx-background-color: transparent");

    }

    public void initializePlayerReport(String cName, String pName) {
        VBox playerReportBox = new VBox();
        VBox topBox = new VBox();

        playerReportBox.setMinSize(695, 425);

        playerReportBox.setSpacing(10);
        playerReportBox.setPadding(new Insets(10, 5, 10, 15));

        Label recentlyMatch = new Label("3 Recent Matches: " + c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                .get(c_list.findPlayerInCountry(cName, pName)).getReport().size());
        recentlyMatch.setFont(Font.loadFont(fontPathBold, 30));
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.getChildren().add(recentlyMatch);
        topBox.setStyle(styleinCountryAndPlayer);

        /*
        if(c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                .get(c_list.findPlayerInCountry(cName, pName)).getReport().size()<=0){
            
            return false;
        }*/
        playerReportBox.getChildren().add(topBox);
        for (int i = c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                .get(c_list.findPlayerInCountry(cName, pName)).getReport().size()-1; i >=0 ; i--) {
            HBox TableBox = new HBox();
            TableBox.setSpacing(20);
            TableView teamOneTable = new TableView();
            TableView teamTwoTable = new TableView();
            teamOneTable.setStyle("-fx-background-color: transparent");
            teamTwoTable.setStyle("-fx-background-color: transparent");
            teamOneTable.setMaxHeight(310);
            teamOneTable.setMinWidth(327);
            teamTwoTable.setMaxHeight(310);
            teamTwoTable.setMinWidth(327);
            ObservableList<Player> dataTeamOne = FXCollections.observableArrayList(c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                    .get(c_list.findPlayerInCountry(cName, pName)).getReport().get(i).getTeamMatch().get(0).getPlayerTeam());
            ObservableList<Player> dataTeamTwo = FXCollections.observableArrayList(c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                    .get(c_list.findPlayerInCountry(cName, pName)).getReport().get(i).getTeamMatch().get(1).getPlayerTeam());
            teamOneTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            teamTwoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            teamOneTable.setEditable(false);
            teamTwoTable.setEditable(false);

            //Label match detail
            Label matchDetail = new Label(c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                    .get(c_list.findPlayerInCountry(cName, pName)).getReport().get(i).getDetail());
            matchDetail.setFont(Font.loadFont(fontPathBold, 26));
            //Label endmatch detail
            Label endmatchDetail = new Label(c_list.getCountryList().get(c_list.findCountry(cName)).playerList
                    .get(c_list.findPlayerInCountry(cName, pName)).getReport().get(i).getEnd_matches());
            endmatchDetail.setFont(Font.loadFont(fontPathBold, 26));
            
               VBox labelHead=new VBox(matchDetail,endmatchDetail);
            labelHead.setStyle("-fx-background-color:#92e5ed;");
          
            labelHead.setAlignment(Pos.CENTER);
            
            
            //Column
            TableColumn teamOneName = new TableColumn("Name");
            teamOneName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            TableColumn teamOnePos = new TableColumn("Position");
            teamOnePos.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            TableColumn teamOneRating = new TableColumn("Rating");
            teamOneRating.setStyle("-fx-alignment: CENTER;");
            teamOneRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));

            TableColumn teamTwoName = new TableColumn("Name");
            teamTwoName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            TableColumn teamTwoPos = new TableColumn("Positon");
            teamTwoPos.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            TableColumn teamTwoRating = new TableColumn("Rating");
            teamTwoRating.setStyle("-fx-alignment: CENTER;");
            teamTwoRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));
            //Set item
            teamOneTable.setItems(dataTeamOne);
            teamOneTable.getColumns().addAll(teamOneName, teamOnePos, teamOneRating);
            teamTwoTable.setItems(dataTeamTwo);
            teamTwoTable.getColumns().addAll(teamTwoName, teamTwoPos, teamTwoRating);

            TableBox.getChildren().addAll(teamOneTable, teamTwoTable);
            playerReportBox.getChildren().addAll(labelHead, TableBox);

        }
        playerReportBox.setAlignment(Pos.TOP_CENTER);
        playerReportBox.setStyle(backgroundCountryAndPlayer);
        layoutReport.setContent(playerReportBox);
    }

    public boolean initializeICCRanking() {

        if (ICCRank.getData().getCountryList().size() <= 0) {//Empty List
            //window.setScene(defaultScene);
            UpdateDefaultComboBox();
//            if (layoutBottom.getChildren().contains(addcountryBtn) == false) {
//                layoutBottom.getChildren().add(addcountryBtn);
//            }
            layoutScreen.setCenter(defaultGridPane);
            return false;
        }
        ICCRank.sort();
        for (int i = 1; i <= ICCRank.getData().getCountryList().size(); i++) {
            ICCRank.getData().getCountryList().get(i - 1).num = i;
        }
        VBox ICCBox = new VBox();
        HBox ICCLabelBox = new HBox();
        TableView ICCTable = new TableView<>();
        ICCBox.setAlignment(Pos.CENTER);
        ICCBox.setPadding(new Insets(10, 5, 10, 15));
        ICCTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ICCTable.setEditable(false);
        ICCTable.setMaxWidth(800);

        ObservableList<Country> data = FXCollections.observableArrayList(ICCRank.getData().getCountryList());
        //Top Label
        Label ICCLabel = new Label("ICC Test Teams Ranking");

        ICCLabel.setFont(Font.loadFont(fontPathBold, 30));

        ICCTable.setMinHeight(ICCRank.getData().getCountryList().size() * 25.1);
        ICCLabelBox.getChildren().add(ICCLabel);
        ICCLabelBox.setStyle(styleICC);
        //Create Column
        TableColumn Pos = new TableColumn("Pos");
        Pos.setMaxWidth(60);
        Pos.setStyle("-fx-alignment: CENTER-RIGHT;");
        Pos.setCellValueFactory(new PropertyValueFactory<Country, String>("num"));
        TableColumn Team = new TableColumn("Team");
        Team.setMaxWidth(250);
        Team.setStyle("-fx-alignment: CENTER-LEFT;");
        Team.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));

        TableColumn weightmatch = new TableColumn("Weighted Matches");
        weightmatch.setStyle("-fx-alignment: CENTER;");
        weightmatch.setMaxWidth(173);
        weightmatch.setCellValueFactory(new PropertyValueFactory<Country, String>("ICCTestWeight_Matches"));
        TableColumn points = new TableColumn("Points");
        points.setStyle("-fx-alignment: CENTER;");
        points.setMaxWidth(173);
        points.setCellValueFactory(new PropertyValueFactory<Country, String>("ICCTestPoints"));
        TableColumn rating = new TableColumn("Rating");
        rating.setMaxWidth(173);
        rating.setStyle("-fx-alignment: CENTER;");
        rating.setCellValueFactory(new PropertyValueFactory<Country, String>("ICCTestRating"));
        ICCTable.setItems(data);
        ICCTable.getColumns().addAll(Pos, Team, weightmatch, points, rating);
        ICCBox.setSpacing(25);

        ICCTable.setStyle("-fx-background-color: transparent");

        ICCBox.getChildren().addAll(ICCLabelBox, ICCTable);
        ICCBox.setMinSize(windowWidth - 15, windowHeight);
        ICCBox.setStyle(backgroundICC);
        layoutICC.setContent(ICCBox);
        return true;
    }

    public boolean initializeTable(String cName) {
        //Label label_country=new Label("Country Name");
        //label_country.setFont(Font.font("Arial", 25));
        //countryTable.getChildren().add(label_country);
        VBox countryTable = new VBox();
        countryTable.setPadding(new Insets(10, 5, 10, 15));

        if (c_list.getCountryList().size() <= 0) {//Empty List
            //window.setScene(defaultScene);
            UpdateDefaultComboBox();
            layoutScreen.setCenter(defaultGridPane);
            return false;
        }

        // for(int i=0;i<c_list.getCountryList().size();i++){
        TableView<Player> countrylist = new TableView<>();

      countrylist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox layoutAddDelPC = new HBox();

        layoutAddDelPC.setSpacing(10);

countrylist.setPrefHeight(c_list.getCountryList().get(c_list.findCountry(cName)).playerList.size() * 36);
  
      countrylist.setEditable(false);
        layoutAddDelPC.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Player> data = FXCollections.observableArrayList(c_list.getCountryList().get(c_list.findCountry(cName)).playerList);
        //System.out.println(data);
        Label label = new Label(c_list.getCountryList().get(c_list.findCountry(cName)).name + "   Matches: " + c_list.getCountryList().get(c_list.findCountry(cName)).ICCTestWeight_Matches
                + "   Point: " + c_list.getCountryList().get(c_list.findCountry(cName)).ICCTestPoints + "   Rating: " + c_list.getCountryList().get(c_list.findCountry(cName)).ICCTestRating);
        label.setFont(Font.loadFont(fontPathBold, 30));
        layoutAddDelPC.getChildren().addAll(label, c_list.getCountryList().get(c_list.findCountry(cName)).addNewplayerBtn, c_list.getCountryList().get(c_list.findCountry(cName)).deleteCountryBtn);
        layoutAddDelPC.setStyle(styleinCountryAndPlayer);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setStyle("-fx-alignment: CENTER-LEFT;");
       // nameCol.setPrefWidth(200);
        nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn posCol = new TableColumn("Fielding");
        posCol.setStyle("-fx-alignment: CENTER-LEFT");
       // posCol.setPrefWidth(175);
        posCol.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setStyle("-fx-alignment: CENTER");
       // ageCol.setPrefWidth(175);
        ageCol.setCellValueFactory(new PropertyValueFactory<Player, String>("age"));
        TableColumn ratingCol = new TableColumn("Rating");
        ratingCol.setStyle("-fx-alignment: CENTER");
        ratingCol.setPrefWidth(175);
        ratingCol.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));
        TableColumn playerReportCol = new TableColumn("Report");
      //  playerReportCol.setPrefWidth(175);
        playerReportCol.setStyle("-fx-alignment: CENTER");
        playerReportCol.setCellValueFactory(new PropertyValueFactory<Player, String>("statisticBtn"));
        TableColumn delplayerCol = new TableColumn("Delete");
       //delplayerCol.setMinWidth(50);
       
        delplayerCol.setStyle("-fx-alignment: CENTER");
        delplayerCol.setCellValueFactory(new PropertyValueFactory<Player, String>("delBtn"));
        countrylist.setItems(data);
        countrylist.getColumns().addAll(nameCol, posCol, ageCol, ratingCol, playerReportCol, delplayerCol);
       //countrylist.setMinSize(windowWidth-100, windowHeight);
        countryTable.setSpacing(10);

        countryTable.setAlignment(Pos.CENTER);
        countryTable.setMinSize(windowWidth - 15, windowHeight);
       countrylist.setStyle("-fx-background-color: transparent");
        countryTable.getChildren().addAll(layoutAddDelPC, countrylist);
        countryTable.setStyle(backgroundCountryAndPlayer);

        layoutCenter.setContent(countryTable);

        //} 
        return true;
    }

    public void initializeTransientModifier() {
        initializeBtnOfCountryList();
        initializeBtnOfCountry();
    }

    public void initializeBtnOfCountryList() {
        for (Country instance : c_list.getCountryList()) {
            instance.deleteCountryBtn = new Button("Delete Country");
            instance.addNewplayerBtn = new Button("Add New Player");
            instance.deleteCountryBtn.setPrefSize(buttonWidth - 120, buttonHeight - 20);
            instance.addNewplayerBtn.setPrefSize(buttonWidth - 120, buttonHeight - 20);
            instance.addNewplayerBtn.setStyle(" -fx-background-color :#C70039 ; " + "-fx-text-fill: White;" + "-fx-background-radius: 20px;");
            instance.deleteCountryBtn.setStyle(" -fx-background-color :#C70039  ; " + "-fx-text-fill: White;" + "-fx-background-radius: 20px;");
            instance.addNewplayerBtn.setFont(Font.loadFont(fontPathBold, 14));
            instance.deleteCountryBtn.setFont(Font.loadFont(fontPathBold, 14));

        }
    }

    public void initializeBtnOfCountry() {
        for (Country instance : c_list.getCountryList()) {
            for (Player instance2 : instance.playerList) {
                instance2.statisticBtn = new Button("Player Reports");
                instance2.delBtn = new Button("X");

                instance2.statisticBtn.setStyle(" -fx-background-color :#92e5ed  ; "+ "-fx-background-radius: 20px;");
                instance2.delBtn.setStyle(" -fx-background-color :#C70039  ; " + "-fx-text-fill: White;" + "-fx-background-radius: 20px;");
                instance2.statisticBtn.setFont(Font.loadFont(fontPathBold, 14));
                instance2.delBtn.setFont(Font.loadFont(fontPathBold, 14));

            }
        }
    }

    public void UpdateDefaultComboBox() {
        defaultCountryList.getItems().clear();
        for (Country instance : c_list.getCountryList()) {
            defaultCountryList.getItems().add(
                    instance.name
            );
        }

    }

    public boolean initializeMatches() {

        VBox outerLayoutMatches = new VBox();
        HBox layoutMatchesBox = new HBox();

        Label amountOfMatches = new Label("10 New Matches: " + matchListObject.getNewMatchLists().size());
        amountOfMatches.setFont(Font.loadFont(fontPathBold, 30));

        //outerLayoutMatches.getChildren().addAll(amountOfMatches);
        layoutMatchesBox.getChildren().addAll(amountOfMatches, newRandomMatchBtn, randomAResultBtn);

        layoutMatchesBox.setSpacing(10);
        layoutMatchesBox.setAlignment(Pos.CENTER_LEFT);
        outerLayoutMatches.getChildren().addAll(layoutMatchesBox);
        outerLayoutMatches.setPadding(new Insets(10, 5, 10, 15));
        layoutMatchesBox.setStyle(styleMatches);
        for (int i = matchListObject.getNewMatchLists().size() - 1; i >= 0; i--) {

            outerLayoutMatches.setSpacing(20);
            layoutMatchesBox = new HBox();
            layoutMatchesBox.setSpacing(20);
           layoutMatchesBox.setAlignment(Pos.CENTER);
            Label matchName = new Label(matchListObject.getNewMatchLists().get(i).getDetail());

            matchName.setFont(Font.loadFont(fontPathBold, 26));
            
            HBox headLabel=new HBox(matchName);
            headLabel.setStyle("-fx-background-color: #8ef1b2;");
            headLabel.setAlignment(Pos.CENTER);
            TableView MatchesTeamOneTable = new TableView<>();

            MatchesTeamOneTable.setPrefWidth(372);
            MatchesTeamOneTable.setMaxHeight(310);
            TableColumn teamOneName = new TableColumn("Name");
            TableColumn teamOneFielding = new TableColumn("Fielding");
            TableColumn teamOneRating = new TableColumn("Rating");

            teamOneName.setEditable(false);
            teamOneRating.setEditable(false);
            teamOneFielding.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
            teamOneName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            teamOneRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));
            teamOneName.setStyle("-fx-alignment: CENTER-LEFT");
            teamOneRating.setStyle("-fx-alignment: CENTER");
            teamOneFielding.setStyle("-fx-alignment: CENTER-LEFT");
            teamOneName.setMinWidth(123);
            teamOneRating.setMinWidth(123);
            teamOneFielding.setMinWidth(124);
            ObservableList<Player> dataTeamOne = FXCollections.observableArrayList(matchListObject.getNewMatchLists().get(i).getTeamMatch().get(0).getPlayerTeam());
            MatchesTeamOneTable.setItems(dataTeamOne);
            MatchesTeamOneTable.getColumns().addAll(teamOneName, teamOneFielding, teamOneRating);

            TableView MatchesTeamTwoTable = new TableView<>();

            TableColumn teamTwoName = new TableColumn("Name");
            TableColumn teamTwoRating = new TableColumn("Rating");
            TableColumn teamTwoFielding = new TableColumn("Fielding");
            teamTwoName.setEditable(false);
            teamTwoRating.setEditable(false);
            MatchesTeamTwoTable.setPrefWidth(372);
            teamTwoName.setStyle("-fx-alignment: CENTER-LEFT");
            teamTwoRating.setStyle("-fx-alignment: CENTER");
            teamTwoFielding.setStyle("-fx-alignment: CENTER-LEFT");
            teamTwoName.setMinWidth(123);
            teamTwoRating.setMinWidth(123);
            teamTwoFielding.setMinWidth(124);

            MatchesTeamTwoTable.setMaxHeight(310);
            teamTwoName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            teamTwoFielding.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));

            teamTwoRating.setCellValueFactory(new PropertyValueFactory<Player, String>("rating"));

            ObservableList<Player> dataTeamTwo = FXCollections.observableArrayList(matchListObject.getNewMatchLists().get(i).getTeamMatch().get(1).getPlayerTeam());

            MatchesTeamTwoTable.setItems(dataTeamTwo);
            MatchesTeamTwoTable.getColumns().addAll(teamTwoName, teamTwoFielding, teamTwoRating);
            MatchesTeamOneTable.setStyle("-fx-background-color: transparent");
            MatchesTeamTwoTable.setStyle("-fx-background-color: transparent");

            layoutMatchesBox.getChildren().addAll(MatchesTeamOneTable, MatchesTeamTwoTable);

           
            outerLayoutMatches.getChildren().addAll(headLabel, layoutMatchesBox);

        }
          outerLayoutMatches.setAlignment(Pos.TOP_CENTER);
        outerLayoutMatches.setMinSize(windowWidth - 15, windowHeight);
        outerLayoutMatches.setStyle(backgroundMatches);
        layoutMatches.setContent(outerLayoutMatches);
        return true;
    }
//
//    public boolean nullNewMatchesScene() {
//        VBox outerLayoutMatches = new VBox();
//
//       outerLayoutMatches.setPadding(new Insets(10, 0, 0, 10));
//
//        Label amountOfMatches = new Label("New Matches: " + matchListObject.getNewMatchLists().size());
//        amountOfMatches.setFont(Font.loadFont(fontPathBold, 40));
//
//        outerLayoutMatches.getChildren().addAll(amountOfMatches);
//
//        layoutMatches.setContent(outerLayoutMatches);
//        return true;
//    }

    public boolean isStringIntNumericOnAddCountry(String test) {
        try {
            int i = Integer.parseInt(test);

            return true;

        } catch (NumberFormatException e) {

            warnTextAddCountry.setText("PLEASE TRY AGAIN!");
            warnTextAddCountry.setFont(Font.loadFont(fontPathBold, 14));
            warnTextAddCountry.setStyle("-fx-text-fill:RED");

            return false;
        }

    }

    public boolean isStringIntNumericOnAddPlayer(String test) {
        try {
            int i = Integer.parseInt(test);

            return true;

        } catch (NumberFormatException e) {

            warnTextAddPlayer.setText("PLEASE TRY AGAIN!");
            warnTextAddPlayer.setFont(Font.loadFont(fontPathBold, 14));
            warnTextAddPlayer.setStyle("-fx-text-fill:RED");

            return false;
        }

    }

}

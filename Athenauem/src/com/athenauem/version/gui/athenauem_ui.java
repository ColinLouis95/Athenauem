package com.athenauem.version.gui;

import javafx.scene.control.TextArea;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*			Colin Kugler
 *  
 *  		github-ColinLouis95
 *  
 * 			Athenauem
 * 
 * 			-This class will create a GUI application layer for Athenauem.
 * 			-The user will be able to interact with the vault using the functionality of the GUI layer.
 * 
 * 			-vaultGUI is controlled by the password_vaultGUI file.
 * 			
 * 			-There are methods that will be used in creating VBoxs, HBoxs, Buttons, etc to maintain clean code.
 			-They will be used in creating objects that will define the look of the Password-Vault.
 			
 			-Scenes will be made out of FlowPanes, to have a flowing effect for the buttons/input fields,
 			-While the showVault method is made with a GridPane, due to better output visibility. 
 			
 			-The Start method will contain all of the class methods and functions in using the Password-Vault, 
 			 in which main will run the program.
 			
 			-any recommendations to improve code is welcomed via discord message.
 */

// vaultUI class that will handle the look of the vault, as well as methods for changing the screens
public class athenauem_ui extends Application{
	
	athenauem_ui_logic pv = new athenauem_ui_logic();
	Stage window;
	Scene main, scene1, scene2, scene3, scene4, scene5;
	
// method for making the home screen	
		public FlowPane homeScreen(HBox welcome, Button addVault, Button updateVault, Button deleteVault,
							   	   Button showVault, Button saveVault) {
		// FlowPane root			
			FlowPane root = makeFP();
			
		// Logo for password-vault
			VBox logo = makeLogo();

		// VBox to hold Buttons	
			VBox holdButtons = new VBox(10);

		// add Buttons to HBox and VBox respectively	
			holdButtons.setAlignment(Pos.CENTER);
			holdButtons.getChildren().addAll(addVault,updateVault,deleteVault,showVault,saveVault);

		// add everything to FlowPane	
			root.getChildren().add(welcome);
			root.getChildren().add(logo);
			root.getChildren().add(holdButtons);
			
		// creating actions for the buttons	to go to different scenes
			addVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(scene1);}});
			updateVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(scene2);}});
			deleteVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(scene3);}});
			showVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(scene4);}});
			saveVault.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(scene5);}});
		
			return root;
		}	
		
// method for adding items to the vault
		public FlowPane addToVault() {
		// add welcome logo
			VBox logo = makeLogo();
			
		// root GridPane to hold all associated objects
			FlowPane root = makeFP();
			root.setHgap(8);
			root.setVgap(8);
			
		// TextField user w/ default attributes 	
			TextField user = makeTF("Enter a user-name:");
			user.setAlignment(Pos.TOP_CENTER);
		
		// TextField password	
			TextField pass = makeTF("Enter a password:");
			pass.setAlignment(Pos.CENTER);
			
		// TextField origin w/ default attributes 	
			TextField origin = makeTF("Enter the origin of use:");
			origin.setAlignment(Pos.BASELINE_CENTER);
			
		// button add that when pressed will save the textfield inputs to the Password-Vault
			Button add = makeButton();
			add.setText("Add");
			
		// event handler when add is pressed info is saved to the vault and the textfields are cleared for new input	
			add.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e){
					String key = user.getText();
					String value1 = pass.getText();
					String value2 = origin.getText();
					pv.addInfo(key, value1, value2); 
					
					user.clear(); pass.clear(); origin.clear();
					}
				});
		
		// button for exiting back to home screen.
			Button home = makeButton();
			home.setText("Return to the Vault");
			home.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(main);}});
		
		// adding everything to the root FlowPane	
			root.getChildren().add(logo);
			root.getChildren().add(user);
			root.getChildren().add(pass);
			root.getChildren().add(origin);
			root.getChildren().add(add);
			root.getChildren().add(home);
			return root;
		}
		
// method for showing the vault		
		public GridPane showVault() {
		// root GridPane to hold all associated objects
			GridPane root = makeGP();
			
		// TextArea displaying that the user will have to click on "show" button to display vault.
			Label security = makeLabel();
			security.setWrapText(true);
			security.setFont(Font.font("Source Serif Pro",FontWeight.BOLD,FontPosture.ITALIC,14));
			security.setText("For Security reasons you must click on Reveal Vault to display your Vault's contents");
			
		// button when pressed will show the vault contents, for security reasons
			Button show = makeButton();
			show.setText("Reveal Vault");
		
			// event handler that will either show the vault's contents or display a .PNG image saying the vaults empty	
			EventHandler<ActionEvent> reveal = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ImageView emptyVault;
					VBox holdIM = makeVBox();
					VBox holdCON = makeVBox();
					
					if(pv.returnVaultUI().isEmpty()) {
						try {
							root.getChildren().remove(security);
							Image image = new Image(new FileInputStream("src/images/empty.png"));
							emptyVault = new ImageView(image);
							holdIM.getChildren().add(emptyVault);
							root.add(holdIM, 0, 0);
						}
						catch(FileNotFoundException e) {
							System.out.println("No file present");
							e.printStackTrace();
						}
					}

					if(!pv.returnVaultUI().isEmpty()) {
						root.getChildren().remove(holdIM);
						String vault = pv.returnVaultUI();
						TextArea content = makeTA(vault); 
						root.getChildren().remove(security);
						holdCON.getChildren().add(content);
						root.add(holdCON,0,0);
						
					}
				}
			};
			show.setOnAction(reveal);
			show.setOnAction(null);
			show.setOnAction(reveal);
		
		// button that will take the user back to home screen, with event handler		
			Button home = makeButton();
			home.setText("Return to the Vault");
			EventHandler<ActionEvent> returnHome = new EventHandler<ActionEvent>() {
				@Override 
				public void handle(ActionEvent event) {
					window.setScene(main);
				}
			};
			home.setOnAction(returnHome);
			home.setOnAction(null);
			home.setOnAction(returnHome);
			
			root.add(security, 1, 0);
			root.add(show, 0, 6);
			root.add(home, 0, 7);
			return root;
		}
		
// method for updating the password vault
		public FlowPane updateVault() {
		// Logo
			VBox logo = makeLogo();
			
		// root GridPane to hold all associated objects
			FlowPane root = makeFP();
			root.setHgap(8);
			root.setVgap(8);
			
		// TextField user w/ default attributes 	
			TextField user = makeTF("Enter the username:");
			user.setAlignment(Pos.TOP_CENTER);
			
		// TextField password w/ default attributes 	
			TextField pass = makeTF("Enter the old password:");
			pass.setAlignment(Pos.CENTER);
			
		// TextField password w/ default attributes 	
			TextField newpass = makeTF("Enter a new password:");
			newpass.setAlignment(Pos.CENTER);
		
		// Button that when pressed will take in the input from the TextFields and update the vault.	
			Button update = makeButton();
			update.setText("Update");
		
			// event handler that when update is pressed will update the vault and clear the textfields for new entry	
			update.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e) {
					String key = user.getText();
					String ogPass = pass.getText();
					String setPass = newpass.getText();
					pv.updatePassword(key, ogPass, setPass);
					user.clear();pass.clear();newpass.clear();	// clears the fields for next input
					}
				});
			
		// button that will take the user back to home screen	
			Button home = makeButton();
			home.setText("Return to the Vault");
			home.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(main);}});
			
			root.getChildren().add(logo);
			root.getChildren().add(user);
			root.getChildren().add(pass);
			root.getChildren().add(newpass);
			root.getChildren().add(update);
			root.getChildren().add(home);
			
			return root;
		}
		
// method for deleting entries from the vault		
		public FlowPane deleteVault() {
			
	// Logo
			VBox logo = makeLogo();
		
	// root GridPane to hold all associated objects
			FlowPane root = makeFP();
			
	// TextField user w/ default attributes 	
			TextField user = makeTF("Enter the username: ");
			user.setAlignment(Pos.TOP_CENTER);
			
	// Button that when pressed will take the key input from the TextField and delete the entry	
			Button delete = makeButton();
			delete.setText("Delete");
			
	// event handler when delete is pressed the vault will delete the specified field and clear the textfield for next entry		
			delete.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					String key = user.getText();
					pv.deleteInfo(key);
					user.clear(); 	// clears field for next input
					}
				});
			
	// button that will take the user back to home screen	
			Button home = makeButton();
			home.setText("Return to the Vault");
			home.setOnAction(new EventHandler<ActionEvent>() {@Override public void handle(ActionEvent e) {window.setScene(main);}});
			
			root.getChildren().add(logo);
			root.getChildren().add(user);
			root.getChildren().add(delete);
			root.getChildren().add(home);
			return root;
		}
		
// method that when chosen from home screen will print vault contents to a .txt file		
		public FlowPane printVaultToFile() {
			
		// Logo
			VBox logo = makeLogo();
		// root GridPane to hold all associated objects	
			
			FlowPane root = makeFP();
			root.setHgap(8);
			root.setVgap(8);
			
		// TextField displaying that the file has been printed	
			TextField filename = makeTF("Enter file path/filename.txt");
			filename.setAlignment(Pos.TOP_CENTER);
			
		// Button that when pressed will save the vault to a file from the TextField
			Button save = makeButton();
			save.setText("Save");
			
		// event handler that when save is pressed the current state of the vault will be saved to a .txt file	
			EventHandler<ActionEvent> saveToFile = new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
				// core event making the file.	
					String file = filename.getText();
					pv.vaultToFileGUI(file);
					filename.clear();	// clears field for next input
				}};
			save.setOnAction(saveToFile);
			save.setOnAction(null);
			save.setOnAction(saveToFile);
			
		// button that will take the user back to home screen	
			Button home = makeButton();
			home.setText("Return to Vault");
			EventHandler<ActionEvent> returnHome = new EventHandler<ActionEvent>() {
				@Override 
				public void handle(ActionEvent event) {
					window.setScene(main);
				}
			};
			home.setOnAction(returnHome);
			home.setOnAction(null);
			home.setOnAction(returnHome);
			
			root.getChildren().add(logo);
			root.getChildren().add(filename);
			root.getChildren().add(save);
			root.getChildren().add(home);
			return root;
		}
		
// method for making the FlowPane views for the Vault
		public FlowPane makeFP() {
		// FlowPane root			
			FlowPane root = new FlowPane();
			
		// setting up the orientation of root		
			root.setAlignment(Pos.TOP_CENTER);
			root.setOrientation(Orientation.VERTICAL);
			root.setPadding(new Insets(15,16,17,18));
			root.setHgap(5);
			root.setVgap(5);
			root.setStyle("-fx-background-color: #1e90ff; -fx-border-color: #663399; -fx-border-width: 5px");
			
			return root;
		}
		
// method for making the GridPane views for the Vault
		public GridPane makeGP() {
			GridPane root = new GridPane();
			
		// orientation of GridPane	
			root.setAlignment(Pos.CENTER);
			root.setPadding(new Insets(10,11,12,13));
			root.setHgap(5);
			root.setVgap(5);
			root.setStyle("-fx-background-color: #1e90ff; -fx-border-color: #663399; -fx-border-width: 10px");
			
			return root;
		}
// method for making a TextArea object 
		public TextArea makeTA(String s) {
			TextArea root = new TextArea();
			
		// orientation of TextAreas
			root.setText(s);
			root.setPrefWidth(265);
			root.setPrefHeight(240);
			root.setEditable(false);
			root.setWrapText(true);
			root.setStyle("-fx-text-fill: #483d8b");
			root.setFont(Font.font("Source Serif Pro",FontWeight.BOLD,FontPosture.ITALIC,20));
			
			return root;
		}
		
// method for making TextField objects
		public TextField makeTF(String s) {
			TextField root = new TextField();
			
		// orientation of TextField	
			root.setPrefWidth(250);
			root.setPromptText(s);
			root.setEditable(true);
			root.setStyle("-fx-font: 14 arial; -fx-text-fill: #000000; -fx-base: #6495ed;"
						  + " -fx-border-color: #4b0082; -fx-border-width: 3px");
			
			return root;
		}

// method for making VBox
		public VBox makeVBox() {
			VBox rootV = new VBox(10);
			rootV.setAlignment(Pos.CENTER);
			rootV.setStyle("-fx-border-color: #001f4d; -fx-border-width: 4px");
			return rootV;
		}

// method for making buttons	
		public Button makeButton() {
			Button rootB = new Button();
		// color scheme for buttons: base = cornflower blue, text = darkslateblue, border = indigo.
			rootB.setStyle("-fx-font: 14 arial; -fx-font-weight: bold; -fx-base: #6495ed;"
						  + " -fx-text-fill: #483d8b; -fx-border-color: #4b0082; -fx-border-width: 3px");
			rootB.setMaxSize(250, 100);
			return rootB;
		}

// method to make Labels	
		public Label makeLabel() {
			Label rootL = new Label();
			rootL.setWrapText(true);
			rootL.setStyle("-fx-text-fill: #000080; -fx-font-weight: bold");
			rootL.setFont(Font.font("Tahoma",FontWeight.BOLD,FontPosture.ITALIC,20));
			return rootL;
		}
	
/// method for displaying logo on each scene
		public VBox makeLogo() {
			Image logo;
			ImageView holdLogo;
			VBox frameLogo = new VBox(15);
			frameLogo.setAlignment(Pos.TOP_CENTER);
			
			try {
				logo = new Image(new FileInputStream("src/images/welcome.png"));
				holdLogo = new ImageView(logo);
				frameLogo.getChildren().add(holdLogo);
				
			}
			catch(FileNotFoundException e) {
				System.out.println(".PNG File not found");
				e.printStackTrace();
			}
			return frameLogo;
		}
		
// start method that will run the UI in the main method	
	@Override
	public void start(Stage primaryStage ) {
// sets the window as the primaryStage
		window = primaryStage;
		
// create the label for the Home Screen
		Label welcome = makeLabel();
		welcome.setText("Welcome to Athenauem");
		
// make HBox to hold the welcome label, so it sits center in BorderPane home
		HBox welcomeHold = new HBox(10);
		welcomeHold.setAlignment(Pos.CENTER);
		welcomeHold.getChildren().add(welcome);
		
// creates the buttons for the Main Screen
		Button add = makeButton();
		add.setText("Add to the Vault");
		
		Button update = makeButton();
		update.setText("Update the Vault");
		
		Button delete = makeButton();
		delete.setText("Delete item in the Vault");
		
		Button show = makeButton();
		show.setText("Display the Vault");
		
		Button save = makeButton();
		save.setText("Save the Vault to a File");
		
// create vault methods and set to scenes
		FlowPane home = homeScreen(welcomeHold,add,update,delete,show,save);
		main = new Scene(home,350,450);

		FlowPane addToVault = addToVault();
		scene1 = new Scene(addToVault,Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
		
		FlowPane updateTheVault = updateVault();
		scene2 = new Scene(updateTheVault,Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
		
		FlowPane deleteTheVault = deleteVault();
		scene3 = new Scene(deleteTheVault,Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
		
		GridPane showTheVault = showVault();
		scene4 = new Scene(showTheVault,Control.USE_COMPUTED_SIZE,400);
		
		FlowPane vaultToFile = printVaultToFile();
		scene5 = new Scene(vaultToFile,Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE);
	
		
// set main to window.		
		window.setScene(main);
		window.setTitle("Athenauem");
// centering window		
		window.centerOnScreen();
		window.show();
// setting centering		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
		window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
	}
	
	public static void main(String[] args) {
		 launch(args);
	}
}

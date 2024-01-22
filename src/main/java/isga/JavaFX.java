package isga;

import isga.produits.Boisson;
import isga.produits.Deca;
import isga.produits.Espresso;
import isga.produits.Sumatra;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import isga.decorateur.*;


import java.util.ArrayList;
import java.util.List;

public class JavaFX extends Application {

    private ComboBox<String> boissonComboBox;
    private List<CheckBox> decorateursCheckBoxes;
    private Label resultatLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Machine à Café");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        // Initialisation des composants
        boissonComboBox = new ComboBox<>();
        boissonComboBox.getItems().addAll("Espresso", "Sumatra", "Deca");
        boissonComboBox.setValue("Espresso");

        decorateursCheckBoxes = new ArrayList<>();
        decorateursCheckBoxes.add(new CheckBox("Noisette"));
        decorateursCheckBoxes.add(new CheckBox("Caramel"));
        decorateursCheckBoxes.add(new CheckBox("Chocolat"));
        decorateursCheckBoxes.add(new CheckBox("Vanille"));

        resultatLabel = new Label();

        Button afficherButton = new Button("Afficher");
        afficherButton.setOnAction(e -> {
            String boissonChoisie = boissonComboBox.getValue();
            Boisson boisson = choisirBoisson(boissonChoisie);

            List<String> decorateursChoisis = new ArrayList<>();
            for (CheckBox checkBox : decorateursCheckBoxes) {
                if (checkBox.isSelected()) {
                    decorateursChoisis.add(checkBox.getText());
                }
            }

            for (String decorateur : decorateursChoisis) {
                boisson = choisirDecorateur(boisson, decorateur);
            }

            afficherResultat(boisson, decorateursChoisis);
        });

        // Ajout des composants à la scène
        root.getChildren().addAll(boissonComboBox);
        root.getChildren().addAll(decorateursCheckBoxes);
        root.getChildren().addAll(afficherButton, resultatLabel);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Boisson choisirBoisson(String boissonChoisie) {
        switch (boissonChoisie) {
            case "Espresso":
                return new Espresso();
            case "Sumatra":
                return new Sumatra();
            case "Deca":
                return new Deca();
            default:
                return new Espresso();
        }
    }

    private Boisson choisirDecorateur(Boisson boisson, String decorateur) {
        switch (decorateur) {
            case "Noisette":
                return new Noisette(boisson);
            case "Chocolat":
                return new Chocolat(boisson);
            case "Vanille":
                return new Vanille(boisson);
            default:
                return boisson;
        }
    }

    private void afficherResultat(Boisson boisson, List<String> decorateursChoisis) {
        StringBuilder resultat = new StringBuilder("****************************************************\n");
        resultat.append(boisson.getDescription()).append("\n");

        for (String decorateur : decorateursChoisis) {
            resultat.append(decorateur).append(" ");
        }

        resultat.append("\n").append(boisson.cout()).append("\n");

        resultatLabel.setText(resultat.toString());
    }
}

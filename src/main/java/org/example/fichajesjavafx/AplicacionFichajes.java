package org.example.fichajesjavafx;

import controlador.Controlador;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.*;
import modelos.Fichaje;
import modelos.Usuario;


import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class AplicacionFichajes extends Application {

    // Declaramos ya el escenario y las distintas escenas de la aplicación
    private Stage stage;
    private VBox vbox;
    private Scene scene;
    private VBox root;
    private Scene adminScene;


    @Override
    public void start(Stage primaryStage) throws IOException {

        Controlador controlador = new Controlador();

        stage = primaryStage;
        creaEscena1(controlador);
        stage.setTitle("I.E.S. Fernando III - Entrada Principal");
        stage.setScene(scene);
        stage.show();

    }

    // Función con la que creamos la primera escena de la aplicación
    private void creaEscena1 (Controlador controlador) {
        // Creamos primero el logo de la empresa
        ImageView vistaLogo = new ImageView(new Image(getClass().getResourceAsStream("/logo.png")));
        vistaLogo.setFitWidth(75);
        vistaLogo.setFitHeight(75);

        vistaLogo.setPreserveRatio(true);
        StackPane.setAlignment(vistaLogo,Pos.TOP_CENTER);

        // Creamos el subtitulo de la aplicación
        Label subtitulo = new Label("I.E.S. Fernando III - Entrada Principal");
        subtitulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        StackPane.setAlignment(subtitulo,Pos.BOTTOM_CENTER);

        // Metemos el logo y subtitulo en un bloque cabecera
        StackPane cabecera = new StackPane();
        cabecera.setPrefHeight(70);
        cabecera.getChildren().addAll(vistaLogo,subtitulo);
        VBox.setMargin(cabecera,new Insets(15,60,15,20));

        // Creamos el area donde el usuario introducirá su clave, creamos una donde esta sea visible y otra en la que no
        PasswordField claveOculta = new PasswordField();
        claveOculta.setPromptText("Inserte su clave");
        StackPane.setAlignment(claveOculta,Pos.CENTER);
        claveOculta.setMaxWidth(150);
        TextField claveMostrada = new TextField();
        claveMostrada.setPromptText("Inserte su clave");
        StackPane.setAlignment(claveMostrada,Pos.CENTER);
        claveMostrada.setMaxWidth(150);
        claveMostrada.setManaged(false);
        claveMostrada.setVisible(false);

        // Creamos el boton en el que el usuario hará click para fichar
        Button botonInicioSesion = new Button("Confirmar");
        StackPane.setAlignment(botonInicioSesion,Pos.CENTER_RIGHT);

        // Metemos el area de contraseña y este ultimo botón en un mismo bloque
        StackPane bloqueClaves = new StackPane();
        bloqueClaves.setAlignment(Pos.CENTER);
        bloqueClaves.getChildren().addAll(claveOculta,claveMostrada,botonInicioSesion);
        VBox.setMargin(bloqueClaves,new Insets(15,20,15,20));

        // Creamos el checkbox en el que podremos cambiar la visibilidad del area de la contraseña
        CheckBox mostrarClave = new CheckBox("Mostrar clave");
        StackPane.setAlignment(mostrarClave,Pos.CENTER_LEFT);
        VBox.setMargin(mostrarClave,new Insets(5,20,5,5));
        claveMostrada.managedProperty().bind(mostrarClave.selectedProperty());
        claveMostrada.visibleProperty().bind(mostrarClave.selectedProperty());

        claveOculta.managedProperty().bind(mostrarClave.selectedProperty().not());
        claveOculta.visibleProperty().bind(mostrarClave.selectedProperty().not());


        // Establecemos la funcionalidad del boton de chequeo
        botonInicioSesion.setOnAction(actionEvent -> {
            int clave = -1;
            try {
                if (claveOculta.isVisible())
                    clave = Integer.parseInt(claveOculta.getText());
                else clave = Integer.parseInt(claveMostrada.getText());
            } catch (NumberFormatException e) {
                claveMostrada.clear();
                claveOculta.clear();
            }
            // Comprobamos d eque usuario es la clave
            Usuario usuario = controlador.login(clave);
            if (usuario == null)
                loginFallido();
            else {
                // Comprobamos si el usuario es admin
                if (controlador.compruebaAdmin(usuario))
                    mostrarEscenaAdmin(controlador);
                else {
                    // Comprobamos si el usuario está chequeando su entrada o su salida
                    String tipoFichaje = controlador.generaTipoNuevoFichaje(usuario);
                    controlador.registraFichaje(usuario, tipoFichaje);
                    if (tipoFichaje.equals("Entrada"))
                        mensajeFichajeEntrada();
                    else mensajeFichajeSalida();
                    stage.close();
                }

            }

        });


        // Creamos el reloj de la aplicación
        Label labelReloj = new Label();
        AnimationTimer reloj = new AnimationTimer() {
            @Override
            public void handle(long l) {
                labelReloj.setText(LocalTime.now().getHour() +
                        ":" + LocalTime.now().getMinute() +
                        ":" + LocalTime.now().getSecond());
            }
        };
        reloj.start();

        // Creamos el panel numérico y añadimos sus botones con sus funcionalidades
        GridPane panelNumerico = new GridPane();
        panelNumerico.setHgap(10);
        panelNumerico.setVgap(10);
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int numeroBoton = num;
                Button boton = new Button(String.valueOf(numeroBoton));
                boton.setPrefSize(60,40);
                boton.setOnAction(actionEvent -> {
                    if (claveOculta.isVisible())
                        claveOculta.appendText(String.valueOf(numeroBoton));
                    else claveMostrada.appendText(String.valueOf(numeroBoton));
                });
                panelNumerico.add(boton,j,i);
                num++;
            }
        }

        Button botonVaciado = new Button("Vaciar");
        botonVaciado.setPrefSize(60,40);
        botonVaciado.setOnAction(actionEvent -> {
            if (claveOculta.isVisible())
                claveOculta.clear();
            else claveMostrada.clear();
        });
        Button botonCero = new Button("0");
        botonCero.setPrefSize(60,40);
        botonCero.setOnAction(actionEvent -> {
            if (claveOculta.isVisible())
                claveOculta.appendText("0");
            else claveMostrada.appendText("0");
        });
        Button botonBorrado = new Button("Borrar");
        botonBorrado.setPrefSize(60,40);
        botonBorrado.setOnAction(actionEvent -> {

            if (claveOculta.isVisible())
                claveOculta.setText(claveOculta.getText().substring(0,claveOculta.getText().length() - 1));
            else claveMostrada.setText(claveMostrada.getText().substring(0,claveMostrada.getText().length() - 1));
        });
        panelNumerico.add(botonVaciado,0,3);
        panelNumerico.add(botonCero,1,3);
        panelNumerico.add(botonBorrado,2,3);

        labelReloj.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Creamos distintos bloques para poder alinear correctamente el reloj y el panel numérico
        VBox bloquePanelNumerico = new VBox(10, panelNumerico);
        bloquePanelNumerico.setAlignment(Pos.BOTTOM_CENTER);
        bloquePanelNumerico.setPadding(new Insets(40));


        VBox relojVBox = new VBox(labelReloj);
        relojVBox.setAlignment(Pos.TOP_CENTER);
        relojVBox.setPadding(new Insets(0, 20, 0, 0));


        HBox contenedorCentro = new HBox(20, relojVBox, bloquePanelNumerico);
        contenedorCentro.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorCentro, new Insets(15));



        // Configuramos y agregamos los nodos a la escena
        vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(cabecera,bloqueClaves,mostrarClave,contenedorCentro);
        vbox.setStyle("-fx-background-color: white;");

        scene = new Scene(vbox,550,550);
        scene.setFill(Color.WHITE);

    }

    // Función por la cual establecemos la escena del escenario a que sea la del admin
    private void mostrarEscenaAdmin(Controlador controlador) {
        creaEscenaAdmin(controlador);
        adminScene.setFill(Color.WHITE);
        stage.setScene(adminScene);
        stage.setTitle("Registro de Fichajes");
        stage.show();
    }

    // Fucnión con la que creamos la escena del admin
    private void creaEscenaAdmin(Controlador controlador) {
        TableView<Fichaje> tablaFichajes = new TableView<>();
        TableColumn<Fichaje, String> nombre = new TableColumn<>("Nombre");
        nombre.setCellValueFactory(cellData ->
                javafx.beans.property.SimpleStringProperty.stringExpression(
                        new javafx.beans.property.SimpleStringProperty(
                                cellData.getValue().getUsuario().getNombre()
                        )
                )
        );

        TableColumn<Fichaje, String> clave = new TableColumn<>("Clave");
        clave.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(cellData.getValue().getUsuario().getClave())
                )
        );

        TableColumn<Fichaje, String> tipo = new TableColumn<>("Tipo");
        tipo.setCellValueFactory( cellData ->
                new SimpleStringProperty(cellData.getValue().getTipo())
        );
        TableColumn<Fichaje, String> fecha = new TableColumn<>("Fecha");
        fecha.setCellValueFactory( cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha().toString())
        );
        TableColumn<Fichaje, String> hora = new TableColumn<>("Hora");
        hora.setCellValueFactory( cellData ->
                new SimpleStringProperty(cellData.getValue().getHora().toString())
        );

        tablaFichajes.getColumns().addAll(nombre,clave,tipo,fecha,hora);
        tablaFichajes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ArrayList<Fichaje> fichajes = controlador.getFichajes();
        ObservableList listaFichajes = FXCollections.observableArrayList(fichajes);

        tablaFichajes.setItems(listaFichajes);

        root = new VBox();
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(tablaFichajes);

        adminScene = new Scene(root,500,500);

    }

    // Función que muestra el mensaje de alerta de fichaje de salida
    private void mensajeFichajeSalida() {
        Alert mensajeFichaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeFichaje.setTitle("Fichaje de salida");
        mensajeFichaje.setHeaderText("¡Fichaje Correcto!");
        mensajeFichaje.setContentText("Ha fichado correctamente su salida de la empresa");
        mensajeFichaje.showAndWait();
    }

    // Función que muestra el mensaje de alerta de fichaje de entrada
    private void mensajeFichajeEntrada() {
        Alert mensajeFichaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensajeFichaje.setTitle("Fichaje de entrada");
        mensajeFichaje.setHeaderText("¡Fichaje Correcto!");
        mensajeFichaje.setContentText("Ha fichado correctamente su entrada a la empresa");
        mensajeFichaje.showAndWait();
    }

    // Función que muestra el mensaje de alerta de que el fichaje ha sido erróneo
    private void loginFallido() {
        Alert mensajeLoginFallido = new Alert(Alert.AlertType.INFORMATION);
        mensajeLoginFallido.setTitle("Inicio de sesión Fallido");
        mensajeLoginFallido.setHeaderText("Error en inicio de sesión");
        mensajeLoginFallido.setContentText("La clave que ha introducido es incorrecta");
        mensajeLoginFallido.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
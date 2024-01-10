package org.example.flora_kutuphane;

import Connection.DB_Connection;
import Entities.Concrete.iade_kitaplar;
import Entities.Concrete.kitaplar;
import Entities.Concrete.odunc_kitaplar;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.example.flora_kutuphane.KitaplarDAO.SQLKitapAraDAO;
import org.example.flora_kutuphane.KitaplarDAO.SQLKitapListelemeDAO;
import org.example.flora_kutuphane.KitaplarDAO.SQLOduncKitaplarDAO;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;



public class HelloController implements Initializable{
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane menu_pane;

    @FXML
    private Pane odunc_kitap;

    @FXML
    private Pane iade_kitap;

    @FXML
    private Pane profil;

    @FXML
    private Pane kitapDetay;


    @FXML
    private Button btnProfil;

    @FXML
    private Button btnOdunc_Kitap;

    @FXML
    private Button btnIade_Kitap;

    @FXML
    private Button button3;

    @FXML
    private Button btnCikis;

    @FXML
    private Label label;

    @FXML
    private Label adLabel;

    @FXML
    private Label soyadLabel;

    @FXML
    private Label ogr_noLabel;


    @FXML
    private Label sinifLabel;


    @FXML
    private Label bolumLabel;


    @FXML
    private Label telLabel;

    @FXML
    private Label e_postaLabel;

    @FXML
    private Label NumaraLabel;

    @FXML
    private Label SinifLabel;

    @FXML
    private Button clickMeButton;

    @FXML
    private TableView<iade_kitaplar> tableView;

    @FXML
    private TableView<odunc_kitaplar> tableView1;

    @FXML
    private JFXTextArea kitapAramaText;

    @FXML
    private TableView<kitaplar> kitaplarListBox;

    @FXML
    private TableColumn<kitaplar,String> kitap_adiColumn;

    @FXML
    private TableColumn<kitaplar,String> yazarColumn;
    @FXML
    private TableColumn<kitaplar, String> yayinevi;

    @FXML
    private TableColumn<kitaplar, Integer> raf_no;

    @FXML
    private TableColumn<kitaplar, String> stok_durumu;

    @FXML
    private void handleClick(ActionEvent event){
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try{
            DB_Connection dbConnection = DB_Connection.ConnectAsSingleton();
            java.sql.Connection connection = DB_Connection.ConnectAsSingleton().getConnection();

            ObservableList<iade_kitaplar> iadeKitapList = FXCollections.observableArrayList();
            ObservableList<odunc_kitaplar> oduncKitapList = FXCollections.observableArrayList();

            String sql = "SELECT i.tarih, i.durumu, k.kitap_adi FROM iade_kitaplar i JOIN kitaplar k ON i.kitap_id = k.kitap_id ";
            String sql2 = "SELECT o.tarih, o.durumu, k.kitap_adi FROM odunc_kitaplar o JOIN kitaplar k ON o.kitap_id = k.kitap_id";

            try(
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String kitapAdi = resultSet.getString("kitap_adi");
                    String tarih = resultSet.getString("tarih");
                    boolean durumu = resultSet.getBoolean("durumu");

                    iadeKitapList.add(new iade_kitaplar());
                }
            }

            try(
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    ResultSet resultSet2 = statement2.executeQuery()){
                while (resultSet2.next()){
                    String kitapAdi2 = resultSet2.getString("kitap_adi");
                    String tarih2 = resultSet2.getString("tarih");
                    boolean durumu2 = resultSet2.getBoolean("durumu");

                    oduncKitapList.add(new odunc_kitaplar(kitapAdi2,tarih2,durumu2));
                }
            }


            TableColumn<iade_kitaplar, Boolean> durumCol = new TableColumn<>("Durumu");
            durumCol.setCellValueFactory(cellData -> cellData.getValue().durumuProperty());
            durumCol.setCellFactory(new Callback<TableColumn<iade_kitaplar, Boolean>, TableCell<iade_kitaplar, Boolean>>() {
                @Override
                public TableCell<iade_kitaplar, Boolean> call(TableColumn<iade_kitaplar, Boolean> param) {
                    return new TableCell<iade_kitaplar, Boolean>() {
                        @Override
                        protected void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);

                            // Boş hücre ise metni ayarla
                            if (empty) {
                                setText("");
                            } else {
                                // Durumu kontrol et ve metni ayarla
                                setText(item ? "Rafta" : "Mevcut Değil");
                            }
                        }
                    };
                }
            });


            TableColumn<odunc_kitaplar, Boolean> durum2Col = new TableColumn<>("Durumu");
            durum2Col.setCellValueFactory(cellData -> cellData.getValue().durumuProperty());
            durum2Col.setCellFactory(new Callback<TableColumn<odunc_kitaplar, Boolean>, TableCell<odunc_kitaplar, Boolean>>() {
                @Override
                public TableCell<odunc_kitaplar, Boolean> call(TableColumn<odunc_kitaplar, Boolean> param) {
                    return new TableCell<odunc_kitaplar, Boolean>() {
                        @Override
                        protected void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setText("");
                            } else {
                                setText(item ? "İade Edildi" : "İade Edilmedi");
                            }
                        }
                    };
                }
            });



            TableColumn<iade_kitaplar, String> kitapAdiCol = new TableColumn<>("Kitap Adı");
            TableColumn<iade_kitaplar, String> tarihCol = new TableColumn<>("Tarih");


            TableColumn<odunc_kitaplar, String> kitapAdi2Col = new TableColumn<>("Kitap Adi");
            TableColumn<odunc_kitaplar, String> tarih2Col = new TableColumn<>("Tarih");

            tarihCol.setCellValueFactory(cellData -> cellData.getValue().tarihProperty());
            kitapAdiCol.setCellValueFactory(cellData -> cellData.getValue().kitap_adiProperty());


            tarih2Col.setCellValueFactory(cellData -> cellData.getValue().tarihProperty());
            kitapAdi2Col.setCellValueFactory(cellData -> cellData.getValue().kitap_adiProperty());

            tableView.getColumns().addAll(kitapAdiCol,tarihCol,durumCol);
            tableView1.getColumns().addAll(kitapAdi2Col,tarih2Col, durum2Col);

            tableView.setItems(iadeKitapList);
            tableView1.setItems(oduncKitapList);

            kitap_adiColumn.setCellValueFactory(new PropertyValueFactory<>("kitap_adi"));
            yazarColumn.setCellValueFactory(new PropertyValueFactory<>("yazar"));
            yayinevi.setCellValueFactory(new PropertyValueFactory<>("yayinevi"));
            raf_no.setCellValueFactory(new PropertyValueFactory<>("raf_no"));
            stok_durumu.setCellValueFactory(new PropertyValueFactory<>("stok_durumu"));

            listAllBooks();

            kitapAramaText.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    kitaplarListBox.setItems(kitapAraDAO.kitapAra(newValue));
                }
            });



            initializeTableView();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initializeTableView() {

    }

    public void setUserInfo(String ad, String soyad, Integer ogr_no, Integer sinif, String bolum, String tel, String e_posta, boolean isOgretimUyesi){
        adLabel.setText(ad);
        soyadLabel.setText(soyad);
        bolumLabel.setText(bolum);
        telLabel.setText(tel);
        e_postaLabel.setText(e_posta);

        if(isOgretimUyesi){
            ogr_noLabel.setVisible(false);
            sinifLabel.setVisible(false);
            NumaraLabel.setVisible(false);
            SinifLabel.setVisible(false);
        }
        else{
            ogr_noLabel.setText(String.valueOf(ogr_no));
            sinifLabel.setText(String.valueOf(sinif));
            ogr_noLabel.setVisible(true);
            sinifLabel.setVisible(true);
            NumaraLabel.setVisible(true);
            SinifLabel.setVisible(true);
        }
    }

    @FXML
    private void handleBtnProfil(ActionEvent event) {
        // Diğer panelleri gizle
        menu_pane.setVisible(true);
        odunc_kitap.setVisible(false);
        iade_kitap.setVisible(false);
        kitapDetay.setVisible(false);

        // Profil panelini görünür yap
        profil.setVisible(true);
    }

    @FXML
    private void handleBtnOdunc_Kitap(ActionEvent event) {
        // Diğer panelleri gizle
        menu_pane.setVisible(true);
        odunc_kitap.setVisible(true);
        iade_kitap.setVisible(false);
        profil.setVisible(false);
        kitapDetay.setVisible(false);
    }

    @FXML
    private void handleBtnIade_Kitap(ActionEvent event) {
        // Diğer panelleri gizle
        menu_pane.setVisible(true);
        odunc_kitap.setVisible(false);
        iade_kitap.setVisible(true);
        profil.setVisible(false);
        kitapDetay.setVisible(false);
    }

    @FXML
    private void handleButton3(ActionEvent event) {
        // Diğer panelleri gizle
        menu_pane.setVisible(true);
        odunc_kitap.setVisible(false);
        iade_kitap.setVisible(false);
        profil.setVisible(false);
        kitapDetay.setVisible(true);
    }

    @FXML
    private void handleBtnCikis(ActionEvent event){
        System.exit(0);
    }


    private final SQLKitapAraDAO kitapAraDAO = new SQLKitapAraDAO();
    SQLKitapListelemeDAO kitapListelemeDAO = new SQLKitapListelemeDAO();
    SQLOduncKitaplarDAO oduncKitaplarDAO = new SQLOduncKitaplarDAO();

    private void listAllBooks() {
        List<kitaplar> kitaplar = kitapListelemeDAO.kitapListele();
        ObservableList<kitaplar> kitapObservableList = FXCollections.observableArrayList(kitaplar);
        kitaplarListBox.setItems(kitapObservableList);

    }

    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showWarningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }





}



package com.example.fxtabbedpages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class fxTabbedPagesController {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    static final String driverName = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASS = "adminadmin";
    static String QUERY = "";

    static Connection conn = null;

    static ResultSet rs;
    static boolean currentlyEditing = false;
    static boolean currentlyInserting = false;

    //TABLE VIEW AND DATA
    private static ObservableList<ObservableList<String>> data;

    // Actor tab fields
    @FXML private TextField actorIdField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField actorLastUpdateField;

    // Film tab fields
    @FXML private TextField filmIdField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField releaseYearField;
    @FXML private TextField languageIdField;
    @FXML private TextField rentalDurationField;
    @FXML private TextField rentalRateField;
    @FXML private TextField lengthField;
    @FXML private TextField replacementCostField;
    @FXML private TextField ratingField;
    @FXML private TextField filmLastUpdateField;

    // City tab fields
    @FXML private TextField cityIdField;
    @FXML private TextField cityField;
    @FXML private TextField cityCountryIdField;
    @FXML private TextField cityLastUpdateField;

    // Country tab fields
    @FXML private TextField countryIdField;
    @FXML private TextField countryField;
    @FXML private TextField countryLastUpdateField;

    // Address tab fields
    @FXML private TextField addressIdField;
    @FXML private TextField addressField;
    @FXML private TextField address2Field;
    @FXML private TextField districtField;
    @FXML private TextField addressCityIdField;
    @FXML private TextField postalCodeField;
    @FXML private TextField phoneField;
    @FXML private TextField addressLastUpdateField;

    @FXML private Button btnNextActor, btnPreviousActor, btnNextFilm, btnPreviousFilm, btnNextCity, btnPreviousCity, btnNextCountry, btnPreviousCountry, btnNextAddress, btnPreviousAddress;

    // You can add initialization or event-handling methods here if needed

    public void initialize() {
        data = FXCollections.observableArrayList();
        // Open a connection
        try {
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            setEditable(false);
            updateFieldsInView();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setEditable(boolean editable) {
        actorIdField.setEditable(editable);
        firstNameField.setEditable(editable);
        lastNameField.setEditable(editable);
        actorLastUpdateField.setEditable(editable);

        filmIdField.setEditable(editable);
        titleField.setEditable(editable);
        descriptionField.setEditable(editable);
        releaseYearField.setEditable(editable);
        languageIdField.setEditable(editable);
        rentalDurationField.setEditable(editable);
        rentalRateField.setEditable(editable);
        lengthField.setEditable(editable);
        replacementCostField.setEditable(editable);
        ratingField.setEditable(editable);
        filmLastUpdateField.setEditable(editable);

        cityIdField.setEditable(editable);
        cityField.setEditable(editable);
        cityCountryIdField.setEditable(editable);
        cityLastUpdateField.setEditable(editable);

        countryIdField.setEditable(editable);
        countryField.setEditable(editable);
        countryLastUpdateField.setEditable(editable);

        addressIdField.setEditable(editable);
        addressField.setEditable(editable);
        address2Field.setEditable(editable);
        districtField.setEditable(editable);
        addressCityIdField.setEditable(editable);
        postalCodeField.setEditable(editable);
        phoneField.setEditable(editable);
        addressLastUpdateField.setEditable(editable);
    }

    protected void updateFieldsInView() {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            QUERY = "SELECT * FROM actor";
            rs = stmt.executeQuery(QUERY);
            rs.next();
            actorIdField.setText(rs.getString("actor_id"));
            firstNameField.setText(rs.getString("first_name"));
            lastNameField.setText(rs.getString("last_name"));
            actorLastUpdateField.setText(rs.getString("last_update"));

            QUERY = "SELECT * FROM film";
            rs = stmt.executeQuery(QUERY);
            rs.next();
            filmIdField.setText(rs.getString("film_id"));
            titleField.setText(rs.getString("title"));
            descriptionField.setText(rs.getString("description"));
            releaseYearField.setText(rs.getString("release_year"));
            languageIdField.setText(rs.getString("language_id"));
            rentalDurationField.setText(rs.getString("rental_duration"));
            rentalRateField.setText(rs.getString("rental_rate"));
            lengthField.setText(rs.getString("length"));
            replacementCostField.setText(rs.getString("replacement_cost"));
            ratingField.setText(rs.getString("rating"));
            filmLastUpdateField.setText(rs.getString("last_update"));

            QUERY = "SELECT * FROM city";
            rs = stmt.executeQuery(QUERY);
            rs.next();
            cityIdField.setText(rs.getString("city_id"));
            cityField.setText(rs.getString("city"));
            cityCountryIdField.setText(rs.getString("country_id"));
            cityLastUpdateField.setText(rs.getString("last_update"));

            QUERY = "SELECT * FROM country";
            rs = stmt.executeQuery(QUERY);
            rs.next();
            countryIdField.setText(rs.getString("country_id"));
            countryField.setText(rs.getString("country"));
            countryLastUpdateField.setText(rs.getString("last_update"));

            QUERY = "SELECT * FROM address";
            rs = stmt.executeQuery(QUERY);
            rs.next();
            addressIdField.setText(rs.getString("address_id"));
            addressField.setText(rs.getString("address"));
            address2Field.setText(rs.getString("address2"));
            districtField.setText(rs.getString("district"));
            addressCityIdField.setText(rs.getString("city_id"));
            postalCodeField.setText(rs.getString("postal_code"));
            phoneField.setText(rs.getString("phone"));
            addressLastUpdateField.setText(rs.getString("last_update"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void next(ActionEvent event) {
        try {
            if (!rs.next()) {rs.first();}
        }
        catch (Exception e) {
            try{
                rs.first();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally {
            updateFieldsInView();
        }
    }

    @FXML protected void previous(ActionEvent event) {
        try {
            if (!rs.previous()) {rs.last();}
        }
        catch (Exception e) {
            try{
                rs.last();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally {
            updateFieldsInView();
        }
    }


}

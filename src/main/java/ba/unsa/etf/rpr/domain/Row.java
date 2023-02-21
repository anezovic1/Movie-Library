package ba.unsa.etf.rpr.domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * Instead of directly adding the hashmap to the table, it is easier and more convenient to create a class.
 * This class can provide that.
 *
 * @author Anida Nezovic
 */
public class Row {
    private final SimpleStringProperty header = new SimpleStringProperty("");
    private final SimpleStringProperty value = new SimpleStringProperty("");

    /**
     * Class constructor.
     */
    public Row() {
        this("","");
    }

    /**
     * Class constructor specifying header and value.
     */
    public Row(String header, String value) {
        setHeader(header);
        setValue(value);
    }

    /**
     * Getter for header
     *
     * @return String
     */
    public String getHeader() {
        return header.get();
    }

    /**
     * Setter for the header
     *
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header.set(header);
    }

    /**
     * Setter for the value
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value.set(value);
    }

    public SimpleStringProperty headerProperty() {
        return header;
    }

    /**
     * Getter for value
     *
     * @return String
     */
    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}

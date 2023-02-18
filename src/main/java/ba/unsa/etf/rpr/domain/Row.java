package ba.unsa.etf.rpr.domain;

import javafx.beans.property.SimpleStringProperty;

public class Row {
    private final SimpleStringProperty header = new SimpleStringProperty("");
    private final SimpleStringProperty value = new SimpleStringProperty("");

    public Row() {
        this("","");
    }

    public Row(String header, String value) {
        setHeader(header);
        setValue(value);
    }

    public String getHeader() {
        return header.get();
    }

    public void setHeader(String header) {
        this.header.set(header);
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public SimpleStringProperty headerProperty() {
        return header;
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}

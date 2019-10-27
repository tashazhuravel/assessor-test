package pages.errorWindow;

public enum ErrorType {

    NUMBER_SITTING_EXIST("Номер уже существует.<br>Номер заседания не может быть пустым");

    private String label;

    ErrorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

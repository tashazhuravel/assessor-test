package pages.errorWindow;

public enum ErrorType {

    NUMBER_SITTING_EMPTY_AND_EXIST("Номер уже существует.<br>Номер заседания не может быть пустым"),
    NUMBER_SITTING_EMPTY("Номер уже существует.<br>Номер заседания не может быть пустым"),
    NUMBER_SITTING_EXIST("Номер №? уже существует."),
    SITTING_PLACE_EMPTY("Место заседания не указано."),
    SITTING_PLACE_AND_NUMBER_EMPTY("Номер уже существует.<br> Номер заседания не может быть пустым.<br> Место заседания не указано."),
    SITTING_DATE_INCORRECT_OR_EMPTY("Дата проведения заседания введена некорректно.");

    private String label;

    ErrorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

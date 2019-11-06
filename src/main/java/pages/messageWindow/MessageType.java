package pages.messageWindow;

public enum MessageType {

    MAILING_HAS_BEEN_SUCCESSFULLY_RESIEVED("Рассылка успешно произведена"),
    MEETING_STATUS_AGENDA_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET("Статус заседания \"Повестка дня проходит согласование\" успешно установлен");

    private String label;

    MessageType(String label) { this.label = label; }

    public String getLabel() {
        return label;
    }
}

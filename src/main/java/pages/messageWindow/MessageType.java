package pages.messageWindow;

public enum MessageType {

    MAILING_HAS_BEEN_SUCCESSFULLY_RESIEVED("Рассылка успешно произведена."),
    MEETING_STATUS_AGENDA_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET("Статус заседания \"Повестка дня проходит согласование\" успешно установлен."),
    MEETING_STATUS_AGENDA_APPROVED_HAS_BEEN_SET("Установлен статус заседания \"Повестка дня утверждена\""),
    YOU_MUST_SPECIFY_SEARCH_CRITERIA ("Необходимо указать критерии поиска"),
    MEETING_STATUS_PROTOCOL_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET("Установлен статус заседания \"Протокол проходит согласование\""),
    MEETING_STATUS_PROTOCOL_APPROVED_HAS_BEEN_SET("Установлен статус заседания \"Протокол утвержден\"");




    private String label;

    MessageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

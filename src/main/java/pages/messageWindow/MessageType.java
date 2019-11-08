package pages.messageWindow;

public enum MessageType {

    MAILING_HAS_BEEN_SUCCESSFULLY_RESIEVED("Рассылка успешно произведена."),
    MEETING_STATUS_AGENDA_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET("Статус заседания \"Повестка дня проходит согласование\" успешно установлен."),
    MEETING_STATUS_AGENDA_APPROVED_HAS_BEEN_SET("Установлен статус заседания \"Повестка дня утверждена\"");


    //TODO создать класс "сообщение" с кнопками да/нет и перенести сообщение ниже в него
    //SET_MEETING_STATUS_AGENDA_APPROVED("Установить статус заседания \"Повестка утверждена?\"");



    private String label;

    MessageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

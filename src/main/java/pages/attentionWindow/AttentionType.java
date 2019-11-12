package pages.attentionWindow;

public enum AttentionType {


    SET_MEETING_STATUS_AGENDA_APPROVED("Внимание! Установить статус заседания \"Повестка утверждена?\""),
    OPEN_THE_MEETING("Открыть заседание?"),
    ENABLE_ANONYMOUS_VOITING_MODE("При нажатии кнопки \"Да\" будет включён режим анонимного голосования.\n" +
            "Отключение данного режима возможно только до непосредственного начала голосования.\n" +
            "Продолжить?\n"),
    ENABLE_ANONYMOUS_IN_ABSENTIA_VOITING_MODE("Внимание! При нажатии кнопки \"Да\" будет включён режим анонимного голосования в рабочем порядке\n" +
            "Отключение данного режима возможно только до непосредственного начала голосования.\n" +
            "Продолжить?\n");


    private String label;

    AttentionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

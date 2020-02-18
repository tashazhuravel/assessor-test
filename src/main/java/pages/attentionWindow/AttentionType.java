package pages.attentionWindow;

public enum AttentionType {

    SET_MEETING_STATUS_AGENDA_APPROVED("Установить статус заседания \"Повестка утверждена?\""),
    REFORMAT_AGENDA_TEXT("Переформировать текст повестки дня?"),
    OPEN_THE_MEETING("Открыть заседание?"),
    ENABLE_ANONYMOUS_VOITING_MODE("При нажатии кнопки \"Да\" будет включён режим анонимного голосования.\n" +
            "Отключение данного режима возможно только до непосредственного начала голосования.\n" +
            "Продолжить?\n"),
    ENABLE_ANONYMOUS_IN_ABSENTIA_VOITING_MODE("При нажатии кнопки \"Да\" будет включён режим анонимного голосования в рабочем порядке\n" +
            "Отключение данного режима возможно только до непосредственного начала голосования.\n" +
            "Продолжить?\n"),
    CANCEL_MEETING("Отменить заседание?\n" + "Все вопросы отменяемого заседания будут перенесены\n" +
            "в список нераспределённых вопросов."),
    SET_MEETING_STATUS_PROTOCOL_APPROVED("Не ждать согласования протокола и установить статус заседания \"Протокол утверждён?\""),
    REFORMAT_PROTOCOL_TEXT("Переформировать текст протокола?"),
    TASK_CREATION("Перейти к выдаче поручений?"),
    DELETE_QUESTION("Удалить вопрос?");

    private String label;

    AttentionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

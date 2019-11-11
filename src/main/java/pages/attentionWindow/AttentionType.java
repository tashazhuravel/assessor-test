package pages.attentionWindow;

public enum AttentionType {


    SET_MEETING_STATUS_AGENDA_APPROVED("Установить статус заседания \"Повестка утверждена?\"");


    private String label;

    AttentionType(String label){this.label = label;}
    public String getLabel() {
        return label;
    }
}

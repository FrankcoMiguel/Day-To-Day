package Controller;

public enum Icons {

    SETTING_SELECTED("../media/settings-selected.png"),
    SETTING_UNSELECTED("../media/settings-unselected.png"),
    CHECKBOX_SELECTED("../media/checkbox-selected.png"),
    CHECKBOX_UNSELECTED("../media/checkbox-unselected.png");

    private final String url;

    private Icons(String ulr){

        this.url = ulr;

    }

    private String getURL(){

        return url;

    }

}

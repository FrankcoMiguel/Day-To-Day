package Controller;

public enum Icons {

    SELECTED("../media/settings-selected"),
    UNSELECTED("../media/settings-unselected");

    private final String url;

    private Icons(String ulr){

        this.url = ulr;

    }

    private String getURL(){

        return url;

    }

}

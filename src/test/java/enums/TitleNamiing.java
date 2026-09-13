package enums;

public enum TitleNamiing {
    PRODUCTES("Products"),
    CART_COUNTER_COLOR("rgb(226, 35, 26)");

    private final String displayName;

    TitleNamiing(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

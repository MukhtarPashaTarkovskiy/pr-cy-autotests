package enums;

public enum TitleNaming {
    PRODUCTS("Products");

    private final String displayName;

    TitleNaming(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

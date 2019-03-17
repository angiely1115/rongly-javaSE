package practice.enums;

public enum FilterPurposeEnum {
    ASSET_FILTER("AssetFilter","资产筛选"),
    FIRST_BUYING("FirstBuying","首次购买"),
    CIRCULAR_BUYING("CircularBuying","循环购买"),
    REDEEM("Redeem","赎回"),
    REPURCHASE("Repurchase","回购");

    private String value;
    private String text;
    FilterPurposeEnum(String value, String text){
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static boolean contains(String test) {

        for (FilterPurposeEnum c : FilterPurposeEnum.values()) {
            if (c.getValue().equals(test)) {
                return true;
            }
        }

        return false;
    }

    public static FilterPurposeEnum fromString(String value) {
        switch (value) {
            case "AssetFilter":
                return ASSET_FILTER;
            case "FirstBuying":
                return FIRST_BUYING;
            case "CircularBuying":
                return CIRCULAR_BUYING;
            case "Redeem":
                return REDEEM;
            case "Repurchase":
                return REPURCHASE;
            default:
                return null;
        }
    }
}

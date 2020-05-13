package eu.cloudnetservice.cloudmob;

import java.util.List;

public final class MobItemLayout implements Cloneable {

    /**
     * itemIds are not supported in all versions, use {@link MobItemLayout#itemName} instead
     */


    private String itemName;
    private int subId;
    private String display;
    private List<String> lore;

    public MobItemLayout(String itemName, int subId, String display, List<String> lore) {
        this.itemName = itemName;
        this.subId = subId;
        this.display = display;
        this.lore = lore;
    }

    public int getSubId() {
        return subId;
    }


    public List<String> getLore() {
        return lore;
    }

    public String getDisplay() {
        return display;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public MobItemLayout clone() {
        return new MobItemLayout(itemName, subId, display, lore);
    }
}

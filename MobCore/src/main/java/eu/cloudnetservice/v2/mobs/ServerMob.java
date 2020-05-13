package eu.cloudnetservice.v2.mobs;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.UUID;

public class ServerMob {

    public static final Type TYPE = TypeToken.get(ServerMob.class).getType();

    protected UUID uniqueId;
    protected String display;
    protected String name;
    protected String type;
    protected String targetGroup;
    protected String itemName;
    protected boolean autoJoin;
    protected MobPosition position;
    protected String displayMessage;

    public ServerMob(UUID uniqueId,
                     String display,
                     String name,
                     String type,
                     String targetGroup,
                     String itemName,
                     boolean autoJoin,
                     MobPosition position,
                     String displayMessage) {
        this.uniqueId = uniqueId;
        this.display = display;
        this.name = name;
        this.type = type;
        this.targetGroup = targetGroup;
        this.itemName = itemName;
        this.autoJoin = autoJoin;
        this.position = position;
        this.displayMessage = displayMessage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public boolean getAutoJoin() {
        return autoJoin;
    }

    public void setAutoJoin(boolean autoJoin) {
        this.autoJoin = autoJoin;
    }

    public MobPosition getPosition() {
        return position;
    }

    public void setPosition(MobPosition position) {
        this.position = position;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

}

package fr.catcore.fdlink.api.minecraft.compat;


import fr.catcore.fdlink.api.minecraft.Message;
import fr.catcore.fdlink.api.minecraft.style.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageCompat implements Message {

    private String message;
    private Style style = Style.EMPTY;
    private TextType type;
    private String key;
    private Object[] args;
    private List<Message> sibblings = new ArrayList<>();
    private UUID authorUUID;

    public MessageCompat(String message) {
        this.message = message;
        this.type = TextType.LITERAL;
    }

    public MessageCompat(String key, String message, Object... args) {
        this.key = key;
        this.message = message;
        this.args = args;
        this.type = TextType.TRANSLATABLE;
    }

    @Override
    public MessageCompat setAuthorUUID(UUID uuid) {
        if (UUID.fromString("00000000-0000-0000-0000-000000000000").equals(uuid)) {
            this.authorUUID = null;
        } else {
            this.authorUUID = uuid;
        }
        return this;
    }

    @Override
    public boolean hasAuthorUUID() {
        return true;
    }

    @Override
    public UUID getAuthorUUID() {
        return this.authorUUID;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Style getStyle() {
        return this.style;
    }

    @Override
    public MessageCompat setStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override
    public MessageObjectType getType() {
        return MessageObjectType.TEXT;
    }

    @Override
    public TextType getTextType() {
        return this.type;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Object[] getArgs() {
        return this.args;
    }

    @Override
    public List<Message> getSibblings() {
        return this.sibblings;
    }

    @Override
    public MessageCompat addSibbling(Message message) {
        this.sibblings.add(message);
        return this;
    }
}
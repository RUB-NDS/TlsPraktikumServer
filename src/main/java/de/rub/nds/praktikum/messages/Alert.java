package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.AlertDescription;
import de.rub.nds.praktikum.constants.AlertLevel;

/**
 * This class represents a TLS alert message. An alert message consists of only
 * two bytes. The first one indicates the level of alert (e.g. fatal or warning),
 * while the second bytes gives the alert description
 *
 */
public class Alert {

    private final byte level;
    private final byte description;

    /**
     * Constructor
     *
     * @param level AlertLevel of the alert
     * @param desc Alert description of the alert
     */
    public Alert(AlertLevel level, AlertDescription desc) {
        this(level.getValue(), desc.getValue());
    }

    /**
     * Constructor
     *
     * @param level The level of the alert
     * @param description The alert description
     */
    public Alert(byte level, byte description) {
        this.level = level;
        this.description = description;
    }

    /**
     * Returns the level of this alert as a byte value
     *
     * @return the level of this alert as a byte value
     */
    public byte getLevel() {
        return level;
    }

    /**
     * Returns the description of this alert as a byte value
     *
     * @return the description of this alert as a byte value
     */
    public byte getDescription() {
        return description;
    }

    /**
     * Returns a String representation of this object
     *
     * @return
     */
    @Override
    public String toString() {
        return "Alert{" + "level=" + level + ", description=" + description + '}';
    }
}

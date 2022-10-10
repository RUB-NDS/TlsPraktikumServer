package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.messages.Serializer;

/**
 * A serializer class which transforms a supported versions into its byte
 * representation
 */
public class SupportedVersionsExtensionSerializer extends Serializer<SupportedVersionsExtension> {

    private final SupportedVersionsExtension extension;

    /**
     * Constructor
     *
     * @param extension the extension that should be serialized
     */
    public SupportedVersionsExtensionSerializer(SupportedVersionsExtension extension) {
        this.extension = extension;
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}

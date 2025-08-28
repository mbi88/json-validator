package com.mbi;

import org.everit.json.schema.ValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Creates validation exception message.
 */
final class ExceptionMessageCompositor {

    /**
     * Returns validation exception message.
     *
     * @param exception validation exception.
     * @param json      validated object.
     * @param <T>       {@link org.json.JSONObject} or {@link org.json.JSONArray}.
     * @return exception message.
     */
    public <T> String getMessage(final ValidationException exception, final T json) {
        // Add error message
        final StringBuilder builder = new StringBuilder();
        builder.append(exception.getMessage()).append('\n');

        // Add all violations
        exception.getAllMessages()
                .forEach(msg -> builder.append(msg).append('\n'));

        // Add validated object as string with indentations
        final String formattedJson = json.toString().startsWith("{")
                ? new JSONObject(json.toString()).toString(4)
                : new JSONArray(json.toString()).toString(4);
        builder.append("\nResponse: ").append(formattedJson);

        return builder.toString();
    }
}

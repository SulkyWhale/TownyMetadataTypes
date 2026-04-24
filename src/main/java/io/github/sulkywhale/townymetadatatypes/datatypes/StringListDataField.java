package io.github.sulkywhale.townymetadatatypes.datatypes;

import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringListDataField extends CustomDataField<List<String>> {

    public StringListDataField(String key, List<String> value, String label) {
        super(key, value, label);
    }

    public StringListDataField(String key, List<String> value) {
        super(key, value);
    }

    @NotNull
    public static String typeID() {
        return "townymetadatatypes_stringlistdf";
    }

    @Override
    public @NotNull String getTypeID() {
        return typeID();
    }

    @Override
    public void setValueFromString(String strValue) {

        final String[] stringStrSplit = strValue.split(",");

        final List<String> stringList = Arrays.asList(stringStrSplit);

        this.setValue(stringList);
    }

    @Override
    public String displayFormattedValue() {
        final List<String> stringList = this.getValue();
        if (stringList == null || stringList.isEmpty())
            return "<Empty>";

        return stringList.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    protected @Nullable String serializeValueToString() {
        List<String> stringList = this.getValue();

        if (stringList == null || stringList.isEmpty())
            return null;

        return stringList.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public @NotNull CustomDataField<List<String>> clone() {

        final List<String> stringList = this.getValue();
        List<String> copyList = null;

        if (stringList != null)
            copyList = new ArrayList<>(stringList);

        final String copyLabel = hasLabel() ? getLabel() : null;

        return new StringListDataField(this.getKey(), copyList, copyLabel);
    }
}

package io.github.sulkywhale.townymetadatatypes.deserializers;

import com.palmergames.bukkit.towny.object.metadata.DataFieldDeserializer;
import io.github.sulkywhale.townymetadatatypes.datatypes.StringListDataField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringListDFDeserializer implements DataFieldDeserializer<StringListDataField> {

    @Override
    public @Nullable StringListDataField deserialize(@NotNull String key, @Nullable String value) {

        List<String> stringList;

        if (value == null) {
            stringList = new ArrayList<>();
        }

        else {
            stringList = Arrays.stream(value.split(","))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return new StringListDataField(key, stringList);
    }
}

# TownyMetadataTypes
[![](https://jitpack.io/v/sulkywhale/townymetadatatypes.svg)](https://jitpack.io/#sulkywhale/townymetadatatypes)

A library for [Towny](https://github.com/TownyAdvanced/Towny) that provides more advanced data types for Towny object metadata.

## Data Types

This library currently comes with the following custom metadata types:

- `MoneyDataField` - An extension to the `DecimalDataField` within Towny, with values formatted for money.
- `ResidentBooleanMapDataField` - A map with a resident key and a boolean value.
- `ResidentDataField.java` - A resident.
- `ResidentIntegerMapDataField` - A map with a resident key and an integer value.
- `ResidentDecimalMapDataField` - A map with a resident key and a decimal value.
- `ResidentListDataField` - A list of residents.
- `StringListDataField` - A list of strings.
- `TownBlockListDataField` - A list of town blocks.

If you would like a specific data type to be added, please create an [Ideas' discussion](https://github.com/SulkyWhale/TownyMetadataTypes/discussions/new?category=ideas) for it.

## Usage

1. Add the project as a dependency to Maven in your pom.xml:
    
    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```
    ```xml
    <dependency>
        <groupId>com.github.sulkywhale</groupId>
        <artifactId>townymetadatatypes</artifactId>
        <version>2.0.0</version>
    </dependency>
    ```
    
    Shade and relocate the package to include it in your project and avoid namespace conflicts:
    
    Replace `{your package}` with the top-level package of your project.
    ```xml
   <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.6.2</version>
      <configuration>
          <relocations>
              <relocation>
                  <pattern>io.github.sulkywhale.townymetadatatypes</pattern>
                  <shadedPattern>{your package}.townymetadatatypes</shadedPattern>
              </relocation>
          </relocations>
      </configuration>
      <executions>
          <execution>
              <phase>package</phase>
              <goals>
                  <goal>shade</goal>
              </goals>
          </execution>
      </executions>
   </plugin>
    ```

2. Register the data types you wish to use in the `onEnable()` method for your plugin:
    
    ```java
     @Override
     public void onEnable() {
         TownyMetadataTypes.registerDataField(StringListDataField.class);
         TownyMetadataTypes.registerDataField(ResidentIntegerMapDataField.class);
     }
    ```

You can now use these metadata types as you would use any built-in metadata types in Towny.

### Examples

Save metadata:

```java
public void saveStringList(Town town) {
    StringListDataField sldf = new StringListDataField("myplugin_list", Arrays.asList("Tree", "Grass", "Dirt"));
    town.addMetaData(sldf);
    // Same for the rest of the Towny objects and custom metadata types
}
```

Load metadata:

```java
public void useStringList(Town town) {
   if (town.hasMeta("myplugin_list")) {
       List<String> list = town.getMetadata("myplugin_list", StringListDataField.class).getValue();
       // Use object value
   }
   // Same for the rest of the Towny objects and custom metadata types
}
```

Please note that these data types will not be available until after the server has loaded, because Towny deserializes all metadata on the first tick of the server.

If you are not familiar with how Towny metadata works, visit the [Towny wiki](https://github.com/TownyAdvanced/Towny/wiki/Configuring-Metadata-in-Towny-objects.) to learn more.

## License

TownyMetadataTypes is licensed under the GNU LGPL v3. Please see the [license](/LICENSE.md) for more information.

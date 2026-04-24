# TownyMetadataTypes
[![](https://jitpack.io/v/sulkywhale/townymetadatatypes.svg)](https://jitpack.io/#sulkywhale/townymetadatatypes)

A library for [Towny](https://github.com/TownyAdvanced/Towny) that provides more advanced data types for Towny object metadata.

## Data Types

This library currently comes with the following data types:

- `ResidentBooleanMapDataField` - A map with a resident key and a boolean value.
- `ResidentIntegerMapDataField` - A map with a resident key and an integer value.
- `ResidentListDataField` - A list of residents.
- `StringListDataField` - A list of strings.

If you would like a specific data type to be added, please create an [issue](https://github.com/SulkyWhale/TownyMetadataTypes/issues/new) for it.

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
        <version>1.0.0</version>
    </dependency>
    ```
    
    Shade and relocate the package to include it in your project and avoid namespace conflicts:
    
    Replace `{your package}` with the top-level package of your project.
    ```xml
   <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.6.1</version>
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
         TownyMetadataTypes townyMetadataTypes = TownyMetadataTypes.getInstance();
 
         townyMetadataTypes.registerDeserializer(StringListDataField.typeID());
         townyMetadataTypes.registerDeserializer(ResidentIntegerMapDataField.typeID());
     }
    ```

You can now use these metadata types as you would use any built-in metadata types in Towny.

### Examples

Save metadata:

```java
StringListDataField sldf = new StringListDataField("myplugin_list", Arrays.asList("Tree", "Grass", "Dirt"));

town.addMetaData(sldf);
// Same for the rest of the Towny objects
```

Load metadata:

```java
if (town.hasMeta("myplugin_list")) {
    CustomDataField<?> cdf = town.getMetadata("myplugin_list");
    if (cdf instanceof StringListDataField sldf) {
        List<String> list = sldf.getValue();
    }
    // Use object value
}
```

Please note that these data types will not be available until after the server has loaded, because Towny deserializes all metadata on the first tick of the server.

If you are not familiar with how Towny metadata works, visit the [Towny wiki](https://github.com/TownyAdvanced/Towny/wiki/Configuring-Metadata-in-Towny-objects.) to learn more.

## License

TownyMetadataTypes is licensed under the GNU LGPL v3. Please see the [license](/LICENSE.md) for more information.

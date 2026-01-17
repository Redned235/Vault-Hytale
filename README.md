# Vault
A port of [Vault](https://github.com/MilkBowl/Vault) from Bukkit to Hytale. Offers an economy SPI for Hytale server developers.

## Features
- Economy API for Hytale servers

In the future, this may be expanded to chat plugins if the need arises, however, it is unlikely a port of Permissions will be made due to Hytale's built-in permission provider.

## For Developers
To use Vault in your Hytale server plugin, add it as a dependency in your build system. This can be done like so:

### Gradle
```gradle
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Redned235:Vault-Hytale:api:master-SNAPSHOT")
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Redned235</groupId>
        <artifactId>Vault-Hytale</artifactId>
        <version>master-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Usage
Vault for Hytale uses Java's built-in ServiceLoader to load economy providers. To use Vault in your plugin, you can do the following:

```java
public class MyPlugin extends JavaPlugin {
    private Economy economy;

    @Override
    protected void setup() {
        ServiceLoader<Economy> loader = ServiceLoader.load(Economy.class);
        Optional<Economy> economyProvider = loader.findFirst();

        if (economyProvider.isPresent()) {
            this.economy = economyProvider.get();
            // Economy provider found, you can now use it
        } else {
            // No economy provider found
        }
    }
}
```

If you are a developer of an economy plugin, you can make your plugin compatible with Vault by implementing the `Economy` interface and registering it as a service provider. The Java documentation on service providers can be found [here](https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html).

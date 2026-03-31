package src.gamelogbackend.primary.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvLoader
{
    public String getEnvVariable(String envVariable)
    {
        Properties props = new Properties();
        String basePath = new File("").getAbsolutePath();
        try (var inputStream = Files.newInputStream(Path.of(basePath, "secrets.env"))) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props.get(envVariable).toString();
    }
}

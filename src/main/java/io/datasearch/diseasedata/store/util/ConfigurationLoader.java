package io.datasearch.diseasedata.store.util;

import io.datasearch.diseasedata.store.DiseaseDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;
import java.util.Map;

/**
 * Feature configurator.
 */
public class ConfigurationLoader {
    private static final Logger logger = LoggerFactory.getLogger(DiseaseDataStore.class);

    private static final String SCHEMA_CONFIG_DIR = "config-schema.yaml";
    private static final String INGESTION_CONFIG_DIR = "config-ingestion.yaml";
    private static final String QUERY_CONFIG_DIR = "config-query.yaml";

    public static Map<String, Object> getSchemaConfigurations() {
        return getConfigurations(SCHEMA_CONFIG_DIR);
    }

    public static Map<String, Object> getIngestConfigurations() {
        return getConfigurations(INGESTION_CONFIG_DIR);
    }

    public static Map<String, Object> getQueryConfigurations() {
        return getConfigurations(QUERY_CONFIG_DIR);
    }

    public static Map<String, Object> getConfigurations(String configFileDir) {
        Map<String, Object> configuration = null;
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = ConfigurationLoader.class
                    .getClassLoader()
                    .getResourceAsStream(configFileDir);
            configuration = yaml.load(inputStream);
        } catch (YAMLException e) {
            logger.error(e.getMessage());
        }
        return configuration;
    }
}

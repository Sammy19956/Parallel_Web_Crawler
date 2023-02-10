package com.udacity.webcrawler.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // TODO: Fill in this method.
    File file = path.toFile();
    try (Reader reader = new FileReader(file)){
      return ConfigurationLoader.read(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  public static CrawlerConfiguration read(Reader reader) {
    // This is here to get rid of the unused variable warning.
//    while(reader.)
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      CrawlerConfiguration configuration = objectMapper.readValue(reader, CrawlerConfiguration.class);
      Objects.requireNonNull(reader);
//      System.out.println("Parallelism is : " + configuration.getParallelism());
      // TODO: Fill in this method

      return new CrawlerConfiguration.Builder()
              .addStartPages(configuration.getStartPages().toString())
              .addIgnoredUrls(configuration.getIgnoredUrls().toString())
              .addIgnoredWords(configuration.getIgnoredWords().toString())
              .setParallelism(configuration.getParallelism())
              .setImplementationOverride(configuration.getImplementationOverride())
              .setMaxDepth(configuration.getMaxDepth())
              .setTimeoutSeconds((int)configuration.getTimeout().toSeconds())
              .setPopularWordCount(configuration.getPopularWordCount())
              .setProfileOutputPath(configuration.getProfileOutputPath())
              .setResultPath(configuration.getResultPath())
              .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

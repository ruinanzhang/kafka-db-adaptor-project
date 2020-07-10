package com.ruinan.kafka;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import com.github.jcustenborder.kafka.connect.utils.config.ConfigKeyBuilder;
import java.util.Map;



public class DBConnectorConfig extends AbstractConfig {
  //  This defines the topic name my connector will write to
  public static final String TOPIC_CONFIG = "topic";


  //  This defines which database user want to connect
  public static final String DB_CONFIG = "database.name";


  // This defines the username of the database user wants to connect
  public static final String AUTH_USERNAME_CONFIG = "database.username";

  // This defines the password of the database user wants to connect
  public static final String AUTH_PASSWORD_CONFIG = "database.password";


  //This defines the batch size -- number of data points to retrieve at a time with defaults set to 100
  public static final String BATCH_SIZE_CONFIG = "batch.size";

  public static final String HOST_CONFIG = "host";

  public static final String PORT_CONFIG = "port";

//  Setting up in memory event and such...

//  public static final String IN_MEMORY_EVENT_SIZE = "in_memory_event_size";
//  public static final String MEMORY_RATIO = "memory_ratio";
//  public static final String EVENT_TYPE_BLACKLIST = "event_type_blacklist";
//  public static final String TABLE_BLACKLIST = "table_blacklist";
//  public static final String EVENT_CACHE_FILE = "event_cache_file";

//  public final String mySetting;
//
//  public MySourceConnectorConfig(Map<?, ?> originals) {
//    super(config(), originals);
//    this.mySetting = this.getString(MY_SETTING_CONFIG);
//  }

  public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(DB_CONFIG, ConfigDef.Type.STRING,"Mysql",ConfigDef.Importance.HIGH,"DB name")
            .define(HOST_CONFIG, ConfigDef.Type.STRING, "localhost", ConfigDef.Importance.HIGH, "DB Master host")
            .define(PORT_CONFIG, ConfigDef.Type.INT, 3306, ConfigDef.Importance.HIGH, "DB Master port")
            .define(BATCH_SIZE_CONFIG, ConfigDef.Type.INT, 100, ConfigDef.Importance.HIGH, "Task poll batch size")
            .define(AUTH_USERNAME_CONFIG, ConfigDef.Type.STRING, "replicator", new BatchSizeValidator(),Importance.LOW, "DB Username")
            .define(AUTH_PASSWORD_CONFIG, ConfigDef.Type.STRING, "replicator", ConfigDef.Importance.HIGH, "DB Password");


  public DBConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }

  public DBConnectorConfig(Map<String, String> parsedConfig) {
    this(CONFIG_DEF, parsedConfig);
  }
//            .define(IN_MEMORY_EVENT_SIZE, ConfigDef.Type.LONG, 1024L, ConfigDef.Importance.HIGH, "In memory event size")
//            .define(MEMORY_RATIO, ConfigDef.Type.DOUBLE, 0.5, ConfigDef.Importance.HIGH, "Memory ratio limit")
//            .define(EVENT_TYPE_BLACKLIST, ConfigDef.Type.LIST, "ROTATE, FORMAT_DESCRIPTION, XID", ConfigDef.Importance.HIGH, "Event type blacklist")
//            .define(TABLE_BLACKLIST, ConfigDef.Type.LIST, "", ConfigDef.Importance.HIGH, "Table blacklist")
//            .define(EVENT_CACHE_FILE, ConfigDef.Type.STRING, "events", ConfigDef.Importance.HIGH, "Event cache file name");
            // aggregate database name & table name
//            .define(DATABASE_PATTERN, ConfigDef.Type.LIST, empty, ConfigDef.Importance.MEDIUM, "Database name pattern")
//            .define(DATABASE_NAME, ConfigDef.Type.LIST, empty, ConfigDef.Importance.MEDIUM, "Database name aggregated")
//            .define(TABLE_PATTERN, ConfigDef.Type.LIST, empty, ConfigDef.Importance.MEDIUM, "Table name pattern")
//            .define(TABLE_NAME, ConfigDef.Type.LIST, empty, ConfigDef.Importance.MEDIUM, "Table name aggregated")
//            .define(TOPIC_PREFIX, ConfigDef.Type.STRING, "", ConfigDef.Importance.HIGH, "Topic name prefix");

//  public DBConnectorConfig(ConfigDef definition, Map<?, ?> originals, Map<String, ?> configProviderProps, boolean doLog) {
//    super(definition, originals, configProviderProps, doLog);
//  }

  public String getDbConfig() {
    return this.getString(DB_CONFIG);
  }

  public String getHostConfig() {
    return this.getString(HOST_CONFIG);
  }

  public String getPortConfig() {
    return this.getString(PORT_CONFIG);
  }

  public Integer getBatchSize() {
    return this.getInt(BATCH_SIZE_CONFIG);
  }

  public String getTopic() {
    return this.getString(TOPIC_CONFIG);
  }

  public String getAuthUsername() {
    return this.getString(AUTH_USERNAME_CONFIG);
  }

  public String getAuthPassword(){
    return this.getPassword(AUTH_PASSWORD_CONFIG).value();
  }
}

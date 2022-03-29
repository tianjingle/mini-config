# mini-config

- 使用springboot开发的配置中心，通过定时任务拉取配置中心的配置，检测配置是否发生变化，当配置与项目使用的配置不一致，则覆盖项目缓存的配置，并将配置写入文件。

```sqlite
    public static String key="tian";

    public static String lele="lele";

    static {
        ConfigFactory.getConfig(key, new IChangeListener() {
            @Override
            public void dataChanged(IConfig iConfig) {
                lele=iConfig.getString("lele");
            }
        },false);
    }
```
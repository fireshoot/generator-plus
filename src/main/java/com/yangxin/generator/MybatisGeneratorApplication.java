package com.yangxin.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

@Slf4j
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        String xmlPath = String.join(File.separator, Arrays.asList("", "src", "main", "resources", "generatorConfigExample.xml"));
        try {
            String configFilePath = System.getProperty("user.dir").concat(xmlPath);
            log.info("加载配置文件==={}", configFilePath);
            File configFile = new File(configFilePath);
            log.info("配置文件是否存在：{}", configFile.exists());
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            log.info("逆向工程错误出错。错误信息: {}", e.getMessage());
        }
        for (String warning : warnings) {
            log.info("warning：" + warning);
        }

    }
}

package com.spring;

import net.jntoo.util.AppUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * application 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.spring.dao" })
public class BootApplication {
    static public ConfigurableApplicationContext content = null;
    public static void main(String[] args) {

        content = SpringApplication.run(BootApplication.class, args);
        //content.getBean(DataSource.class);
        AppUtil.bootstrap(BootApplication.class.getPackage().getName()+".util");
    }

}

package cn.lanqiao.library_final_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.lanqiao.library_final_project.mapper")
public class LibraryFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryFinalProjectApplication.class, args);
    }

}

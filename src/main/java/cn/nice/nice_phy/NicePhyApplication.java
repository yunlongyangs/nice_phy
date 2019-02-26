package cn.nice.nice_phy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.nice.nice_phy.mapper")
public class NicePhyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NicePhyApplication.class, args);
	}

}

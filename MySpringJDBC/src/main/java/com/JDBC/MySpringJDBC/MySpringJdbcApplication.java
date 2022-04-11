package com.JDBC.MySpringJDBC;

import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.ProductDao;
import com.JDBC.MySpringJDBC.Service.ShoppingMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringJdbcApplication implements CommandLineRunner
{


	private final ShoppingMain shopping;
	private final ProductDao productDao;
	private final ScannerConfig scannerConfig;

	@Autowired
	public MySpringJdbcApplication(ShoppingMain shopping, ProductDao productDao, ScannerConfig scannerConfig)
	{
		this.shopping = shopping;
		this.productDao = productDao;
		this.scannerConfig = scannerConfig;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(MySpringJdbcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception
	{
		shopping.runApplication();
	}
}

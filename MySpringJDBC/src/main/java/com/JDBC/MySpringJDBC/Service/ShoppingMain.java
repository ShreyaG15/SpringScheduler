package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.DataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Scanner;

@Component
public class ShoppingMain {

    private final Logger logger = LoggerFactory.getLogger(ShoppingMain.class);

    private final DataSourceConfig dataSourceConfig;
    private final ProductService productService;

    @Autowired
    public ShoppingMain(DataSourceConfig dataSourceConfig, ProductService productService)
    {
        this.dataSourceConfig = dataSourceConfig;
        this.productService = productService;
    }

    public void runApplication() throws IOException
    {
           String ans;
        do {


            Scanner sc = dataSourceConfig.scanner();
            System.out.println("Welcome");
            logger.info("\n welcome");
            logger.info("\n1 Add Product \n2 View Product \n3 Remove Product \n4 Export Product \n5 Import Product");

            int input = sc.nextInt();
            switch (input) {
                case 1 -> productService.addProduct();
                case 2 -> productService.viewProduct();
                case 3 -> productService.removeProduct();
                case 4 -> productService.exportProduct();
                case 5 -> productService.importProduct();
                default -> logger.error("\n enter Valid Details");
            }
            logger.info("\n Enter 'y' to continue and 'n' to exit inventory: ");
            ans = sc.next();
        } while (ans.equalsIgnoreCase("Y"));
    }
}

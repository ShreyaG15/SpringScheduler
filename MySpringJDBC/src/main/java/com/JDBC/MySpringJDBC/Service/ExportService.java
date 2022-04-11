package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Dao.ProductDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ExportService
{

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductDao productDao;
    private final Brand brand;

    @Autowired
    public ExportService(ProductDao productDao, Brand brand)
    {
        this.productDao = productDao;
        this.brand = brand;
    }

    public void exportData(String fileName)
    {
        fileName = "/Users/kepatel/Desktop/" + fileName + ".csv ";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            int offSet = 100;
            int CurrentIndex = 1;
            int length = getProductLength();

            logger.info("length is :" + length);

            String line = "productName,brandName,price,currency";
            printWriter.println(line);

            while (CurrentIndex <= length)
            {

                List<Product> productResult = productDao.getProductData(CurrentIndex, offSet);
                productResult.forEach(p -> insertData(p, line, printWriter));
                CurrentIndex = CurrentIndex + offSet;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void insertData(Product productResult, String line, PrintWriter printWriter)
    {
                line = productResult.getProduct_name()
                + "," + productResult.getPrice()
                + "," + productResult.getBrand().getBrand_name()
                + "," + productResult.getCurrency().getCurrency_name();
                printWriter.println(line);
    }

    private int getProductLength()
    {
        int brandresult = productDao.getProductLength();
        return brandresult;
    }
}
package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.DataSourceConfig;
import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.BrandDao;
import com.JDBC.MySpringJDBC.Dao.ProductDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final BrandService brandService;
    private final DataSourceConfig dataSourceConfig;
    private final ProductDao productDao;
    private final CurrencyService currencyService;
    private final ExportService exportService;
    private final ImportService importService;
    private final ScannerConfig scannerConfig;
    private final BrandDao brandDao;


    @Autowired
    public ProductService(BrandService brandService, DataSourceConfig dataSourceConfig, ProductDao productDao, CurrencyService currencyService, ExportService exportService, ImportService importService, ScannerConfig scannerConfig, BrandDao brandDao)
    {
        this.brandService = brandService;
        this.dataSourceConfig = dataSourceConfig;
        this.productDao = productDao;
        this.currencyService = currencyService;
        this.exportService = exportService;
        this.importService = importService;
        this.scannerConfig = scannerConfig;
        this.brandDao = brandDao;
    }

    public void addProduct()
    {
        logger.info("\nAdding Product");
        logger.info("\nEnter Name of Product");
        String productName = scannerConfig.scannerNext();

        Product productResult = productDao.searchProduct(productName);

        if (productResult != null)
        {
            logger.info("\nProduct already exists in the inventory");
        } else
        {
            int brand_id = brandService.brandValidation();
            logger.info("\nEnter price of new product: ");
            int price = scannerConfig.scannerNextInt();

            int currency_id = currencyService.currencyValidattion();
            int product_id = productDao.getNextProduct();

            int result = productDao.insertProduct(product_id, productName, brand_id, price, currency_id);

            if (result == 1) {
                logger.info("\n Insertion done....");
            }
        }

    }


    public void viewProduct()
    {
        logger.info("\n Enter name of the product :");
        String productName = scannerConfig.scannerNext();
        Product productresult = productDao.findProduct(productName);
        if (productresult != null)
        {
            int brand=productresult.getBrand_id();

            Brand brandresult=brandDao.getBrandData(brand) ;
            logger.info("\n Product Name : " + productresult.getProduct_name()
                    + "\n Product Price :" + productresult.getPrice()
                    + "\n Brand Name :" +brandresult.getBrand_name());
        }

    }


    public int removeProduct()
    {
        logger.info("\n Enter name of the product you want to remove :");
        String removeProductName = scannerConfig.scannerNext();
        int result = productDao.removeProduct(removeProductName);

        if (result == 1)
        {
            logger.info("\n product Remove .....");
        }
        return result;
    }

    public void exportProduct()
    {
        logger.info("\n Enter Name Of File :");
        String fileName= scannerConfig.scannerNext();
        exportService.exportData(fileName);
    }
    public void importProduct() throws IOException {
        logger.info("\n Enter File Path :");
        String filePath= scannerConfig.scannerNext();
        importService.importData(filePath);
    }
}

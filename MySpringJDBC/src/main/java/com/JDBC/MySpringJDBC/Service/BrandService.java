package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.DataSourceConfig;
import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.BrandDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final DataSourceConfig dataSourceConfig;
    private final BrandDao brandDao;
    private final ScannerConfig scannerConfig;

    @Autowired
    public BrandService(DataSourceConfig dataSourceConfig, BrandDao brandDao, ScannerConfig scannerConfig)
    {
        this.dataSourceConfig = dataSourceConfig;
        this.brandDao = brandDao;
        this.scannerConfig = scannerConfig;
    }
    public int brandValidation()
    {
        int brand_id;
      //  Scanner sc = dataSourceConfig.scanner();
        logger.info("\nEnter Name of brand for new product: ");
        String brandName = scannerConfig.scannerNext();

        Brand brandresult = brandDao.searchBrand(brandName);
        if (brandresult != null)
        {
            brand_id = brandresult.getBrand_id();
            return brand_id;
        } else
        {
            logger.info("\nThe provided brand doesn't exist in inventory.");
            int newBrand_id = insertBrand(brandName);
            return newBrand_id;
        }
    }

    int insertBrand(String brandName)
    {
        logger.info("\nEnter location for new brand: ");
      //  Scanner scanner = new Scanner(System.in);
      //  String location = scanner.next();
        String location = scannerConfig.scannerNext();
        int brand_id = brandDao.getNextBrand();

        brandDao.insertBrand(brand_id, brandName, location);
        int newBrand_id = getNewBrandId(brandName);
        return newBrand_id;

    }
    public int getNewBrandId(String brandName)
    {
        int brand_id;

        Brand brandresult = brandDao.searchBrand(brandName);
        if (brandresult != null)
        {
            brand_id = brandresult.getBrand_id();
            return brand_id;
        }
        return -1;
    }

    /*public String getBrandName(int brand_id)
    {

        Brand brandResult = brandDao.searchBrandName(brand_id);
        if (brandResult != null)
        {
            String brandName = brandResult.getBrand_name();
            System.out.println(brandName);
            return brandName;
        }
        return null;
    }
*/
  /*  public Brand getBrandDetails(int brand) {

        Brand brand1 =
      return brand1;
    }*/
}

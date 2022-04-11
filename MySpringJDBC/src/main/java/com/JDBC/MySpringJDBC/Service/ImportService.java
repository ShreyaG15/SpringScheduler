package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Dao.BrandDao;
import com.JDBC.MySpringJDBC.Dao.CurrencyDao;
import com.JDBC.MySpringJDBC.Dao.ProductDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Currency;
import com.JDBC.MySpringJDBC.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ImportService
{

    private final ProductDao productDao;
    private final BrandService brandService;
    private final CurrencyService currencyService;
    private final BrandDao brandDao;
    private final CurrencyDao currencyDao;
    private final Brand brand;
    private final Currency currency;
    private final Product product;

    Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Autowired
    public ImportService(ProductDao productDao,
                         BrandService brandService,
                         CurrencyService currencyService,
                         BrandDao brandDao,
                         CurrencyDao currencyDao,
                         Brand brand, Currency currency, Product product)
    {
        this.productDao = productDao;
        this.brandService = brandService;
        this.currencyService = currencyService;
        this.brandDao = brandDao;
        this.currencyDao = currencyDao;
        this.brand = brand;
        this.currency = currency;
        this.product = product;
    }

    public void importData(String filePath) throws IOException
    {
        Logger logger = LoggerFactory.getLogger(ImportService.class);

        //   File file=new File(filePath);

        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        InputStream inputStream = fileSystemResource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        String[] row;

        if ((line = bufferedReader.readLine()) != null)
        {
            row = line.split(",");

            if (row.length != 4)
            {
                logger.info("File is not in valid form.");
                return;
            }
            if(!isValidFile(row))
            {
                logger.info("File is not in valid form.");
                return;
            }
        }
        while ((line=bufferedReader.readLine()) != null)
        {

            row=line.split(",");
            if (isValidRow(row)){
                if (isValidCurrency(row[3].trim()))
                {

                    List<String > productData=new ArrayList<>();
                    productData.add(row[0].trim());
                    productData.add(row[1].trim());
                    productData.add(row[2].trim());
                    productData.add(row[3].trim());

                    double price= Double.parseDouble(productData.get(2));
                   insertData(productData.get(0),productData.get(1), (int) price,productData.get(3));
                }
            }
        }
        bufferedReader.close();
        }

    private void insertData(String productName, String brandName, int price, String currencyCode )
    {
    Product productResult = productDao.searchProductForImport(productName);

        if (productResult!=null )
        {
           logger.info("Already existed :"+productResult.getProduct_name());
        }
        else
        {
            List<Brand> brandNewList= brandValidattion(brandName);
            int brand_id= brandNewList.get(0).getBrand_id();
            product.setBrand_id(brand_id);

            List<Currency> currencyList= currencyValidattion(currencyCode);
            int currency_id=  currencyList.get(0).getCurrency_id();
            product.setCurrency_id(currency_id);

            List<Product> productList=new ArrayList<>();
            int product_id =productDao.getNextProduct();
            product.setProduct_name(productName);
            product.setProduct_id(product_id);
            product.setPrice(price);

            productList.add(new Product(product_id,productName,brand_id,price,currency_id));

            productDao.insertProductByBatch(productList);
        }
}

    private List<Brand> brandValidattion(String brandName)
    {
        List<Brand> brandList=new ArrayList<>();

        Brand brandresult = brandDao.searchBrand(brandName);

        if (brandresult == null)
        {
            brand.setBrand_name(brandName);
            int brand_id = brandDao.getNextBrand();
            brandList.add(new Brand(brand_id,brandName,null));
            brandDao.insertBrandForExportUsingbatch(brandList);
            return brandList;
        }
        else
        {
            int brand_id = brandresult.getBrand_id();
            brandList.add(new Brand(brand_id,brandName,null));
            return brandList;
        }
    }

    private List<Currency> currencyValidattion(String currencyCode)
    {

        List<Currency> currencyList =new ArrayList<>();

        Currency currencyResult = currencyDao.searchCurrencyCode(currencyCode);

        if (currencyResult ==null)
        {
            currency.setCurrency_code(currencyCode);
            int currency_id = currencyDao.getNextCurrency();
            currencyList.add(new Currency(currency_id,null,null,currencyCode));
            currencyDao.insertCurrencyUsingBatch(currencyList);
            return currencyList;
        }
        else
        {
            int currency_id=currencyResult.getCurrency_id();
            currencyList.add(new Currency(currency_id,null,null,currencyCode));
            return currencyList;

        }
    }
    public boolean isValidFile (String[]params)
    {
            return (params[0].trim().equals("productName")
                    || params[1].trim().equals("brandName")
                    || params[2].trim().equals("price")
                    || params[3].trim().equals("currency"));
        }
    public boolean isValidRow (String[]row){
            if (row != null)
            {
                if (row.length == 4)
                {
                    return true;
                }
            }
            return false;
        }
        public boolean isValidCurrency (String currency){
            return (currency.length() == 3);
        }
    }

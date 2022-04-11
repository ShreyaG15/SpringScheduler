package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.BrandDao;
import com.JDBC.MySpringJDBC.Dao.ProductDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductDao productDao;
    @Mock
    ScannerConfig scannerConfig;

    @Mock
    BrandService brandService;
    @Mock
    CurrencyService currencyService;
    @Mock
    Brand brand;
    @Mock
    Product product;
    @Mock
    BrandDao brandDao;


    @Test
    void addProduct() {

        String productName="Apple";
        Mockito.when(scannerConfig.scannerNext()).thenReturn(productName);
        Mockito.when(productDao.searchProduct(productName)).thenReturn(null);
        Mockito.when(brandService.brandValidation()).thenReturn(1);
        Mockito.when(scannerConfig.scannerNextInt()).thenReturn(1000);
        Mockito.when(currencyService.currencyValidattion()).thenReturn(2);
        Mockito.when(productDao.getNextProduct()).thenReturn(3);

        Mockito.when(productDao.insertProduct(3,productName,1,1000,2)).thenReturn(1);

        productService.addProduct();

        Mockito.verify(productDao,Mockito.times(1)).insertProduct(3,productName,1,1000,2);

    }
    @Test
    void viewProduct()
    {
        String productName="apple";
        int brand_id=1;
        int price=100;
        String brandName="APPLE";
       Brand brandResult=new Brand(brand_id,"APPLE","india");
        Mockito.when(scannerConfig.scannerNext()).thenReturn(productName);
        Mockito.when(brandDao.getBrandData(brand_id)).thenReturn(brandResult);

        Product productresult=new Product(1,productName,brand_id,price,2);
        Mockito.when(productDao.findProduct(productName)).thenReturn(productresult);

        productService.viewProduct();
        Assertions.assertEquals(productresult.getProduct_name(),productName);
        Assertions.assertEquals(productresult.getPrice(),price);
        Assertions.assertEquals(brandResult.getBrand_name(),brandName);
    }

    @Test
    void removeProduct() {

        String removeProduct="ipad";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(removeProduct);
        Mockito.when(productDao.removeProduct(removeProduct)).thenReturn(1);

        int result=productService.removeProduct();

        Assertions.assertEquals(1,result);


    }
}
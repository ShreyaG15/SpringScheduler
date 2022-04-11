package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Models.Product;
import com.JDBC.MySpringJDBC.PrepareStatmentSetter.ProductPreparedStatementSetter;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.ImportExtractor;
import com.JDBC.MySpringJDBC.extractor.ProductExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductDaoTest {

    @InjectMocks
    ProductDao productDao;
    @Mock
    JdbcTemplate jdbcTemplate;
    @Mock
    Product product;
    @Mock
    ProductExtractor productExtractor;
    @Mock
    ImportExtractor importExtractor;
    @Mock
    ProductPreparedStatementSetter preparedStatementSetter;

    @Test
    void searchProduct() {
        String productName="ipad";
        Mockito.when(jdbcTemplate.query(QuerySet.LOOKUP_PRODUCT,productExtractor,productName)).thenReturn(product);
        Product result=productDao.searchProduct(productName);

        Assertions.assertEquals(product,result);
    }

    @Test
    void searchProductForImport() {
        String productName="ipad";
        Mockito.when(jdbcTemplate.query(QuerySet.LOOKUP_PRODUCT,importExtractor,productName)).thenReturn(product);
        Product result=productDao.searchProductForImport(productName);
        Assertions.assertEquals(product,result);
    }

    @Test
    void getNextProduct() {
    }

    @Test
    void insertProduct() {
        int product_id=1;
        String productName="ipad";
        int brand_id=2;
        int price=100;
        int currency_id=3;

        Mockito.when(jdbcTemplate.update(QuerySet.INSERT_PRODUCT,product_id,productName,brand_id,price,currency_id)).thenReturn(1);
       int result=productDao.insertProduct(product_id,productName,brand_id,price,currency_id);
        Assertions.assertEquals(1,result);
    }

    @Test
    void findProduct() {
        String key="ipad";
        Mockito.when(jdbcTemplate.query(QuerySet.GET_PRODUCT_DETAILS,productExtractor,key)).thenReturn(product);
         Product result=productDao.findProduct(key);
         Assertions.assertEquals(result,product);
    }

    @Test
    void removeProduct() {
        String removeProductName="ipad";
        Mockito.when(jdbcTemplate.update(QuerySet.DELETE_PRODUCT,removeProductName)).thenReturn(1);
        int result=productDao.removeProduct(removeProductName);
        Assertions.assertEquals(1,result);
    }

    @Test
    void getProductLength() {

    }

    @Test
    void getProductData() {
    }

    @Test
    void insertProductByBatch() {
        int[] expected={1,1,1};
        List<Product> productList=new ArrayList<>();
        productList.add(new Product(1,"kevin",1,100,2));
        productList.add(new Product(2,"patel",11,100,22));
        productList.add(new Product(3,"kwin",111,100,222));

        Mockito.when(jdbcTemplate.batchUpdate(QuerySet.INSERT_PRODUCT, new ProductPreparedStatementSetter(productList))).thenReturn(new int[]{1,1,1});
        int[] resultt=productDao.insertProductByBatch(productList);
        org.assertj.core.api.Assertions.assertThat(resultt).isEqualTo(expected);

    }

}
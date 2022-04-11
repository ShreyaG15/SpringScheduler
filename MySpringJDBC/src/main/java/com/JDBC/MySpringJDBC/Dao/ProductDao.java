package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Mapper.ProductMapper;
import com.JDBC.MySpringJDBC.Models.Product;
import com.JDBC.MySpringJDBC.PrepareStatmentSetter.ProductPreparedStatementSetter;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.ExportExtractor;
import com.JDBC.MySpringJDBC.extractor.ImportExtractor;
import com.JDBC.MySpringJDBC.extractor.ProductExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.JDBC.MySpringJDBC.Query.QuerySet.*;

@Repository
public class ProductDao {


    private final ProductExtractor productExtractor;
    private final JdbcTemplate jdbcTemplate;
    private final QuerySet querySet;
    private final ProductMapper productMapper;
    private final ImportExtractor importExtractor;

   private final ExportExtractor exportExtractor;


    @Autowired
    public ProductDao(ProductExtractor productExtractor, JdbcTemplate jdbcTemplate, QuerySet querySet, ProductMapper productMapper, ImportExtractor importExtractor, ExportExtractor exportExtractor) {
        this.productExtractor = productExtractor;
        this.jdbcTemplate = jdbcTemplate;
        this.querySet = querySet;
        this.productMapper = productMapper;
        this.importExtractor = importExtractor;

        this.exportExtractor = exportExtractor;
    }

    public Product searchProduct(String productName) {
        Product product = jdbcTemplate.query(LOOKUP_PRODUCT, productExtractor, productName);

        return product;
    }

    public Product searchProductForImport(String productName) {
        Product product = jdbcTemplate.query(LOOKUP_PRODUCT,importExtractor, productName);
        return product;
    }


    public int getNextProduct() {
        return jdbcTemplate.queryForObject(GET_MAX_PRODUCT_ID, Integer.class) + 1;

    }

    public int insertProduct(int product_id, String productName, int brand_id, int price, int currency_id) {

        int product = jdbcTemplate.update(INSERT_PRODUCT, product_id, productName, brand_id, price, currency_id);
        return product;

    }

    public Product findProduct(String key) {
        Product product = jdbcTemplate.query(GET_PRODUCT_DETAILS, productExtractor, key);
        return product;
    }

    public int removeProduct(String removeProductName) {

        int product = jdbcTemplate.update(DELETE_PRODUCT, removeProductName);
        return product;

    }

    public int getProductLength() {

       return jdbcTemplate.queryForObject(SIZE_PRODUCT,Integer.class);
    }

    public List<Product> getProductData(int currentIndex,int offSet) {
        List<Product> products = jdbcTemplate.query(GET_PRODUCT_DATA, exportExtractor, currentIndex, currentIndex + offSet);
        return products;
    }

    public int[] insertProductByBatch(List<Product> productList) {

        return jdbcTemplate.batchUpdate(INSERT_PRODUCT,new ProductPreparedStatementSetter(productList));

    }
}

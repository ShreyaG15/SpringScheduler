package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.BrandExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandDaoTest {

    @InjectMocks
    BrandDao brandDao;

    @Mock
    Brand brand;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    BrandExtractor brandExtractor;

    @Test
    void searchBrand() {

        String brandName="apple";

        Mockito.when(jdbcTemplate.query(QuerySet.LOOKUP_BRAND,brandExtractor,brandName)).thenReturn(brand);

        Brand brandObj=brandDao.searchBrand(brandName);
        Assertions.assertEquals(brandObj,brand);
    }

    @Test
    void getNextBrand() {

    }

    @Test
    void insertBrand() {
        int brand_id=1;
        String brandName="apple";
        String location="india";

        Mockito.when(jdbcTemplate.update(QuerySet.INSERT_BRAND,brand_id,brandName,location)).thenReturn(brand_id);

        int insert=brandDao.insertBrand(brand_id,brandName,location);
        Assertions.assertEquals(1,insert);
    }

    @Test
    void searchBrandName() {
        int brand_id=1;

        Mockito.when(jdbcTemplate.query(QuerySet.LOOKBRAND_NAME_BY_ID,brandExtractor,brand_id)).thenReturn(brand);
        Brand brandSearch=brandDao.searchBrandName(brand_id);
        Assertions.assertEquals(brandSearch,brand);

    }

    @Test
    void insertBrandForExport() {
        int brand_id =1;
        String brandName="apple";

        Mockito.when(jdbcTemplate.update(QuerySet.INSERT_BRAND_FORIMPORT,brand_id,brandName)).thenReturn(brand_id);

        int insert=brandDao.insertBrandForExport(brand_id,brandName);
        Assertions.assertEquals(1,insert);
    }

    @Test
    void insertBrandForExportUsingbatch() {
    }

    @Test
    void getBrandData() {

        int brand_id=1;
        Mockito.when(jdbcTemplate.query(QuerySet.GET_BRAND_DETAILS,brandExtractor,brand_id)).thenReturn(brand);

        Brand brandresult=brandDao.getBrandData(brand_id);
        Assertions.assertEquals(brand,brandresult);

    }
}
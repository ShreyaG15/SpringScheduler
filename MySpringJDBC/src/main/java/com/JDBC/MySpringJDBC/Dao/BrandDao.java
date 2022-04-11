package com.JDBC.MySpringJDBC.Dao;

import com.JDBC.MySpringJDBC.Models.Brand;
import com.JDBC.MySpringJDBC.PrepareStatmentSetter.BrandPreparedStatementSetter;
import com.JDBC.MySpringJDBC.Query.QuerySet;
import com.JDBC.MySpringJDBC.extractor.BrandExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.JDBC.MySpringJDBC.Query.QuerySet.*;

@Repository
public class BrandDao {

    private final JdbcTemplate jdbcTemplate;
    private final QuerySet querySet;
    private final BrandExtractor brandExtractor;

    @Autowired
    public BrandDao(JdbcTemplate jdbcTemplate, QuerySet querySet, BrandExtractor brandExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.querySet = querySet;
        this.brandExtractor = brandExtractor;
    }

    public Brand searchBrand(String brandName) {
        Brand brand = jdbcTemplate.query(LOOKUP_BRAND, brandExtractor, brandName);
        return brand;

    }

    public int getNextBrand() {
        return jdbcTemplate.queryForObject(GET_MAX_BRAND_ID, Integer.class) + 1;

    }


    public int insertBrand(int brand_id, String brandName, String location) {

        int brand = jdbcTemplate.update(INSERT_BRAND, brand_id, brandName, location);
        return brand;

    }

    public Brand searchBrandName(int brand_id) {
        Brand brand = jdbcTemplate.query(LOOKBRAND_NAME_BY_ID, brandExtractor, brand_id);
        return brand;
    }

    public int insertBrandForExport(int brand_id, String brandName) {
        int brand= jdbcTemplate.update(INSERT_BRAND_FORIMPORT,brand_id,brandName);
        return brand;
    }

    public int[] insertBrandForExportUsingbatch(List<Brand> brandList) {
        return jdbcTemplate.batchUpdate(INSERT_BRAND_FORIMPORT, new BrandPreparedStatementSetter(brandList));
    }


    public Brand getBrandData(int brand) {

        Brand brandData=jdbcTemplate.query(GET_BRAND_DETAILS,brandExtractor,brand);
        return brandData;
    }
}

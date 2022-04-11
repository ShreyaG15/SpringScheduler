package com.JDBC.MySpringJDBC.Query;

import org.springframework.stereotype.Component;

@Component
public class QuerySet {

    public static final String LOOKUP_PRODUCT = "Select * from product where product_name ilike ?";

    public static final String LOOKUP_BRAND = "Select * from brand where brand_name ilike ?";

    public static final String LOOKBRAND_NAME_BY_ID = "select * from brand where brand_id = ?";

    public static final String GET_MAX_BRAND_ID = "Select max(brand_id) from brand";

    public static final String INSERT_BRAND = "Insert into brand values(?,?,?)";

    public static final String INSERT_BRAND_FORIMPORT="Insert into brand values(?,?)";

    public static final String DELETE_PRODUCT = "Delete from product where product_name ilike ?";

    public static final String LOOKUP_CURRENCY = "Select * from currency where currency_name ilike ?";

    public static final String LOOKUP_CURRENCY_CODE = "Select * from currency where currency_code ilike ?";

    public static final String GET_MAX_CURRENCY_ID = "Select max(currency_id) from currency";

    public static final String INSERT_CURRENCY = "Insert into currency values(?,?,?,?)";

    public static final String INSERT_CURRENCY_FOR_IMPORT = "Insert into currency values(?,?,?,?)";

    public static final String INSERT_CURRENCY_ID_FOR_CODE = "Insert into currency values(?,?,?,?)";


    public static final String GET_MAX_PRODUCT_ID = "Select max(product_id) from product";

    public static final String INSERT_PRODUCT = "Insert into product values(?,?,?,?,?)";

    public static final String SIZE_PRODUCT="SELECT COUNT(product_id) FROM product";

    public static final String GET_BRAND_DETAILS = " select * from brand where brand_id= ?";

    public static final String GET_PRODUCT_DETAILS = "Select p.product_id, p.product_name,  b.brand_id, p.price,  " +
            "c.currency_id, c.currency_code, c.country from product p inner join brand b " +
            "on p.brand_id = b.brand_id inner join currency c " +
            "on p.currency_id = c.currency_id where p.product_name ilike ?";

    public static final String EXPORT_DATA="select p.product_name,b.brand_name,p.price,c.currency_name from product p "+
            "inner join brand b on p.brand_id =b.brand_id "+
            "inner join currency c on p.currency_id=c.currency_id where p.product_id >=? and p.product_id<?";


    public static final String GET_PRODUCT_DATA = "select p.product_id,p.product_name,b.brand_name,p.price,c.currency_name from product p "+
            "inner join brand b on p.brand_id =b.brand_id "+
            "inner join currency c on p.currency_id=c.currency_id where p.product_id >=? and p.product_id<? ORDER BY p.product_id ASC";
}

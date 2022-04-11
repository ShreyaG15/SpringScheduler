package com.JDBC.MySpringJDBC.Service;

import com.JDBC.MySpringJDBC.Config.ScannerConfig;
import com.JDBC.MySpringJDBC.Dao.BrandDao;
import com.JDBC.MySpringJDBC.Models.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    BrandService brandService;

    @Mock
    BrandDao brandDao;

    @Mock
    ScannerConfig scannerConfig;
    @Mock
    Brand brand;

    @Test
    void brandValidattion_If_ReturnNULL() {

        String brandName="Apple";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(brandName);
        Mockito.when(brandDao.searchBrand(brandName)).thenReturn(null);

        int brandId=insertBrand_ByName_Return_BrandId();
        Assertions.assertEquals(-1,brandId);
    }
    @Test
    void bramdValidation_If_ReturnNotNull(){

        int brand_id=1;
        String brandName="Apple";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(brandName);

        Mockito.when(brandDao.searchBrand(brandName)).thenReturn(brand);
        Mockito.when(brand.getBrand_id()).thenReturn(brand_id);

        int actualresult=brandService.brandValidation();
        Assertions.assertEquals(1,actualresult);
    }
    @Test
    int insertBrand_ByName_Return_BrandId() {

        String brandName = "Apple";

        Mockito.when(scannerConfig.scannerNext()).thenReturn(brandName);

        Mockito.when(brandDao.getNextBrand()).thenReturn(1);
        Mockito.when(brandDao.searchBrand(brandName)).thenReturn(null);

        int brandId=brandService.insertBrand(brandName);
        Assertions.assertEquals(-1, brandId);

        return brandId;
    }

    @Test
    void get_NewBrandId_By_Inserting_Name_If_It__NotNull() {
        String brandName="Apple";

        Brand brand=new Brand(1,"Apple","USA");
        Mockito.when(brandDao.searchBrand(brandName)).thenReturn(brand);

        int actualBrandId=brandService.getNewBrandId(brandName);

        Assertions.assertEquals(actualBrandId,brand.getBrand_id());

       //Assertions.assertEquals(-1,brandId);

    }
    @Test
    void get_NewBrandId_By_Inserting_Name_IF_It_Is_Is_NULL() {
        String brandName="Apple";

        Mockito.when(brandDao.searchBrand(brandName)).thenReturn(null);

        int actualBrandId=brandService.getNewBrandId(brandName);

        Assertions.assertEquals(actualBrandId,-1);

        //Assertions.assertEquals(-1,brandId);

    }


    @Test
    void insertBrand_withScanner() {

        String brandName = "Apple";

//        ScannerConfig scannerConfig=Mockito.mock(ScannerConfig.class);
        String input = "India";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Mockito.when(brandDao.getNextBrand()).thenReturn(1);
        Mockito.when(brandDao.searchBrand (brandName)).thenReturn(null);

        int brandId=brandService.insertBrand(brandName);
        Assertions.assertEquals(-1, brandId);

    }
}
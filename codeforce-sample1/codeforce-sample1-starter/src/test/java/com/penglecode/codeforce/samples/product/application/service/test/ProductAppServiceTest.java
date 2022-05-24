package com.penglecode.codeforce.samples.product.application.service.test;

import com.penglecode.codeforce.common.model.Page;
import com.penglecode.codeforce.common.util.JsonUtils;
import com.penglecode.codeforce.samples.Sample1Application;
import com.penglecode.codeforce.samples.product.ProductExampleUtils;
import com.penglecode.codeforce.samples.product.application.service.ProductAppService;
import com.penglecode.codeforce.samples.product.domain.model.ProductAggregate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author pengpeng
 * @version 1.0
 */
@SpringBootTest(classes=Sample1Application.class)
public class ProductAppServiceTest {

    @Autowired
    private ProductAppService productAppService;

    @Test
    public void createProduct() {
        ProductAggregate product = ProductExampleUtils.getExampleProduct4Create(1L);
        productAppService.createProduct(product);
    }

    @Test
    public void modifyProductById() {
        ProductAggregate product = ProductExampleUtils.getExampleProduct4Modify(1L);
        productAppService.modifyProductById(product);
    }

    @Test
    public void getProductById() {
        ProductAggregate product1 = productAppService.getProductById(1L, false);
        System.out.println(JsonUtils.object2Json(product1));
        System.out.println("----------------------------------------------");
        ProductAggregate product2 = productAppService.getProductById(1L, true);
        System.out.println(JsonUtils.object2Json(product2));
    }

    @Test
    public void createRemainingProducts() {
        List<ProductAggregate> exampleProducts = ProductExampleUtils.getExampleProductList();
        for(int id = 1; id < exampleProducts.size(); id++) {
            productAppService.createProduct(exampleProducts.get(id));
        }
    }

    @Test
    public void getProductListByIds() {
        List<Long> ids = Arrays.asList(2L, 3L, 4L, 5L, 6L);
        List<ProductAggregate> productList = productAppService.getProductsByIds(ids, true);
        if(!CollectionUtils.isEmpty(productList)) {
            for(ProductAggregate product : productList) {
                System.out.println(JsonUtils.object2Json(product));
            }
        }
    }

    @Test
    public void getProductListByPage() {
        ProductAggregate condition = new ProductAggregate();
        condition.setProductType(1);
        condition.setOnlineStatus(0);
        int totalCount = productAppService.getProductTotalCount();
        int pageSize = 10;
        int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        for(int currentPage = 1; currentPage <= totalPageCount; currentPage++) {
            Page page = Page.of(currentPage, pageSize);
            List<ProductAggregate> productList = productAppService.getProductsByPage(condition, page, false);
            System.out.println("==========> page = " + page);
            if(!CollectionUtils.isEmpty(productList)) {
                for(ProductAggregate product : productList) {
                    System.out.println(JsonUtils.object2Json(product));
                }
            }
            if(Objects.equals(page.getPageIndex(), page.getTotalPageCount())) {
                break;
            }
        }

    }

    @Test
    public void forEachProduct() {
        productAppService.forEachProduct((item, index) -> {
            System.out.printf("【%s】==> %s%n", index, JsonUtils.object2Json(item));
        });
    }

    @Test
    public void removeProductById() {
        productAppService.removeProductById(1L);
    }

}

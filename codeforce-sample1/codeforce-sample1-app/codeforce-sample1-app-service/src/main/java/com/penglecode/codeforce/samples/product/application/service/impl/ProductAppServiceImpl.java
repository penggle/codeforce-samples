package com.penglecode.codeforce.samples.product.application.service.impl;

import com.penglecode.codeforce.common.model.Page;
import com.penglecode.codeforce.common.support.*;
import com.penglecode.codeforce.samples.product.application.service.ProductAppService;
import com.penglecode.codeforce.samples.product.domain.model.*;
import com.penglecode.codeforce.samples.product.domain.service.ProductBaseInfoService;
import com.penglecode.codeforce.samples.product.domain.service.ProductExtraInfoService;
import com.penglecode.codeforce.samples.product.domain.service.ProductSaleSpecService;
import com.penglecode.codeforce.samples.product.domain.service.ProductSaleStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collectors;

/**
 * 商品应用服务实现
 *
 * @author pengpeng
 * @version 1.0
 */
@Service("productAppService")
public class ProductAppServiceImpl implements ProductAppService {

    @Resource(name="productBaseInfoService")
    private ProductBaseInfoService productBaseInfoService;

    @Resource(name="productExtraInfoService")
    private ProductExtraInfoService productExtraInfoService;

    @Resource(name="productSaleSpecService")
    private ProductSaleSpecService productSaleSpecService;

    @Resource(name="productSaleStockService")
    private ProductSaleStockService productSaleStockService;

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProduct(ProductAggregate product) {
        productBaseInfoService.createProductBase(product);
        ProductExtraInfo productExtra = product.getProductExtra();
        if(productExtra != null) {
            productExtra.setProductId(product.getProductId());
            productExtraInfoService.createProductExtra(productExtra);
        }
        List<ProductSaleSpec> productSaleSpecs = product.getProductSaleSpecs();
        if(!CollectionUtils.isEmpty(productSaleSpecs)) {
            productSaleSpecs.forEach(item -> item.setProductId(product.getProductId()));
            productSaleSpecService.batchCreateProductSaleSpec(productSaleSpecs);
        }
        List<ProductSaleStock> productSaleStocks = product.getProductSaleStocks();
        if(!CollectionUtils.isEmpty(productSaleStocks)) {
            productSaleStocks.forEach(item -> item.setProductId(product.getProductId()));
            productSaleStockService.batchCreateProductSaleStock(productSaleStocks);
        }
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductById(ProductAggregate product) {
        productBaseInfoService.modifyProductBaseById(product);
        ProductExtraInfo productExtra = product.getProductExtra();
        if(productExtra != null) {
            productExtra.setProductId(product.getProductId());
            productExtraInfoService.modifyProductExtraById(product.getProductExtra());
        }
        List<ProductSaleSpec> transientProductSaleSpecs = product.getProductSaleSpecs();
        if(!CollectionUtils.isEmpty(transientProductSaleSpecs)) {
            List<ProductSaleSpec> persistedProductSaleSpecs = productSaleSpecService.getProductSaleSpecsByProductId(product.getProductId());
            DomainServiceHelper.batchMergeEntityObjects(transientProductSaleSpecs, persistedProductSaleSpecs, ProductSaleSpec::identity, productSaleSpecService::batchCreateProductSaleSpec, productSaleSpecService::batchModifyProductSaleSpecById, productSaleSpecService::removeProductSaleSpecByIds);
        }
        List<ProductSaleStock> transientProductSaleStocks = product.getProductSaleStocks();
        if(!CollectionUtils.isEmpty(transientProductSaleStocks)) {
            List<ProductSaleStock> persistedProductSaleStocks = productSaleStockService.getProductSaleStocksByProductId(product.getProductId());
            DomainServiceHelper.batchMergeEntityObjects(transientProductSaleStocks, persistedProductSaleStocks, ProductSaleStock::identity, productSaleStockService::batchCreateProductSaleStock, productSaleStockService::batchModifyProductSaleStockById, productSaleStockService::removeProductSaleStockByIds);
        }
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductById(Long id) {
        productBaseInfoService.removeProductBaseById(id);
        productExtraInfoService.removeProductExtraById(id);
        productSaleSpecService.removeProductSaleSpecsByProductId(id);
        productSaleStockService.removeProductSaleStocksByProductId(id);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductByIds(List<Long> ids) {
        for(Long id : ids) {
            removeProductById(id);
        }
    }

    @Override
    public ProductAggregate getProductById(Long id, boolean cascade) {
        ProductBaseInfo productInfo = productBaseInfoService.getProductBaseById(id);
        if(productInfo != null) {
            ProductAggregate product = BeanCopier.copy(productInfo, ProductAggregate::new);
            if(cascade) {
                product.setProductExtra(productExtraInfoService.getProductExtraById(id));
                product.setProductSaleSpecs(productSaleSpecService.getProductSaleSpecsByProductId(id));
                product.setProductSaleStocks(productSaleStockService.getProductSaleStocksByProductId(id));
            }
            return product;
        }
        return null;
    }

    @Override
    public List<ProductAggregate> getProductsByIds(List<Long> ids, boolean cascade) {
        List<ProductBaseInfo> productBases = productBaseInfoService.getProductBasesByIds(ids);
        return prepareProductList(productBases, cascade);
    }

    @Override
    public List<ProductAggregate> getProductsByPage(ProductAggregate condition, Page page, boolean cascade) {
        List<ProductBaseInfo> productBases = productBaseInfoService.getProductBasesByPage(condition, page);
        return prepareProductList(productBases, cascade);
    }

    protected List<ProductAggregate> prepareProductList(List<ProductBaseInfo> productBases, boolean cascade) {
        if(!CollectionUtils.isEmpty(productBases)) {
            List<ProductAggregate> products = BeanCopier.copy(productBases, ProductAggregate::new);
            if(cascade) {
                List<Long> productIds = productBases.stream().map(ProductBaseInfo::getProductId).collect(Collectors.toList());
                Map<Long,ProductExtraInfo> productExtras = productExtraInfoService.getProductExtrasByIds(productIds);
                Map<Long,List<ProductSaleSpec>> productSaleSpecs = productSaleSpecService.getProductSaleSpecsByProductIds(productIds);
                Map<Long,List<ProductSaleStock>> productSaleStocks = productSaleStockService.getProductSaleStocksByProductIds(productIds);
                for(ProductAggregate product : products) {
                    product.setProductExtra(productExtras.get(product.getProductId()));
                    product.setProductSaleSpecs(productSaleSpecs.get(product.getProductId()));
                    product.setProductSaleStocks(productSaleStocks.get(product.getProductId()));
                }
            }
            return products;
        }
        return Collections.emptyList();
    }

    @Override
    public int getProductTotalCount() {
        return productBaseInfoService.getProductTotalCount();
    }

    @Override
    public void forEachProduct(Consumer<ProductAggregate> consumer) {
        productBaseInfoService.forEachProductBase(productInfo -> consumer.accept(BeanCopier.copy(productInfo, ProductAggregate::new)));
    }

    @Override
    public void forEachProduct(ObjIntConsumer<ProductAggregate> consumer) {
        productBaseInfoService.forEachProductBase((productInfo, index) -> consumer.accept(BeanCopier.copy(productInfo, ProductAggregate::new), index));
    }

}
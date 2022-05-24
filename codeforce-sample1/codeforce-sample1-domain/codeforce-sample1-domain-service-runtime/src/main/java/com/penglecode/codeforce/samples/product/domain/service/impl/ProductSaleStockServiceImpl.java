package com.penglecode.codeforce.samples.product.domain.service.impl;

import com.penglecode.codeforce.common.domain.ID;
import com.penglecode.codeforce.common.support.BeanValidator;
import com.penglecode.codeforce.common.support.MapLambdaBuilder;
import com.penglecode.codeforce.common.support.MessageSupplier;
import com.penglecode.codeforce.common.support.ValidationAssert;
import com.penglecode.codeforce.common.util.DateTimeUtils;
import com.penglecode.codeforce.common.util.StringUtils;
import com.penglecode.codeforce.mybatistiny.dsl.LambdaQueryCriteria;
import com.penglecode.codeforce.mybatistiny.dsl.QueryCriteria;
import com.penglecode.codeforce.mybatistiny.support.EntityMapperHelper;
import com.penglecode.codeforce.samples.product.dal.mapper.ProductSaleStockMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductSaleStock;
import com.penglecode.codeforce.samples.product.domain.service.ProductSaleStockService;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collectors;

/**
 * 商品销售库存信息 领域服务实现
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
@Service("productSaleStockService")
public class ProductSaleStockServiceImpl implements ProductSaleStockService {

    @Resource(name="productProductSaleStockMapper")
    private ProductSaleStockMapper productSaleStockMapper;

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProductSaleStock(ProductSaleStock productStock) {
        ValidationAssert.notNull(productStock, MessageSupplier.ofRequiredParameter("productStock"));
        productStock.setCreateTime(StringUtils.defaultIfBlank(productStock.getCreateTime(), DateTimeUtils.formatNow()));
        productStock.setUpdateTime(productStock.getCreateTime());
        BeanValidator.validateBean(productStock, ProductSaleStock::getProductId, ProductSaleStock::getSpecNo, ProductSaleStock::getSpecName, ProductSaleStock::getSellPrice, ProductSaleStock::getStock, ProductSaleStock::getCreateTime, ProductSaleStock::getUpdateTime);
        productSaleStockMapper.insert(productStock);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProductSaleStocks(List<ProductSaleStock> productStocks) {
        ValidationAssert.notEmpty(productStocks, MessageSupplier.ofRequiredParameter("productStocks"));
        EntityMapperHelper.batchUpdateEntityObjects(productStocks, this::createProductSaleStock, productSaleStockMapper);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductSaleStockById(ProductSaleStock productStock) {
        ValidationAssert.notNull(productStock, MessageSupplier.ofRequiredParameter("productStock"));
        productStock.setUpdateTime(StringUtils.defaultIfBlank(productStock.getUpdateTime(), DateTimeUtils.formatNow()));
        BeanValidator.validateBean(productStock, ProductSaleStock::getProductId, ProductSaleStock::getSpecNo, ProductSaleStock::getSpecName, ProductSaleStock::getSellPrice, ProductSaleStock::getStock, ProductSaleStock::getUpdateTime);
        Map<String,Object> updateColumns = MapLambdaBuilder.of(productStock)
                .with(ProductSaleStock::getSpecName)
                .with(ProductSaleStock::getSellPrice)
                .with(ProductSaleStock::getStock)
                .with(ProductSaleStock::getUpdateTime)
                .build();
        productSaleStockMapper.updateById(productStock.identity(), updateColumns);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductSaleStocksById(List<ProductSaleStock> productStocks) {
        ValidationAssert.notEmpty(productStocks, MessageSupplier.ofRequiredParameter("productStocks"));
        EntityMapperHelper.batchUpdateEntityObjects(productStocks, this::modifyProductSaleStockById, productSaleStockMapper);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleStockById(ID id) {
        BeanValidator.validateMap(id, ProductSaleStock::getProductId, ProductSaleStock::getSpecNo);
        productSaleStockMapper.deleteById(id);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleStockByIds(List<ID> ids) {
        ValidationAssert.notEmpty(ids, MessageSupplier.ofRequiredParameter("ids"));
        productSaleStockMapper.deleteByIds(ids);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleStocksByProductId(Long productId) {
        BeanValidator.validateProperty(productId, ProductSaleStock::getProductId);
        QueryCriteria<ProductSaleStock> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleStock::new)
            .eq(ProductSaleStock::getProductId, productId);
        productSaleStockMapper.deleteByCriteria(criteria);
    }

    @Override
    public ProductSaleStock getProductSaleStockById(ID id) {
        return ObjectUtils.isEmpty(id) ? null : productSaleStockMapper.selectById(id);
    }

    @Override
    public List<ProductSaleStock> getProductSaleStocksByIds(List<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : productSaleStockMapper.selectListByIds(ids);
    }

    @Override
    public List<ProductSaleStock> getProductSaleStocksByProductId(Long productId) {
        if(!ObjectUtils.isEmpty(productId)) {
            QueryCriteria<ProductSaleStock> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleStock::new)
                .eq(ProductSaleStock::getProductId, productId);
            return productSaleStockMapper.selectListByCriteria(criteria);
        }
        return Collections.emptyList();
    }

    @Override
    public Map<Long,List<ProductSaleStock>> getProductSaleStocksByProductIds(List<Long> productIds) {
        if(!CollectionUtils.isEmpty(productIds)) {
            QueryCriteria<ProductSaleStock> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleStock::new)
                .in(ProductSaleStock::getProductId, productIds.toArray());
            List<ProductSaleStock> productStocks = productSaleStockMapper.selectListByCriteria(criteria);
            if(!CollectionUtils.isEmpty(productStocks)) {
                return productStocks.stream().collect(Collectors.groupingBy(ProductSaleStock::getProductId, Collectors.toList()));
            }
        }
        return Collections.emptyMap();
    }

    @Override
    public int getProductTotalCount() {
        return productSaleStockMapper.selectAllCount();
    }

    @Override
    public void forEachProductSaleStock(Consumer<ProductSaleStock> consumer) {
        productSaleStockMapper.selectAllList().forEach(consumer);
    }

    @Override
    public void forEachProductSaleStock(ObjIntConsumer<ProductSaleStock> consumer) {
        Cursor<ProductSaleStock> cursor = productSaleStockMapper.selectAllList();
        int index = 0;
        for (ProductSaleStock item : cursor) {
            consumer.accept(item, index++);
        }
    }

}
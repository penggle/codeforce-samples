package com.penglecode.codeforce.samples.product.domain.service.impl;

import com.penglecode.codeforce.common.domain.ID;
import com.penglecode.codeforce.common.support.BeanValidator;
import com.penglecode.codeforce.common.support.MapLambdaBuilder;
import com.penglecode.codeforce.common.support.MessageSupplier;
import com.penglecode.codeforce.common.support.ValidationAssert;
import com.penglecode.codeforce.common.util.DateTimeUtils;
import com.penglecode.codeforce.mybatistiny.dsl.LambdaQueryCriteria;
import com.penglecode.codeforce.mybatistiny.dsl.QueryCriteria;
import com.penglecode.codeforce.mybatistiny.support.EntityMapperHelper;
import com.penglecode.codeforce.samples.product.dal.mapper.ProductSaleSpecMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductSaleSpec;
import com.penglecode.codeforce.samples.product.domain.service.ProductSaleSpecService;
import org.apache.commons.lang3.StringUtils;
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
 * 商品销售规格信息 领域服务实现
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
@Service("productSaleSpecService")
public class ProductSaleSpecServiceImpl implements ProductSaleSpecService {

    @Resource(name="productProductSaleSpecMapper")
    private ProductSaleSpecMapper productSaleSpecMapper;

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProductSaleSpec(ProductSaleSpec productSaleSpec) {
        ValidationAssert.notNull(productSaleSpec, MessageSupplier.ofRequiredParameter("productSaleSpec"));
        productSaleSpec.setCreateTime(StringUtils.defaultIfBlank(productSaleSpec.getCreateTime(), DateTimeUtils.formatNow()));
        productSaleSpec.setUpdateTime(productSaleSpec.getCreateTime());
        BeanValidator.validateBean(productSaleSpec, ProductSaleSpec::getProductId, ProductSaleSpec::getSpecNo, ProductSaleSpec::getSpecName, ProductSaleSpec::getSpecIndex, ProductSaleSpec::getCreateTime, ProductSaleSpec::getUpdateTime);
        productSaleSpecMapper.insert(productSaleSpec);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void batchCreateProductSaleSpec(List<ProductSaleSpec> productSaleSpecs) {
        ValidationAssert.notEmpty(productSaleSpecs, MessageSupplier.ofRequiredParameter("productSaleSpecs"));
        EntityMapperHelper.batchUpdateEntityObjects(productSaleSpecs, this::createProductSaleSpec, productSaleSpecMapper);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductSaleSpecById(ProductSaleSpec productSaleSpec) {
        ValidationAssert.notNull(productSaleSpec, MessageSupplier.ofRequiredParameter("productSaleSpec"));
        productSaleSpec.setUpdateTime(StringUtils.defaultIfBlank(productSaleSpec.getUpdateTime(), DateTimeUtils.formatNow()));
        BeanValidator.validateBean(productSaleSpec, ProductSaleSpec::getProductId, ProductSaleSpec::getSpecNo, ProductSaleSpec::getSpecName, ProductSaleSpec::getSpecIndex, ProductSaleSpec::getUpdateTime);
        Map<String,Object> updateColumns = MapLambdaBuilder.of(productSaleSpec)
                .with(ProductSaleSpec::getSpecName)
                .with(ProductSaleSpec::getSpecIndex)
                .with(ProductSaleSpec::getRemark)
                .with(ProductSaleSpec::getUpdateTime)
                .build();
        productSaleSpecMapper.updateById(productSaleSpec.identity(), updateColumns);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void batchModifyProductSaleSpecById(List<ProductSaleSpec> productSaleSpecs) {
        ValidationAssert.notEmpty(productSaleSpecs, MessageSupplier.ofRequiredParameter("productSaleSpecs"));
        EntityMapperHelper.batchUpdateEntityObjects(productSaleSpecs, this::modifyProductSaleSpecById, productSaleSpecMapper);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleSpecById(ID id) {
        BeanValidator.validateMap(id, ProductSaleSpec::getProductId, ProductSaleSpec::getSpecNo);
        productSaleSpecMapper.deleteById(id);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleSpecByIds(List<ID> ids) {
        ValidationAssert.notEmpty(ids, MessageSupplier.ofRequiredParameter("ids"));
        productSaleSpecMapper.deleteByIds(ids);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductSaleSpecsByProductId(Long productId) {
        BeanValidator.validateProperty(productId, ProductSaleSpec::getProductId);
        QueryCriteria<ProductSaleSpec> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleSpec::new)
            .eq(ProductSaleSpec::getProductId, productId);
        productSaleSpecMapper.deleteByCriteria(criteria);
    }

    @Override
    public ProductSaleSpec getProductSaleSpecById(ID id) {
        return ObjectUtils.isEmpty(id) ? null : productSaleSpecMapper.selectById(id);
    }

    @Override
    public List<ProductSaleSpec> getProductSaleSpecsByIds(List<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : productSaleSpecMapper.selectListByIds(ids);
    }

    @Override
    public List<ProductSaleSpec> getProductSaleSpecsByProductId(Long productId) {
        if(!ObjectUtils.isEmpty(productId)) {
            QueryCriteria<ProductSaleSpec> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleSpec::new)
                .eq(ProductSaleSpec::getProductId, productId);
            return productSaleSpecMapper.selectListByCriteria(criteria);
        }
        return Collections.emptyList();
    }

    @Override
    public Map<Long,List<ProductSaleSpec>> getProductSaleSpecsByProductIds(List<Long> productIds) {
        if(!CollectionUtils.isEmpty(productIds)) {
            QueryCriteria<ProductSaleSpec> criteria = LambdaQueryCriteria.ofSupplier(ProductSaleSpec::new)
                .in(ProductSaleSpec::getProductId, productIds.toArray());
            List<ProductSaleSpec> productSaleSpecs = productSaleSpecMapper.selectListByCriteria(criteria);
            if(!CollectionUtils.isEmpty(productSaleSpecs)) {
                return productSaleSpecs.stream().collect(Collectors.groupingBy(ProductSaleSpec::getProductId, Collectors.toList()));
            }
        }
        return Collections.emptyMap();
    }

    @Override
    public int getProductTotalCount() {
        return productSaleSpecMapper.selectAllCount();
    }

    @Override
    public void forEachProductSaleSpec(Consumer<ProductSaleSpec> consumer) {
        productSaleSpecMapper.selectAllList().forEach(consumer);
    }

    @Override
    public void forEachProductSaleSpec(ObjIntConsumer<ProductSaleSpec> consumer) {
        Cursor<ProductSaleSpec> cursor = productSaleSpecMapper.selectAllList();
        int index = 0;
        for (ProductSaleSpec item : cursor) {
            consumer.accept(item, index++);
        }
    }

}
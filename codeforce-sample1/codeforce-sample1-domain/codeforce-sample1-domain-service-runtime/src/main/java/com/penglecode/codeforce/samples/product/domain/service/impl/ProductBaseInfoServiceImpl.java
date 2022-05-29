package com.penglecode.codeforce.samples.product.domain.service.impl;

import com.penglecode.codeforce.common.model.Page;
import com.penglecode.codeforce.common.support.BeanValidator;
import com.penglecode.codeforce.common.support.MapLambdaBuilder;
import com.penglecode.codeforce.common.support.MessageSupplier;
import com.penglecode.codeforce.common.support.ValidationAssert;
import com.penglecode.codeforce.common.util.CollectionUtils;
import com.penglecode.codeforce.common.util.DateTimeUtils;
import com.penglecode.codeforce.common.util.ObjectUtils;
import com.penglecode.codeforce.common.util.StringUtils;
import com.penglecode.codeforce.mybatistiny.dsl.LambdaQueryCriteria;
import com.penglecode.codeforce.mybatistiny.dsl.QueryCriteria;
import com.penglecode.codeforce.mybatistiny.support.EntityMapperHelper;
import com.penglecode.codeforce.samples.product.dal.mapper.ProductBaseInfoMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductBaseInfo;
import com.penglecode.codeforce.samples.product.domain.service.ProductBaseInfoService;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * 商品基础信息 领域服务实现
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
@Service("productBaseInfoService")
public class ProductBaseInfoServiceImpl implements ProductBaseInfoService {

    @Resource(name="productProductBaseInfoMapper")
    private ProductBaseInfoMapper productBaseInfoMapper;

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProductBase(ProductBaseInfo productBase) {
        ValidationAssert.notNull(productBase, MessageSupplier.ofRequiredParameter("productBase"));
        productBase.setCreateTime(StringUtils.defaultIfBlank(productBase.getCreateTime(), DateTimeUtils.formatNow()));
        productBase.setUpdateTime(productBase.getCreateTime());
        BeanValidator.validateBean(productBase, ProductBaseInfo::getProductName, ProductBaseInfo::getProductUrl, ProductBaseInfo::getProductTags, ProductBaseInfo::getProductType, ProductBaseInfo::getAuditStatus, ProductBaseInfo::getOnlineStatus, ProductBaseInfo::getShopId, ProductBaseInfo::getCreateTime, ProductBaseInfo::getUpdateTime);
        productBaseInfoMapper.insert(productBase);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductBaseById(ProductBaseInfo productBase) {
        ValidationAssert.notNull(productBase, MessageSupplier.ofRequiredParameter("productBase"));
        productBase.setUpdateTime(StringUtils.defaultIfBlank(productBase.getUpdateTime(), DateTimeUtils.formatNow()));
        BeanValidator.validateBean(productBase, ProductBaseInfo::getProductId, ProductBaseInfo::getProductName, ProductBaseInfo::getProductUrl, ProductBaseInfo::getProductTags, ProductBaseInfo::getProductType, ProductBaseInfo::getAuditStatus, ProductBaseInfo::getOnlineStatus, ProductBaseInfo::getUpdateTime);
        Map<String,Object> updateColumns = MapLambdaBuilder.of(productBase)
                .with(ProductBaseInfo::getProductName)
                .with(ProductBaseInfo::getProductUrl)
                .with(ProductBaseInfo::getProductTags)
                .with(ProductBaseInfo::getProductType)
                .with(ProductBaseInfo::getAuditStatus)
                .with(ProductBaseInfo::getOnlineStatus)
                .with(ProductBaseInfo::getRemark)
                .with(ProductBaseInfo::getUpdateTime)
                .build();
        productBaseInfoMapper.updateById(productBase.identity(), updateColumns);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductBaseById(Long id) {
        BeanValidator.validateProperty(id, ProductBaseInfo::getProductId);
        productBaseInfoMapper.deleteById(id);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductBaseByIds(List<Long> ids) {
        ValidationAssert.notEmpty(ids, MessageSupplier.ofRequiredParameter( "ids"));
        EntityMapperHelper.batchDeleteEntityObjects(ids, productBaseInfoMapper);
    }

    @Override
    public ProductBaseInfo getProductBaseById(Long id) {
        return ObjectUtils.isEmpty(id) ? null : productBaseInfoMapper.selectById(id);
    }

    @Override
    public List<ProductBaseInfo> getProductBasesByIds(List<Long> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : productBaseInfoMapper.selectListByIds(ids);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", propagation=REQUIRES_NEW, readOnly=true, isolation=REPEATABLE_READ)
    public List<ProductBaseInfo> getProductBasesByPage(ProductBaseInfo condition, Page page) {
        QueryCriteria<ProductBaseInfo> criteria = LambdaQueryCriteria.of(condition)
                .like(ProductBaseInfo::getProductName)
                .eq(ProductBaseInfo::getProductType)
                .eq(ProductBaseInfo::getAuditStatus)
                .eq(ProductBaseInfo::getOnlineStatus)
                .eq(ProductBaseInfo::getShopId)
                .in(ProductBaseInfo::getAuditStatus, condition.getAuditStatuses())
                .gte(ProductBaseInfo::getCreateTime, condition.getStartCreateTime())
                .lte(ProductBaseInfo::getCreateTime, condition.getEndCreateTime())
                .dynamic(true)
                .orderBy(page.getOrderBys());
        return EntityMapperHelper.selectEntityObjectListByPage(productBaseInfoMapper, criteria, page);
    }

    @Override
    public int getProductTotalCount() {
        return productBaseInfoMapper.selectAllCount();
    }

    @Override
    public void forEachProductBase(Consumer<ProductBaseInfo> consumer) {
        productBaseInfoMapper.selectAllList().forEach(consumer);
    }

    @Override
    public void forEachProductBase(ObjIntConsumer<ProductBaseInfo> consumer) {
        Cursor<ProductBaseInfo> cursor = productBaseInfoMapper.selectAllList();
        int index = 0;
        for (ProductBaseInfo item : cursor) {
            consumer.accept(item, index++);
        }
    }

}
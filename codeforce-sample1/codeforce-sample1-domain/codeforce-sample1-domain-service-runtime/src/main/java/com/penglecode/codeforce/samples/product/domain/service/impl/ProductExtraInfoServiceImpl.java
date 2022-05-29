package com.penglecode.codeforce.samples.product.domain.service.impl;

import com.penglecode.codeforce.common.support.BeanValidator;
import com.penglecode.codeforce.common.support.MapLambdaBuilder;
import com.penglecode.codeforce.common.support.MessageSupplier;
import com.penglecode.codeforce.common.support.ValidationAssert;
import com.penglecode.codeforce.common.util.DateTimeUtils;
import com.penglecode.codeforce.common.util.ObjectUtils;
import com.penglecode.codeforce.common.util.StreamUtils;
import com.penglecode.codeforce.common.util.StringUtils;
import com.penglecode.codeforce.mybatistiny.support.EntityMapperHelper;
import com.penglecode.codeforce.samples.product.dal.mapper.ProductExtraInfoMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductExtraInfo;
import com.penglecode.codeforce.samples.product.domain.service.ProductExtraInfoService;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collectors;

/**
 * 商品额外信息 领域服务实现
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
@Service("productExtraInfoService")
public class ProductExtraInfoServiceImpl implements ProductExtraInfoService {

    @Resource(name="productProductExtraInfoMapper")
    private ProductExtraInfoMapper productExtraInfoMapper;

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void createProductExtra(ProductExtraInfo productExtra) {
        ValidationAssert.notNull(productExtra, MessageSupplier.ofRequiredParameter("productExtra"));
        productExtra.setCreateTime(StringUtils.defaultIfBlank(productExtra.getCreateTime(), DateTimeUtils.formatNow()));
        productExtra.setUpdateTime(productExtra.getCreateTime());
        BeanValidator.validateBean(productExtra, ProductExtraInfo::getProductId, ProductExtraInfo::getProductDetails, ProductExtraInfo::getCreateTime, ProductExtraInfo::getUpdateTime);
        productExtraInfoMapper.insert(productExtra);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void modifyProductExtraById(ProductExtraInfo productExtra) {
        ValidationAssert.notNull(productExtra, MessageSupplier.ofRequiredParameter("productExtra"));
        productExtra.setUpdateTime(StringUtils.defaultIfBlank(productExtra.getUpdateTime(), DateTimeUtils.formatNow()));
        BeanValidator.validateBean(productExtra, ProductExtraInfo::getProductId, ProductExtraInfo::getProductDetails, ProductExtraInfo::getUpdateTime);
        Map<String,Object> updateColumns = MapLambdaBuilder.of(productExtra)
                .with(ProductExtraInfo::getProductDetails)
                .with(ProductExtraInfo::getProductSpecifications)
                .with(ProductExtraInfo::getProductServices)
                .with(ProductExtraInfo::getUpdateTime)
                .build();
        productExtraInfoMapper.updateById(productExtra.identity(), updateColumns);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductExtraById(Long id) {
        BeanValidator.validateProperty(id, ProductExtraInfo::getProductId);
        productExtraInfoMapper.deleteById(id);
    }

    @Override
    @Transactional(transactionManager="productTransactionManager", rollbackFor=Exception.class)
    public void removeProductExtraByIds(List<Long> ids) {
        ValidationAssert.notEmpty(ids, MessageSupplier.ofRequiredParameter( "ids"));
        EntityMapperHelper.batchDeleteEntityObjects(ids, productExtraInfoMapper);
    }

    @Override
    public ProductExtraInfo getProductExtraById(Long id) {
        return ObjectUtils.isEmpty(id) ? null : productExtraInfoMapper.selectById(id);
    }

    @Override
    public Map<Long,ProductExtraInfo> getProductExtrasByIds(List<Long> ids) {
        List<ProductExtraInfo> productExtras = productExtraInfoMapper.selectListByIds(ids);
        return productExtras.stream().collect(Collectors.toMap(ProductExtraInfo::getProductId, Function.identity(), StreamUtils.preferOld(), LinkedHashMap::new));
    }

    @Override
    public int getProductTotalCount() {
        return productExtraInfoMapper.selectAllCount();
    }

    @Override
    public void forEachProductExtra(Consumer<ProductExtraInfo> consumer) {
        productExtraInfoMapper.selectAllList().forEach(consumer);
    }

    @Override
    public void forEachProductExtra(ObjIntConsumer<ProductExtraInfo> consumer) {
        Cursor<ProductExtraInfo> cursor = productExtraInfoMapper.selectAllList();
        int index = 0;
        for (ProductExtraInfo item : cursor) {
            consumer.accept(item, index++);
        }
    }

}
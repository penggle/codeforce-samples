package com.penglecode.codeforce.samples.product.dal.mapper;

import com.penglecode.codeforce.mybatistiny.mapper.BaseEntityMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductSaleSpec;
import org.springframework.boot.autoconfigure.mybatis.NamedDatabase;

/**
 * 商品销售规格Mapper
 *
 * @author pengpeng
 * @version 1.0
 */
@NamedDatabase("product")
public interface ProductSaleSpecMapper extends BaseEntityMapper<ProductSaleSpec> {
}

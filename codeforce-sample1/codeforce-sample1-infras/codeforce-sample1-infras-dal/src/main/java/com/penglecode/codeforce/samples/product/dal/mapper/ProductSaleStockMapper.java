package com.penglecode.codeforce.samples.product.dal.mapper;

import com.penglecode.codeforce.mybatistiny.mapper.BaseEntityMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductSaleStock;
import org.springframework.boot.autoconfigure.mds.NamedDatabase;

/**
 * 商品销售库存Mapper
 *
 * @author pengpeng
 * @version 1.0
 */
@NamedDatabase("product")
public interface ProductSaleStockMapper extends BaseEntityMapper<ProductSaleStock> {
}

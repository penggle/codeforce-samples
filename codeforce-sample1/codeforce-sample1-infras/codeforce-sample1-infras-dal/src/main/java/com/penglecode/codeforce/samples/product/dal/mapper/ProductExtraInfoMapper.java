package com.penglecode.codeforce.samples.product.dal.mapper;

import com.penglecode.codeforce.mybatistiny.mapper.BaseEntityMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductExtraInfo;
import org.springframework.boot.autoconfigure.mds.NamedDatabase;

/**
 * 商品额外信息Mapper
 *
 * @author pengpeng
 * @version 1.0
 */
@NamedDatabase("product")
public interface ProductExtraInfoMapper extends BaseEntityMapper<ProductExtraInfo> {
}

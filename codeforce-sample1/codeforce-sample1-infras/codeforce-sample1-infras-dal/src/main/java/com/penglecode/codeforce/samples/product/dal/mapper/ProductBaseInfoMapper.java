package com.penglecode.codeforce.samples.product.dal.mapper;

import com.penglecode.codeforce.mybatistiny.mapper.BaseEntityMapper;
import com.penglecode.codeforce.samples.product.domain.model.ProductBaseInfo;
import org.springframework.boot.autoconfigure.mybatis.NamedDatabase;

/**
 * 商品基础信息Mapper
 *
 * @author pengpeng
 * @version 1.0
 */
@NamedDatabase("product")
public interface ProductBaseInfoMapper extends BaseEntityMapper<ProductBaseInfo> {
}

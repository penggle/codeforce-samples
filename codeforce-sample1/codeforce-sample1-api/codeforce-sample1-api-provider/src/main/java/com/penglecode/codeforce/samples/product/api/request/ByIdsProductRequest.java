package com.penglecode.codeforce.samples.product.api.request;

import com.penglecode.codeforce.common.model.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 根据ID批量操作商品通用请求DTO
 *
 * @author peng2.peng
 * @version 1.0.0
 */
@Schema(description="根据ID批量操作商品通用请求DTO")
public class ByIdsProductRequest extends BaseRequest {

    @Schema(description="商品ID列表")
    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

}

package com.penglecode.codeforce.samples.product.api.request;

import com.penglecode.codeforce.common.model.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 根据ID操作商品通用请求DTO
 *
 * @author peng2.peng
 * @version 1.0.0
 */
@Schema(description="根据ID操作商品通用请求DTO")
public class ByIdProductRequest extends BaseRequest {

    @Schema(description="商品ID")
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}

package com.penglecode.codeforce.samples.product.api.model;

import com.penglecode.codeforce.common.model.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 商品额外信息查询(出站)DTO
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
@Schema(description="商品额外信息查询(出站)DTO")
public class ProductExtraOutDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品ID */
    @Schema(description="商品ID")
    private Long productId;

    /** 商品详情(HTML片段) */
    @Schema(description="商品详情(HTML片段)")
    private String productDetails;

    /** 商品规则参数(HTML片段) */
    @Schema(description="商品规则参数(HTML片段)")
    private String productSpecifications;

    /** 商品服务(HTML片段) */
    @Schema(description="商品服务(HTML片段)")
    private String productServices;

    /** 创建时间 */
    @Schema(description="创建时间")
    private String createTime;

    /** 最近修改时间 */
    @Schema(description="最近修改时间")
    private String updateTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(String productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public String getProductServices() {
        return productServices;
    }

    public void setProductServices(String productServices) {
        this.productServices = productServices;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}

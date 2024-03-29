package com.penglecode.codeforce.samples.product.api.model;

import com.penglecode.codeforce.common.model.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 商品分页查询参数DTO
 *
 * @author pengpeng
 * @version 1.0
 */
@Schema(description="商品分页查询参数DTO")
public class ProductPageInDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品名称 */
    @Schema(description="商品名称")
    private String productName;

    /** 商品类型：0-虚拟商品,1-实物商品 */
    @Schema(description="商品类型(0-虚拟商品,1-实物商品)", defaultValue="1", example="1")
    private Integer productType;

    /** 商品审核状态：0-待审核,1-审核通过,2-审核不通过 */
    @Schema(description="商品审核状态(0-待审核,1-审核通过,2-审核不通过)", defaultValue="0", example="0")
    private Integer auditStatus;

    /** 上下架状态：0-已下架,1-已上架 */
    @Schema(description="上下架状态(0-已下架,1-已上架)", defaultValue="0", example="0")
    private Integer onlineStatus;

    /** 所属店铺ID */
    @Schema(description="所属店铺ID")
    private Long shopId;

    /** createTime的范围查询条件辅助字段 */
    @Schema(description="起始创建时间")
    private String startCreateTime;

    /** createTime的范围查询条件辅助字段 */
    @Schema(description="终止创建时间")
    private String endCreateTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

}
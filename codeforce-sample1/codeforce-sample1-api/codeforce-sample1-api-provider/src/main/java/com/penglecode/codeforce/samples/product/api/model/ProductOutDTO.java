package com.penglecode.codeforce.samples.product.api.model;

import com.penglecode.codeforce.common.model.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 查询商品详情响应DTO
 *
 * @author pengpeng
 * @version 1.0
 */
@Schema(description="查询商品详情响应DTO")
public class ProductOutDTO extends ProductBaseOutDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品额外信息 */
    @Schema(description="商品额外信息")
    private ProductExtraOutDTO productExtra;

    /** 商品销售规格信息列表 */
    @Schema(description="商品销售规格信息列表")
    private List<ProductSaleSpecOutDTO> productSaleSpecs;

    /** 商品销售库存信息列表 */
    @Schema(description="商品销售库存信息列表")
    private List<ProductSaleStockOutDTO> productSaleStocks;

    public ProductExtraOutDTO getProductExtra() {
        return productExtra;
    }

    public void setProductExtra(ProductExtraOutDTO productExtra) {
        this.productExtra = productExtra;
    }

    public List<ProductSaleSpecOutDTO> getProductSaleSpecs() {
        return productSaleSpecs;
    }

    public void setProductSaleSpecs(List<ProductSaleSpecOutDTO> productSaleSpecs) {
        this.productSaleSpecs = productSaleSpecs;
    }

    public List<ProductSaleStockOutDTO> getProductSaleStocks() {
        return productSaleStocks;
    }

    public void setProductSaleStocks(List<ProductSaleStockOutDTO> productSaleStocks) {
        this.productSaleStocks = productSaleStocks;
    }

}

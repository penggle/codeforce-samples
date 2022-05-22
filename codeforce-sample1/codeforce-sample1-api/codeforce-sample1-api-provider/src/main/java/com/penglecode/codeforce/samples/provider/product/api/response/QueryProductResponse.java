package com.penglecode.codeforce.samples.provider.product.api.response;

import com.penglecode.codeforce.common.model.BaseDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductBaseQueryDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductExtraQueryDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductSaleSpecQueryDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductSaleStockQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 查询商品响应DTO
 *
 * @author pengpeng
 * @version 1.0
 */
@Schema(description="查询商品响应DTO")
public class QueryProductResponse extends ProductBaseQueryDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品额外信息 */
    @Schema(description="商品额外信息")
    private ProductExtraQueryDTO productExtra;

    /** 商品销售规格信息列表 */
    @Schema(description="商品销售规格信息列表")
    private List<ProductSaleSpecQueryDTO> productSaleSpecs;

    /** 商品销售库存信息列表 */
    @Schema(description="商品销售库存信息列表")
    private List<ProductSaleStockQueryDTO> productSaleStocks;

    public ProductExtraQueryDTO getProductExtra() {
        return productExtra;
    }

    public void setProductExtra(ProductExtraQueryDTO productExtra) {
        this.productExtra = productExtra;
    }

    public List<ProductSaleSpecQueryDTO> getProductSaleSpecs() {
        return productSaleSpecs;
    }

    public void setProductSaleSpecs(List<ProductSaleSpecQueryDTO> productSaleSpecs) {
        this.productSaleSpecs = productSaleSpecs;
    }

    public List<ProductSaleStockQueryDTO> getProductSaleStocks() {
        return productSaleStocks;
    }

    public void setProductSaleStocks(List<ProductSaleStockQueryDTO> productSaleStocks) {
        this.productSaleStocks = productSaleStocks;
    }

}

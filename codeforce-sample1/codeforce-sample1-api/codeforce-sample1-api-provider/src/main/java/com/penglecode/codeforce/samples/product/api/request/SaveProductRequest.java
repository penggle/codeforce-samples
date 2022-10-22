package com.penglecode.codeforce.samples.product.api.request;

import com.penglecode.codeforce.common.model.BaseDTO;
import com.penglecode.codeforce.samples.product.api.model.ProductBaseInDTO;
import com.penglecode.codeforce.samples.product.api.model.ProductExtraInDTO;
import com.penglecode.codeforce.samples.product.api.model.ProductSaleSpecInDTO;
import com.penglecode.codeforce.samples.product.api.model.ProductSaleStockInDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 保存(创建/修改)商品请求DTO
 *
 * @author pengpeng
 * @version 1.0
 */
@Schema(description="创建商品请求DTO")
public class SaveProductRequest extends ProductBaseInDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品额外信息 */
    @Schema(description="商品额外信息")
    private ProductExtraInDTO productExtra;

    /** 商品销售规格信息列表 */
    @Schema(description="商品销售规格信息列表")
    private List<ProductSaleSpecInDTO> productSaleSpecs;

    /** 商品销售库存信息列表 */
    @Schema(description="商品销售库存信息列表")
    private List<ProductSaleStockInDTO> productSaleStocks;

    public ProductExtraInDTO getProductExtra() {
        return productExtra;
    }

    public void setProductExtra(ProductExtraInDTO productExtra) {
        this.productExtra = productExtra;
    }

    public List<ProductSaleSpecInDTO> getProductSaleSpecs() {
        return productSaleSpecs;
    }

    public void setProductSaleSpecs(List<ProductSaleSpecInDTO> productSaleSpecs) {
        this.productSaleSpecs = productSaleSpecs;
    }

    public List<ProductSaleStockInDTO> getProductSaleStocks() {
        return productSaleStocks;
    }

    public void setProductSaleStocks(List<ProductSaleStockInDTO> productSaleStocks) {
        this.productSaleStocks = productSaleStocks;
    }

}

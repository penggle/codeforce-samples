package com.penglecode.codeforce.samples.provider.product.api.request;

import com.penglecode.codeforce.common.model.BaseDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductBaseSaveDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductExtraSaveDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductSaleSpecSaveDTO;
import com.penglecode.codeforce.samples.provider.product.api.dto.ProductSaleStockSaveDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 保存(创建/修改)商品请求DTO
 *
 * @author pengpeng
 * @version 1.0
 */
@Schema(description="创建商品请求DTO")
public class SaveProductRequest extends ProductBaseSaveDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /** 商品额外信息 */
    @Schema(description="商品额外信息")
    private ProductExtraSaveDTO productExtra;

    /** 商品销售规格信息列表 */
    @Schema(description="商品销售规格信息列表")
    private List<ProductSaleSpecSaveDTO> productSaleSpecs;

    /** 商品销售库存信息列表 */
    @Schema(description="商品销售库存信息列表")
    private List<ProductSaleStockSaveDTO> productSaleStocks;

    public ProductExtraSaveDTO getProductExtra() {
        return productExtra;
    }

    public void setProductExtra(ProductExtraSaveDTO productExtra) {
        this.productExtra = productExtra;
    }

    public List<ProductSaleSpecSaveDTO> getProductSaleSpecs() {
        return productSaleSpecs;
    }

    public void setProductSaleSpecs(List<ProductSaleSpecSaveDTO> productSaleSpecs) {
        this.productSaleSpecs = productSaleSpecs;
    }

    public List<ProductSaleStockSaveDTO> getProductSaleStocks() {
        return productSaleStocks;
    }

    public void setProductSaleStocks(List<ProductSaleStockSaveDTO> productSaleStocks) {
        this.productSaleStocks = productSaleStocks;
    }

}

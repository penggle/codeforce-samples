package com.penglecode.codeforce.samples.product.api.facade.impl;

import com.penglecode.codeforce.common.model.DefaultResponse;
import com.penglecode.codeforce.common.model.Page;
import com.penglecode.codeforce.common.model.PageRequest;
import com.penglecode.codeforce.common.model.PageResponse;
import com.penglecode.codeforce.common.support.BeanCopier;
import com.penglecode.codeforce.common.web.servlet.support.ServletHttpApiSupport;
import com.penglecode.codeforce.samples.product.api.model.ProductPageInDTO;
import com.penglecode.codeforce.samples.product.api.facade.ProductApiFacade;
import com.penglecode.codeforce.samples.product.api.request.ByIdProductRequest;
import com.penglecode.codeforce.samples.product.api.request.ByIdsProductRequest;
import com.penglecode.codeforce.samples.product.api.request.SaveProductRequest;
import com.penglecode.codeforce.samples.product.api.model.ProductOutDTO;
import com.penglecode.codeforce.samples.product.application.service.ProductAppService;
import com.penglecode.codeforce.samples.product.domain.model.ProductAggregate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品API接口(实现)
 *
 * @author pengpeng
 * @version 1.0
 */
@RestController
public class ProductApiFacadeImpl extends ServletHttpApiSupport implements ProductApiFacade {

    @Resource(name="productAppService")
    private ProductAppService productAppService;

    @Override
    public DefaultResponse<Long> createProduct(SaveProductRequest createRequest) {
        ProductAggregate product = BeanCopier.copy(createRequest, ProductAggregate::new);
        productAppService.createProduct(product);
        return DefaultResponse.<Long>ok().data(product.getProductId()).build();
    }

    @Override
    public DefaultResponse<Void> modifyProduct(SaveProductRequest modifyRequest) {
        ProductAggregate product = BeanCopier.copy(modifyRequest, ProductAggregate::new);
        productAppService.modifyProductById(product);
        return DefaultResponse.<Void>ok().build();
    }

    @Override
    public DefaultResponse<Void> removeProductById(ByIdProductRequest removeRequest) {
        productAppService.removeProductById(removeRequest.getProductId());
        return DefaultResponse.<Void>ok().build();
    }

    @Override
    public DefaultResponse<Void> removeProductByIds(ByIdsProductRequest removeRequest) {
        productAppService.removeProductByIds(removeRequest.getProductIds());
        return DefaultResponse.<Void>ok().build();
    }

    @Override
    public DefaultResponse<ProductOutDTO> getProductById(ByIdProductRequest queryRequest) {
        ProductAggregate product = productAppService.getProductById(queryRequest.getProductId(), queryRequest.isCascade());
        ProductOutDTO queryResponse = BeanCopier.copy(product, ProductOutDTO::new);
        return DefaultResponse.<ProductOutDTO>ok().data(queryResponse).build();
    }

    @Override
    public DefaultResponse<List<ProductOutDTO>> getProductByIds(ByIdsProductRequest queryRequest) {
        List<ProductAggregate> products = productAppService.getProductsByIds(queryRequest.getProductIds(), queryRequest.isCascade());
        List<ProductOutDTO> queryResponses = BeanCopier.copy(products, ProductOutDTO::new);
        return DefaultResponse.<List<ProductOutDTO>>ok().data(queryResponses).build();
    }

    @Override
    public PageResponse<ProductOutDTO> getProductsByPage(PageRequest<ProductPageInDTO> queryRequest) {
        ProductAggregate condition = BeanCopier.copy(queryRequest, ProductAggregate::new);
        Page page = Page.of(queryRequest.getPageIndex(), queryRequest.getPageSize(), queryRequest.getOrderBys());
        List<ProductAggregate> products = productAppService.getProductsByPage(condition, page, queryRequest.isCascade());
        List<ProductOutDTO> dataList = BeanCopier.copy(products, ProductOutDTO::new);
        return PageResponse.<ProductOutDTO>ok().data(dataList).page(page).build();
    }

}

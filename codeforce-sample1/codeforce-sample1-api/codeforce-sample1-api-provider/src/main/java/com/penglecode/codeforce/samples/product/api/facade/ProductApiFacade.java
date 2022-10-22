package com.penglecode.codeforce.samples.product.api.facade;

import com.penglecode.codeforce.common.model.DefaultResponse;
import com.penglecode.codeforce.common.model.PageRequest;
import com.penglecode.codeforce.common.model.PageResponse;
import com.penglecode.codeforce.samples.product.api.model.ProductPageInDTO;
import com.penglecode.codeforce.samples.product.api.request.ByIdProductRequest;
import com.penglecode.codeforce.samples.product.api.request.ByIdsProductRequest;
import com.penglecode.codeforce.samples.product.api.request.SaveProductRequest;
import com.penglecode.codeforce.samples.product.api.model.ProductOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品API接口服务
 *
 * @author pengpeng
 * @version 1.0
 */
@RequestMapping("/api/product")
@Tag(name="ProductApiFacade", description="商品API接口")
public interface ProductApiFacade {

    /**
     * 创建商品
     *
     * @param createRequest - 请求参数
     * @return
     */
    @Operation(summary="创建商品")
    @PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<Long> createProduct(@RequestBody SaveProductRequest createRequest);

    /**
     * 修改商品
     *
     * @param modifyRequest - 请求参数
     * @return
     */
    @Operation(summary="修改商品")
    @PostMapping(value="/modify", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<Void> modifyProduct(@RequestBody SaveProductRequest modifyRequest);

    /**
     * 根据商品ID删除商品
     *
     * @param removeRequest - 请求参数
     * @return
     */
    @Operation(summary="根据商品ID删除商品")
    @PostMapping(value="/remove", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<Void> removeProductById(@RequestBody ByIdProductRequest removeRequest);

    /**
     * 根据多个商品ID删除商品
     *
     * @param removeRequest - 请求参数
     * @return
     */
    @Operation(summary="根据多个商品ID删除商品")
    @PostMapping(value="/removes", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<Void> removeProductByIds(@RequestBody ByIdsProductRequest removeRequest);

    /**
     * 根据商品ID获取商品详情
     *
     * @param detailRequest - 请求参数
     * @return
     */
    @Operation(summary="根据商品ID获取商品详情")
    @GetMapping(value="/detail", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<ProductOutDTO> getProductById(@RequestBody ByIdProductRequest detailRequest);

    /**
     * 根据多个商品ID获取商品详情
     *
     * @param detailRequest - 请求参数
     * @return
     */
    @Operation(summary="根据多个商品ID获取商品")
    @PostMapping(value="/ids", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    DefaultResponse<List<ProductOutDTO>> getProductByIds(@RequestBody ByIdsProductRequest detailRequest);

    /**
     * 根据条件分页查询商品
     *
     * @param queryRequest  - 查询参数
     * @return
     */
    @Operation(summary="根据条件分页查询商品")
    @PostMapping(value="/list", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    PageResponse<ProductOutDTO> getProductsByPage(@RequestBody PageRequest<ProductPageInDTO> queryRequest);

}

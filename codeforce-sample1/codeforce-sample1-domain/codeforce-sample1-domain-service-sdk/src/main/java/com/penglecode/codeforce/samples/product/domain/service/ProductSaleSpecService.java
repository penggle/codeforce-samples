package com.penglecode.codeforce.samples.product.domain.service;


import com.penglecode.codeforce.common.domain.ID;
import com.penglecode.codeforce.samples.product.domain.model.ProductSaleSpec;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

/**
 * 商品销售规格信息 领域服务接口
 *
 * @author AutoCodeGenerator
 * @version 1.0
 */
public interface ProductSaleSpecService {

    /**
     * 创建商品销售规格信息
     *
     * @param productSaleSpec     - 被保存的领域对象
     */
    void createProductSaleSpec(ProductSaleSpec productSaleSpec);

    /**
     * 批量创建商品销售规格信息
     *
     * @param productSaleSpecs     - 被保存的领域对象集合
     */
    void createProductSaleSpecs(List<ProductSaleSpec> productSaleSpecs);

    /**
     * 根据ID修改商品销售规格信息
     *
     * @param productSaleSpec     - 被保存的领域对象(id字段必须有值)
     */
    void modifyProductSaleSpecById(ProductSaleSpec productSaleSpec);

    /**
     * 根据ID批量修改商品销售规格信息
     *
     * @param productSaleSpecs     - 被保存的领域对象集合(id字段必须有值)
     */
    void modifyProductSaleSpecsById(List<ProductSaleSpec> productSaleSpecs);

    /**
     * 根据ID删除商品销售规格信息
     *
     * @param id    - ID主键
     */
    void removeProductSaleSpecById(ID id);

    /**
     * 根据多个ID删除商品销售规格信息
     *
     * @param ids    - ID主键列表
     */
    void removeProductSaleSpecByIds(List<ID> ids);

    /**
     * 根据商品信息ID删除商品销售规格信息列表
     *
     * @param productId   - 商品信息ID
     */
    void removeProductSaleSpecsByProductId(Long productId);

    /**
     * 根据ID获取商品销售规格信息
     *
     * @param id    - ID主键
     * @return 返回完整的领域对象信息
     */
    ProductSaleSpec getProductSaleSpecById(ID id);

    /**
     * 根据多个ID获取商品销售规格信息
     *
     * @param ids   - ID主键列表
     * @return 返回完整的领域对象信息
     */
    List<ProductSaleSpec> getProductSaleSpecsByIds(List<ID> ids);

    /**
     * 根据商品信息ID获取商品销售规格信息列表
     *
     * @param productId   - 商品信息ID
     * @return 返回完整的领域对象信息
     */
    List<ProductSaleSpec> getProductSaleSpecsByProductId(Long productId);

    /**
     * 根据多个商品信息ID获取商品销售规格信息列表
     *
     * @param productIds   - 商品信息ID列表
     * @return 返回完整的领域对象信息
     */
    Map<Long,List<ProductSaleSpec>> getProductSaleSpecsByProductIds(List<Long> productIds);

    /**
     * 获取商品销售规格信息总记录数
     *
     * @return 返回商品销售规格信息总记录数
     */
    int getProductTotalCount();

    /**
     * 基于Mybatis游标操作，遍历所有商品销售规格信息
     * (在数据量大的情况下，避免一次加载出所有数据而引起内存溢出)
     * 典型示例1、(数据量不大的情况下一次获取所有元素)：
     *      List<MyModel> allModels = new ArrayList<>(); //承接所有元素的集合
     *      myModelService.forEachProductSaleSpec(allModels::add); //加载所有元素进入集合
     *
     * 典型示例2、(数据量大的情况需要逐步迭代处理每个元素)：
     *      myModelService.forEachProductSaleSpec(System.out::println); //逐步迭代处理每个元素
     *
     * @param consumer  - 遍历元素的Consumer
     */
    void forEachProductSaleSpec(Consumer<ProductSaleSpec> consumer);

    /**
     * 基于Mybatis游标操作，遍历所有商品销售规格信息
     * (在数据量大的情况下，避免一次加载出所有数据而引起内存溢出)
     *
     * 典型示例、(数据量大的情况需要逐步迭代处理每个元素)：
     *      myModelService.forEachProductSaleSpec((item, index) -> {
     *          System.out.println("index = " + index + ", item = " + item);
     *      }); //逐步迭代处理每个元素
     *
     * @param consumer  - 遍历元素的Consumer
     */
    void forEachProductSaleSpec(ObjIntConsumer<ProductSaleSpec> consumer);

}
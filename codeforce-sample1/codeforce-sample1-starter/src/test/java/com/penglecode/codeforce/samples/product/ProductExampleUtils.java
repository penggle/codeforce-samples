package com.penglecode.codeforce.samples.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.penglecode.codeforce.common.support.BeanCopier;
import com.penglecode.codeforce.common.util.CollectionUtils;
import com.penglecode.codeforce.common.util.DateTimeUtils;
import com.penglecode.codeforce.common.util.JsonUtils;
import com.penglecode.codeforce.common.util.StringUtils;
import com.penglecode.codeforce.samples.product.application.service.ProductAppService;
import com.penglecode.codeforce.samples.product.domain.enums.ProductAuditStatusEnum;
import com.penglecode.codeforce.samples.product.domain.enums.ProductOnlineStatusEnum;
import com.penglecode.codeforce.samples.product.domain.enums.ProductTypeEnum;
import com.penglecode.codeforce.samples.product.domain.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品阳历数据工具类
 *
 * @author pengpeng
 * @version 1.0
 */
public class ProductExampleUtils {

    private static final Random RANDOM = new Random();

    /**
     * 获取专门用于{@link ProductAppService#createProduct(ProductAggregate)}的示例数据
     */
    public static ProductAggregate getExampleProduct4Create(Long productId) {
        String nowTime = DateTimeUtils.formatNow();
        ProductBaseInfo productBase = new ProductBaseInfo();
        productBase.setProductId(productId);
        productBase.setProductName("24期免息【当天发】Huawei/华为Mate40 5G手机官方旗舰店50pro直降mate40e官网30正品4G鸿蒙正品30全网通");
        productBase.setProductUrl("https://detail.tmall.com/item.htm?id=633658852628");
        productBase.setProductTags("华为手机 5G mate40pro");
        productBase.setProductType(ProductTypeEnum.PHYSICAL_PRODUCT.getTypeCode());
        productBase.setAuditStatus(ProductAuditStatusEnum.WAIT_AUDIT.getStatusCode());
        productBase.setOnlineStatus(ProductOnlineStatusEnum.OFFLINE.getStatusCode());
        productBase.setShopId(111212422L);
        productBase.setRemark("当天发货 保修3年 送影视会员 咨询客服");
        productBase.setCreateTime(nowTime);
        productBase.setUpdateTime(nowTime);

        ProductExtraInfo productExtra = new ProductExtraInfo();
        productExtra.setProductId(productBase.getProductId());
        productExtra.setProductDetails("商品详情");
        productExtra.setProductSpecifications("商品规格参数");
        productExtra.setProductServices("商品服务");
        productExtra.setCreateTime(nowTime);
        productExtra.setUpdateTime(nowTime);

        List<ProductSaleSpec> productSaleSpecs = new ArrayList<>();
        List<ProductSaleStock> productSaleStocks = new ArrayList<>();

        List<String> nets = Arrays.asList("11:4G全网通", "12:5G全网通");
        List<String> colors = Arrays.asList("21:亮黑色", "22:釉白色", "23:秘银色", "24:夏日胡杨", "25:秋日胡杨");
        List<String> storages = Arrays.asList("31:8+128GB", "32:8+256GB");
        List<List<String>> specs = Arrays.asList(nets, colors, storages);

        for(int i = 0, len1 = specs.size(); i < len1; i++) {
            for(int j = 0, len2 = specs.get(i).size(); j < len2; j++) {
                String[] specNoAndName = specs.get(i).get(j).split(":");
                ProductSaleSpec productSpec = new ProductSaleSpec();
                productSpec.setProductId(productBase.getProductId());
                productSpec.setSpecNo(specNoAndName[0]);
                productSpec.setSpecName(specNoAndName[1]);
                productSpec.setSpecIndex(Integer.valueOf("" + productSpec.getSpecNo().charAt(1)));
                productSpec.setCreateTime(nowTime);
                productSpec.setUpdateTime(nowTime);
                productSaleSpecs.add(productSpec);
            }
        }

        Map<Character,List<ProductSaleSpec>> groupedProductSaleSpecs = productSaleSpecs.stream().collect(Collectors.groupingBy(spec -> spec.getSpecNo().charAt(0)));
        List<ProductSaleSpec> productSaleSpecs0 = groupedProductSaleSpecs.get('1');
        List<ProductSaleSpec> productSaleSpecs1 = groupedProductSaleSpecs.get('2');
        List<ProductSaleSpec> productSaleSpecs2 = groupedProductSaleSpecs.get('3');
        List<List<ProductSaleSpec>> cartesians = CollectionUtils.cartesianProduct(Arrays.asList(productSaleSpecs0, productSaleSpecs1, productSaleSpecs2)); //笛卡尔积

        for (List<ProductSaleSpec> cartesian : cartesians) {
            StringBuilder specNos = new StringBuilder();
            StringBuilder specNames = new StringBuilder();
            for (int j = 0, len2 = cartesian.size(); j < len2; j++) {
                specNos.append(cartesian.get(j).getSpecNo());
                specNames.append(cartesian.get(j).getSpecName());
                if (j != len2 - 1) {
                    specNos.append(":");
                    specNames.append(":");
                }
            }
            ProductSaleStock productSaleStock = new ProductSaleStock();
            productSaleStock.setProductId(productBase.getProductId());
            productSaleStock.setSpecNo(specNos.toString());
            productSaleStock.setSpecName(specNames.toString());
            productSaleStock.setSellPrice(619900L);
            productSaleStock.setStock(999);
            productSaleStock.setCreateTime(nowTime);
            productSaleStock.setUpdateTime(nowTime);
            productSaleStocks.add(productSaleStock);
        }
        ProductAggregate product = BeanCopier.copy(productBase, ProductAggregate::new);
        product.setProductExtra(productExtra);
        product.setProductSaleSpecs(productSaleSpecs);
        product.setProductSaleStocks(productSaleStocks);
        return product;
    }

    /**
     * 获取专门用于{@link ProductAppService#modifyProductById(ProductAggregate)}的示例数据
     */
    public static ProductAggregate getExampleProduct4Modify(Long productId) {
        String nowTime = DateTimeUtils.formatNow();
        ProductBaseInfo productBase = new ProductBaseInfo();
        productBase.setProductId(productId);
        productBase.setProductName("24期免息【当天发】Huawei/华为Mate40 5G手机官方旗舰店50pro直降mate40e官网30正品4G鸿蒙正品30全网通AAA");
        productBase.setProductUrl("https://detail.tmall.com/item.htm?id=633658852628");
        productBase.setProductTags("华为手机 5G mate40proAAA");
        productBase.setProductType(ProductTypeEnum.PHYSICAL_PRODUCT.getTypeCode());
        productBase.setAuditStatus(ProductAuditStatusEnum.WAIT_AUDIT.getStatusCode());
        productBase.setOnlineStatus(ProductOnlineStatusEnum.OFFLINE.getStatusCode());
        productBase.setShopId(111212422L);
        productBase.setRemark("当天发货 保修3年 送影视会员 咨询客服AAA");
        productBase.setCreateTime(nowTime);
        productBase.setUpdateTime(nowTime);

        ProductExtraInfo productExtra = new ProductExtraInfo();
        productExtra.setProductId(productBase.getProductId());
        productExtra.setProductDetails("商品详情AAA");
        productExtra.setProductSpecifications("商品规格参数AAA");
        productExtra.setProductServices("商品服务AAA");
        productExtra.setCreateTime(nowTime);
        productExtra.setUpdateTime(nowTime);

        List<ProductSaleSpec> productSaleSpecs = new ArrayList<>();
        List<ProductSaleStock> productSaleStocks = new ArrayList<>();

        List<String> nets = Arrays.asList("11:4G全网通", "12:5G全网通");
        List<String> colors = Arrays.asList("22:釉白色", "23:秘银色", "24:夏日胡杨", "25:秋日胡杨", "26:暗黑色"); //修改颜色销售规格
        List<String> storages = Arrays.asList("31:8+128GB", "32:8+256GB");
        List<List<String>> specs = Arrays.asList(nets, colors, storages);

        for(int i = 0, len1 = specs.size(); i < len1; i++) {
            for(int j = 0, len2 = specs.get(i).size(); j < len2; j++) {
                String[] specNoAndName = specs.get(i).get(j).split(":");
                ProductSaleSpec productSpec = new ProductSaleSpec();
                productSpec.setProductId(productBase.getProductId());
                productSpec.setSpecNo(specNoAndName[0]);
                productSpec.setSpecName(specNoAndName[1]);
                productSpec.setSpecIndex(Integer.valueOf("" + productSpec.getSpecNo().charAt(1)));
                productSpec.setCreateTime(nowTime);
                productSpec.setUpdateTime(nowTime);
                productSaleSpecs.add(productSpec);
            }
        }

        Map<Character,List<ProductSaleSpec>> groupedProductSaleSpecs = productSaleSpecs.stream().collect(Collectors.groupingBy(spec -> spec.getSpecNo().charAt(0)));
        List<ProductSaleSpec> productSaleSpecs0 = groupedProductSaleSpecs.get('1');
        List<ProductSaleSpec> productSaleSpecs1 = groupedProductSaleSpecs.get('2');
        List<ProductSaleSpec> productSaleSpecs2 = groupedProductSaleSpecs.get('3');
        List<List<ProductSaleSpec>> cartesians = CollectionUtils.cartesianProduct(Arrays.asList(productSaleSpecs0, productSaleSpecs1, productSaleSpecs2)); //笛卡尔积

        for (List<ProductSaleSpec> cartesian : cartesians) {
            StringBuilder specNos = new StringBuilder();
            StringBuilder specNames = new StringBuilder();
            for (int j = 0, len2 = cartesian.size(); j < len2; j++) {
                specNos.append(cartesian.get(j).getSpecNo());
                specNames.append(cartesian.get(j).getSpecName());
                if (j != len2 - 1) {
                    specNos.append(":");
                    specNames.append(":");
                }
            }
            ProductSaleStock productSaleStock = new ProductSaleStock();
            productSaleStock.setProductId(productBase.getProductId());
            productSaleStock.setSpecNo(specNos.toString());
            productSaleStock.setSpecName(specNames.toString());
            productSaleStock.setSellPrice(619900L);
            productSaleStock.setStock(999);
            productSaleStock.setCreateTime(nowTime);
            productSaleStock.setUpdateTime(nowTime);
            productSaleStocks.add(productSaleStock);
        }
        ProductAggregate product = BeanCopier.copy(productBase, ProductAggregate::new);
        product.setProductExtra(productExtra);
        product.setProductSaleSpecs(productSaleSpecs);
        product.setProductSaleStocks(productSaleStocks);
        return product;
    }

    public static List<ProductAggregate> getExampleProductList() {
        try {
            Resource sampleResource = new ClassPathResource("product-example-list.json");
            String json = StreamUtils.copyToString(sampleResource.getInputStream(), StandardCharsets.UTF_8);
            List<ProductExample> productExampleList = JsonUtils.json2Object(json, new TypeReference<List<ProductExample>>() {});
            return productExampleList.stream().map(ProductExampleUtils::buildProduct).collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    protected static ProductAggregate buildProduct(ProductExample sample) {
        String nowTime = DateTimeUtils.formatNow();
        ProductAggregate product = new ProductAggregate();
        product.setProductName(sample.getProductName());
        product.setProductType(sample.getProductType());
        product.setProductUrl(sample.getProductUrl());
        product.setProductTags(sample.getProductTags());
        product.setShopId(sample.getShopId());
        product.setAuditStatus(RANDOM.nextInt(3));
        product.setOnlineStatus(RANDOM.nextInt(2));
        product.setCreateTime(nowTime);
        product.setUpdateTime(nowTime);

        ProductExtraInfo productExtra = new ProductExtraInfo();
        productExtra.setProductDetails(sample.getProductUrl());
        productExtra.setProductSpecifications("商品规格");
        productExtra.setProductServices("正品行货，七天包退换");
        productExtra.setCreateTime(nowTime);
        productExtra.setUpdateTime(nowTime);
        product.setProductExtra(productExtra);

        List<List<ProductSaleSpec>> groupedProductSaleSpecs = new ArrayList<>();
        for(Map.Entry<Integer,List<String>> entry : sample.getProductSaleSpecs().entrySet()) {
            List<ProductSaleSpec> subProductSaleSpecs = new ArrayList<>();
            Integer index = entry.getKey();
            List<String> specNames = entry.getValue();
            int specSize = specNames.size();
            for(int i = 1; i <= specSize; i++) {
                ProductSaleSpec productSaleSpec = new ProductSaleSpec();
                productSaleSpec.setSpecNo(index + StringUtils.leftPad(String.valueOf(i), 2, "0"));
                productSaleSpec.setSpecName(specNames.get(i - 1));
                productSaleSpec.setSpecIndex(i);
                productSaleSpec.setCreateTime(nowTime);
                productSaleSpec.setUpdateTime(nowTime);
                subProductSaleSpecs.add(productSaleSpec);
            }
            groupedProductSaleSpecs.add(subProductSaleSpecs);
        }
        product.setProductSaleSpecs(groupedProductSaleSpecs.stream().flatMap(Collection::stream).collect(Collectors.toList()));

        List<ProductSaleStock> productSaleStocks = new ArrayList<>();
        List<List<ProductSaleSpec>> cartesians = CollectionUtils.cartesianProduct(groupedProductSaleSpecs); //笛卡尔积
        for (List<ProductSaleSpec> cartesian : cartesians) {
            StringBuilder specNos = new StringBuilder();
            StringBuilder specNames = new StringBuilder();
            for (int j = 0, len2 = cartesian.size(); j < len2; j++) {
                specNos.append(cartesian.get(j).getSpecNo());
                specNames.append(cartesian.get(j).getSpecName());
                if (j != len2 - 1) {
                    specNos.append(":");
                    specNames.append(":");
                }
            }
            ProductSaleStock productSaleStock = new ProductSaleStock();
            productSaleStock.setSpecNo(specNos.toString());
            productSaleStock.setSpecName(specNames.toString());
            long sellPrice = sample.getAvgSellPrice();
            int delta = (int) (sellPrice * 0.2);
            productSaleStock.setSellPrice(RANDOM.nextBoolean() ? sellPrice + RANDOM.nextInt(delta) : sellPrice - RANDOM.nextInt(delta));
            productSaleStock.setStock(RANDOM.nextInt(1000));
            productSaleStock.setCreateTime(nowTime);
            productSaleStock.setUpdateTime(nowTime);
            productSaleStocks.add(productSaleStock);
        }
        product.setProductSaleStocks(productSaleStocks);

        return product;
    }

}

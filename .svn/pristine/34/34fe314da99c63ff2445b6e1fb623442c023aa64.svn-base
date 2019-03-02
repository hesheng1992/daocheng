package com.a1magway.bgg.data.entity;

import com.almagway.common.utils.CollectionUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 加入购物车的商品
 * Created by jph on 2017/8/14.
 */
public class CartProduct implements Serializable {


    private static final long serialVersionUID = -7748679057356224474L;
    /**
     * id : 184
     * productId : 14
     * productName : 欧洲名牌
     * product_code : 货号
     * brand : prada
     * skuId : 20
     * specs : 颜色:红色 尺码:38 容量:50ml
     * listPrice : 5000
     * sellPrice : 3000
     * saleRate : 15%off
     * count : 5
     * totalStock : 3
     * skuCover : ["/vandafile/platform/commodity/picture/201609/1472835548209_46728356.jpg","/vandafile/platform/commodity/picture/201609/1472835548209_46765699.jpg"]
     * color : 蓝色
     * measures : UNI
     * style :
     * volume : 50ml
     * freight : 180
     * tax : 1111
     * isValid : true
     */

    private int id;
    private int productId;
    private String productName;
    private String productCode;
    private String brand;
    private int skuId;
    private String specs;
    private String listPrice;
    private String sellPrice;
    private String saleRate;
    private int count;
    private int totalStock;
    private String color;
    private String measures;
    private String style;
    private String volume;
    private String freight;
    private String tax;
    private boolean valid;
    private List<String> skuCover;
    private String memberPrice;
    private String specsMap;
    private String valiInfo;
    private int productType;
    private int couponsNum;
    private int categoryId;
    private int brandId;
    private String subject_id;

    ////本地使用////
    private boolean selected;


    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public CartProduct() {
        setSelected(true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getSkuCover() {
        return skuCover;
    }

    public void setSkuCover(List<String> skuCover) {
        this.skuCover = skuCover;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getSpecsMap() {
        return specsMap;
    }

    public void setSpecsMap(String specsMap) {
        this.specsMap = specsMap;
    }

    public String getValiInfo() {
        return valiInfo;
    }

    public void setValiInfo(String valiInfo) {
        this.valiInfo = valiInfo;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getCouponsNum() {
        return couponsNum;
    }

    public void setCouponsNum(int couponsNum) {
        this.couponsNum = couponsNum;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCoverUrl() {
        if (CollectionUtil.isEmpty(getSkuCover())) {
            return null;
        }

        return getSkuCover().get(0);
    }

    /**
     * 通过是否下架和是否有库存判断是否可用
     *
     * @return
     */
    public boolean isEnable() {
        return isValid() && getTotalStock() > 0;
    }


}

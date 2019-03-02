package com.a1magway.bgg.data.entity;

import com.almagway.common.utils.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 商品对应的货品，多个规格对应下的货品
 * Created by jph on 2017/8/7.
 */
public class Commodity implements Serializable {

    private static final long serialVersionUID = -5792209652253084335L;

    /**
     * id : 3182
     * skuId : 3182
     * productName : CALVIN KLEIN POLO衫 & T恤 蓝 K10K100970JASESH478
     * productId : 1424
     * brand : CALVIN KLEIN
     * listPrice : 1051.98
     * sellPrice : 788.00
     * totalStock : 29162
     * saleRate : 25%OFF
     * skuCover : ["https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782806620_917498096.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811406_919345138.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811544_921192180.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811549_942433163.jpg"]
     * color : 蓝色
     * measures : S
     * style : null
     * volume : null
     * specs : 颜色:蓝色 尺寸:S
     * specsMap : {"颜色":"蓝色","尺寸":"S"}
     * count : null
     * productCode : K10K100970JASESH478
     * tax : 0.00
     * freight : ￥180
     * valiInfo : null
     * valid : true
     */


    private int skuId = 0;
    private String productName= "";
    private int productId = 0;
    private String brand = "";
    private String listPrice = "";
    private String sellPrice = "";
    private int totalStock = 0;
    private String saleRate = "";
    private String color = "";
    private String measures = "";
    private Object style;
    private Object volume;
    private String specs = "";
    private HashMap<String, String> specsMap;
    private int count;
    private String productCode = "";
    private String tax = "";
    private String freight = "";
    private Object valiInfo;
    private boolean valid;
    private List<String> skuCover;
    private int id;
    private String memberPrice = "";
    private String specsList = "";
    private int productType = 0;
    private int couponsNum = 0;
    private int categoryId = 0;
    private int brandId = 0;
    private String briefin;

    public String getBriefin() {
        return briefin;
    }

    public void setBriefin(String briefin) {
        this.briefin = briefin;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
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

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public Object getVolume() {
        return volume;
    }

    public void setVolume(Object volume) {
        this.volume = volume;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public HashMap<String, String> getSpecsMap() {
        return specsMap;
    }

    public void setSpecsMap(HashMap<String, String> specsMap) {
        this.specsMap = specsMap;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public Object getValiInfo() {
        return valiInfo;
    }

    public void setValiInfo(Object valiInfo) {
        this.valiInfo = valiInfo;
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

    public boolean isDiscount() {
        return StringUtil.isNotEmpty(getSaleRate());
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getSpecsList() {
        return specsList;
    }

    public void setSpecsList(String specsList) {
        this.specsList = specsList;
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

    @Override
    public String toString() {
        return "Commodity{" +
                "skuId=" + skuId +
                ", productName='" + productName + '\'' +
                ", productId=" + productId +
                ", brand='" + brand + '\'' +
                ", listPrice='" + listPrice + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", totalStock=" + totalStock +
                ", saleRate='" + saleRate + '\'' +
                ", color='" + color + '\'' +
                ", measures='" + measures + '\'' +
                ", style=" + style +
                ", volume=" + volume +
                ", specs='" + specs + '\'' +
                ", specsMap=" + specsMap +
                ", count=" + count +
                ", productCode='" + productCode + '\'' +
                ", tax='" + tax + '\'' +
                ", freight='" + freight + '\'' +
                ", valiInfo=" + valiInfo +
                ", valid=" + valid +
                ", skuCover=" + skuCover +
                ", id=" + id +
                ", memberPrice='" + memberPrice + '\'' +
                ", specsList='" + specsList + '\'' +
                ", productType=" + productType +
                ", couponsNum=" + couponsNum +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                '}';
    }
}

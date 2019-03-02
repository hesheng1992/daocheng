package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jph on 2017/7/31.
 */
public class LoginData implements Serializable {

    /**
     * id:用户id
     * gender:性别（0：男 1：女 2：其他）
     * birthday:生日
     * telephone:手机号
     * defaultAddress:默认地址
     * monthlySalary:月薪
     * profession:职业
     * roleType:（0 普通 1会员2万达会员（既是会员 也是万达会员为3
     * userName：用户姓名
     * cars：(
     * startTime：卡有效期始
     * endTime：卡有效期止
     * cardType:卡类型
     * cardNumber:卡号
     * permissionList：权限列表（
     * permissionsType:权限类型
     * permissonsCategory:权限标签
     * permissionsStatus：权限状态
     * name：名称
     * description：描述
     * imagePath：大图
     * iconPath:小图
     * count:权益享受次数）
     * )
     */

    private int id;
    private String birthday;
    private String gender;
    private String telephone;
    private AddressData defaultAddress;
    private String monthlySalary;
    private String profession;
    private int roleType;
    private String userName;
    private String nickName = "";
    private String inviterName;//邀请者昵称
    private String inviteCode;
    private int memberGrade;//会员等级1：普通用户 2：普通会员 3：中级试用会员 4：中级会员 5：高级会员 6：超级会员//中级及以上等级可以查看利润和销售订单
    //时尚买手:格格（4级）、阿哥（4级）；时尚代理：郡主（6级），贝勒(6级);
    private List<Cards> cards;
    private String rank;
    private String expirationDate;
    private String authorizeDate;
    private String authorizeTitle;

    private String iconurl;//微信登录AuthBean返回的用户头像地址保存起来//本地参数
    private String iconPath;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(String authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public String getAuthorizeTitle() {
        return authorizeTitle;
    }

    public void setAuthorizeTitle(String authorizeTitle) {
        this.authorizeTitle = authorizeTitle;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    private boolean isWeixinLogin;//判断是否从微信端登录；//本地参数

    public boolean isWeixinLogin() {
        return isWeixinLogin;
    }

    public void setWeixinLogin(boolean weixinLogin) {
        isWeixinLogin = weixinLogin;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AddressData getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(AddressData defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public int getMemberGrade() {
        return memberGrade;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /*   public String getMemberGradeName() {
        switch (memberGrade) {
            case 1:
                return "普通用户";
            case 2:
                return "普通会员";
            case 3:
                return "中级试用会员";
            case 4:
                return "中级会员";
            case 5:
                return "高级会员";
            case 6:
                return "超级会员";
            default:
                return "普通用户";
        }
    }*/


    public String getMemberGradeName() {
        if (gender.equals("男")) {
            switch (memberGrade) {
                case 4:
                    return "阿哥";
                case 6:
                    return "贝勒";
                default:
                    return "";
            }
        } else {
            switch (memberGrade) {
                case 4:
                    return "格格";
                case 6:
                    return "郡主";
                default:
                    return "";
            }
        }
    }

    public void setMemberGrade(int memberGrade) {
        this.memberGrade = memberGrade;
    }

    public boolean isNull() {
        //TODO enid: 第三方登录没有手机号码 时登录成功
//        return id <= 0 || telephone == null;
        return id <= 0;
    }
}

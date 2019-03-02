package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/8/16.
 */

public class InviteFriendData {


    /**
     * id : 2311
     * lastInvitedDate : null
     * status : 1
     * userType : 1
     * username : null
     * password : null
     * nickName : 魏一峰6
     * realName : null
     * birthDay : null
     * inviteCode : MM6ysu
     * invitedCode : mm3nx1
     * inviterId : 2416
     * idcard : null
     * gender : 0
     * areaId : null
     * address : null
     * email : null
     * telephone : null
     * introduction : null
     * iconPath : https://wx.qlogo.cn/mmopen/vi_32/gb9JnUNFE01HibojgVym7UdgG9gl4wQAF2Tia0dH3EibZ9AZiaI0nicAaOc7bl3NINZXzLKbZcMmHjVN3Co9kpfNvyQ/132
     * createDate : 2018-07-17 15:35:55
     * lastLoginDate : null
     * modifyDate : 2018-07-18 17:37:23
     * modifyUserId : 34
     * modifyMetaValue : operator
     * complete : true
     * weChar : null
     * qqNumber : null
     * parentMultiLevelSalesId : null
     * distributionAgreementId : null
     * userAccountId : 2262
     * multiLevelSalesId : null
     * useCardId : null
     * certificationCode : null
     * certificationCodeDate : null
     * profession : null
     * monthlySalary : null
     * passportNo : null
     * roleType : null
     * memberGrade : 4
     * memberOverDate : 2019-07-18 17:37:23
     * openId : oN8EjxDVq7oFAiIv1WEq-u2Jcmky
     * saleMoney : 0.24
     * bdUserId : 2240
     * idcardNo : null
     */

    private int id;
    private String lastInvitedDate;
    private int status;
    private int userType;
    private Object username;
    private Object password;
    private String nickName;
    private Object realName;
    private Object birthDay;
    private String inviteCode;
    private String invitedCode;
    private int inviterId;
    private Object idcard;
    private int gender;
    private Object areaId;
    private Object address;
    private Object email;
    private Object telephone;
    private Object introduction;
    private String iconPath;
    private String createDate;
    private Object lastLoginDate;
    private String modifyDate;
    private int modifyUserId;
    private String modifyMetaValue;
    private boolean complete;
    private Object weChar;
    private Object qqNumber;
    private Object parentMultiLevelSalesId;
    private Object distributionAgreementId;
    private int userAccountId;
    private Object multiLevelSalesId;
    private Object useCardId;
    private Object certificationCode;
    private Object certificationCodeDate;
    private Object profession;
    private Object monthlySalary;
    private Object passportNo;
    private Object roleType;
    private int memberGrade;
    private String memberOverDate;
    private String openId;
    private double saleMoney;
    private int bdUserId;
    private Object idcardNo;

    private boolean canInvited;//本地参数，现在是否能邀请

    public boolean isCanInvited() {
        return canInvited;
    }

    public void setCanInvited(boolean canInvited) {
        this.canInvited = canInvited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastInvitedDate() {
        return lastInvitedDate;
    }

    public void setLastInvitedDate(String lastInvitedDate) {
        this.lastInvitedDate = lastInvitedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
        this.realName = realName;
    }

    public Object getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Object birthDay) {
        this.birthDay = birthDay;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public int getInviterId() {
        return inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    public Object getIdcard() {
        return idcard;
    }

    public void setIdcard(Object idcard) {
        this.idcard = idcard;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Object getAreaId() {
        return areaId;
    }

    public void setAreaId(Object areaId) {
        this.areaId = areaId;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getTelephone() {
        return telephone;
    }

    public void setTelephone(Object telephone) {
        this.telephone = telephone;
    }

    public Object getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Object introduction) {
        this.introduction = introduction;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Object lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(int modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyMetaValue() {
        return modifyMetaValue;
    }

    public void setModifyMetaValue(String modifyMetaValue) {
        this.modifyMetaValue = modifyMetaValue;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Object getWeChar() {
        return weChar;
    }

    public void setWeChar(Object weChar) {
        this.weChar = weChar;
    }

    public Object getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(Object qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Object getParentMultiLevelSalesId() {
        return parentMultiLevelSalesId;
    }

    public void setParentMultiLevelSalesId(Object parentMultiLevelSalesId) {
        this.parentMultiLevelSalesId = parentMultiLevelSalesId;
    }

    public Object getDistributionAgreementId() {
        return distributionAgreementId;
    }

    public void setDistributionAgreementId(Object distributionAgreementId) {
        this.distributionAgreementId = distributionAgreementId;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Object getMultiLevelSalesId() {
        return multiLevelSalesId;
    }

    public void setMultiLevelSalesId(Object multiLevelSalesId) {
        this.multiLevelSalesId = multiLevelSalesId;
    }

    public Object getUseCardId() {
        return useCardId;
    }

    public void setUseCardId(Object useCardId) {
        this.useCardId = useCardId;
    }

    public Object getCertificationCode() {
        return certificationCode;
    }

    public void setCertificationCode(Object certificationCode) {
        this.certificationCode = certificationCode;
    }

    public Object getCertificationCodeDate() {
        return certificationCodeDate;
    }

    public void setCertificationCodeDate(Object certificationCodeDate) {
        this.certificationCodeDate = certificationCodeDate;
    }

    public Object getProfession() {
        return profession;
    }

    public void setProfession(Object profession) {
        this.profession = profession;
    }

    public Object getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Object monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Object getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Object passportNo) {
        this.passportNo = passportNo;
    }

    public Object getRoleType() {
        return roleType;
    }

    public void setRoleType(Object roleType) {
        this.roleType = roleType;
    }

    public int getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(int memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMemberOverDate() {
        return memberOverDate;
    }

    public void setMemberOverDate(String memberOverDate) {
        this.memberOverDate = memberOverDate;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public int getBdUserId() {
        return bdUserId;
    }

    public void setBdUserId(int bdUserId) {
        this.bdUserId = bdUserId;
    }

    public Object getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(Object idcardNo) {
        this.idcardNo = idcardNo;
    }
}

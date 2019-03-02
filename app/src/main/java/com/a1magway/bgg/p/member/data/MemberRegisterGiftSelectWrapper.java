package com.a1magway.bgg.p.member.data;

import com.a1magway.bgg.data.entity.MemberRegisterGift;

/**
 * author: Beaven
 * date: 2017/10/16 17:57
 */

public class MemberRegisterGiftSelectWrapper {

    private MemberRegisterGift memberRegisterGift;
    private boolean isSelect;
    private int id;

    public MemberRegisterGiftSelectWrapper(MemberRegisterGift memberRegisterGift, boolean isSelect, int id) {
        this.memberRegisterGift = memberRegisterGift;
        this.isSelect = isSelect;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public MemberRegisterGift getMemberRegisterGift() {
        return memberRegisterGift;
    }

    public void setMemberRegisterGift(MemberRegisterGift memberRegisterGift) {
        this.memberRegisterGift = memberRegisterGift;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}

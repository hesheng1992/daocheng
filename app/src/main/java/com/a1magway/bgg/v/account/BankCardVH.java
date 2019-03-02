package com.a1magway.bgg.v.account;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.repository.IRemoveCard;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.Toaster;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.ApplicationUtil;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/7.
 */

public class BankCardVH extends BaseRecyclerVH<AccountBankCardData> {

    @BindView(R.id.item_bank_card_name_tv)
    TextView mBankCardNameTv;

    @BindView(R.id.item_bank_card_type_tv)
    TextView mBankCardTypeTv;

    @BindView(R.id.item_bank_card_number_tv)
    TextView mBankCardNumberTv;

    @BindView(R.id.item_bank_card_img)
    ImageView mBankCardBackImg;

    @BindView(R.id.swipe_content)
    LinearLayout swipeContent;

    @BindView(R.id.swipe_layout)
    SwipeMenuLayout swipeItemLayout;

    @BindView(R.id.right_menu)
    TextView mDeleteTv;

    private IRemoveCard mIRemoveCardData;

    private String mStartType;

    public BankCardVH(@NonNull ViewGroup parent, IRemoveCard removeCardData, String startType) {
        super(parent, R.layout.item_account_swipe);
        mIRemoveCardData = removeCardData;
        mStartType = startType;
    }

    public void showViewContent(final AccountBankCardData bankCardData, final int position, final BankCardAdapter adapter) {
        swipeItemLayout.setSwipeEnable(true);
        swipeContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeItemLayout.smoothClose();
                if (mStartType.equals(AccountManageActivity.CARD_LIST_TYPE_SELECT)) {
                    EventBus.getDefault().post(bankCardData);
//                    Intent intent = new Intent();
//                    intent.putExtra("data",bankCardData);
//                    ToastUtil.showShort("click");
//                    mView.getActivity().setResult(Activity.RESULT_OK,intent);
//                    mView.getActivity().finish();
                }
            }
        });
        mDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                swipeItemLayout.close();
                new AlertDialog.Builder(getContext())
                        .setMessage("确定解绑银行卡？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mIRemoveCardData.removeCard(adapter.getList().get(position).getId())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new BaseObserver<APIResponse>(getContext()) {
                                            @Override
                                            public void onNext(APIResponse response) {
                                                if (response.isSuccess()) {
                                                    adapter.removeItem(bankCardData);
                                                    Toaster.showShort(getContext(), response.getMsg());
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });

        mBankCardNameTv.setText(bankCardData.getAccountOwner());
        setBankImage(bankCardData.getAbbreviation());
        mBankCardTypeTv.setText(bankCardData.getAccountType());
        String accountNumber = bankCardData.getAccountNumber();
        if (!TextUtils.isEmpty(accountNumber)) {
            String substring = accountNumber.substring(accountNumber.length() - 4);
            mBankCardNumberTv.setText(String.format(ApplicationUtil.getContext().getResources().getString(R.string.account_list_item_card_number), substring));
        }
        if (!TextUtils.isEmpty(bankCardData.getAbbreviation())) {
            String formatUrl = String.format(ApplicationUtil.getContext().getResources().getString(R.string.account_list_item_card_img), bankCardData.getAbbreviation());
//            ImageLoaderUtil.displayImage(mBankCardBackImg,formatUrl);//TODO
        }
    }
//    public interface

    //设置银行卡图片
    private void setBankImage(String abbreviation) {
        String imgUrl = AppConfig.BASE_URL + "/pictures/bank/image/" + abbreviation + "-BG-3.png";
        ImageLoaderUtil.displayImage(mBankCardBackImg, imgUrl);
    }
}

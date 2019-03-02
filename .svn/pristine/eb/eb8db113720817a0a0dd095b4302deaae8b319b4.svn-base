package com.a1magway.bgg.p.articleManager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.MyCreateArticle;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by enid on 2018/8/23.
 */

public class ArticleManagerAdapter extends RecyclerView.Adapter<ArticleManagerAdapter.ViewHold> implements View.OnClickListener {
    private Context context;
    private List<MyCreateArticle> articleList;
    private DeleteListener deleteListener;

    public ArticleManagerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MyCreateArticle> articleList) {
        this.articleList = articleList;
    }

    public MyCreateArticle getItemData(int position) {
        return articleList.get(position);
    }

    public void addDeleteListener(DeleteListener listener) {
        this.deleteListener = listener;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_article_manager, parent, false);
        return new ViewHold(layout);
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.lly.setTag(position);
        holder.delete.setTag(position);
        MyCreateArticle article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.content.setText(article.getOutline());
        ImageLoaderUtil.displayImage(holder.img, article.getCover());
    }

    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_delete:
                if (deleteListener != null) {
                    deleteListener.delete((Integer) v.getTag());
                }
                break;
            default:
                //跳文章详细
                if (deleteListener != null) {
                    deleteListener.toDetail((Integer) v.getTag());
                }
                break;
        }

    }

    class ViewHold extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private ImageView img;
        private TextView delete;
        private LinearLayout lly;

        ViewHold(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            content = itemView.findViewById(R.id.article_content);
            img = itemView.findViewById(R.id.article_img);
            delete = itemView.findViewById(R.id.article_delete);
            lly = itemView.findViewById(R.id.article_lly);
            lly.setOnClickListener(ArticleManagerAdapter.this);
            delete.setOnClickListener(ArticleManagerAdapter.this);
        }
    }

    public interface DeleteListener {
        void delete(int position);

        void toDetail(int position);
    }
}

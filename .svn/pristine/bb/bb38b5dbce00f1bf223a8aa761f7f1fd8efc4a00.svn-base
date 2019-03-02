package com.a1magway.bgg.data.repository.cache;

import android.content.Context;
import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.DaoMaster;
import com.a1magway.bgg.data.entity.SearchRecord;
import com.a1magway.bgg.data.entity.SearchRecordDao;
import com.a1magway.bgg.data.repository.ISearchRecordsData;

import org.greenrobot.greendao.database.Database;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;

/**
 * 缓存
 * Created by jph on 2017/8/1.
 */
public class CacheSearchRecordsData implements ISearchRecordsData {

    private Context mContext;

    private SearchRecordDao mSearchRecordDao;

    public CacheSearchRecordsData(Context context) {
        mContext = context;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "search_records_db");
        Database db = helper.getWritableDb();
        mSearchRecordDao = new DaoMaster(db).newSession().getSearchRecordDao();
    }


    @Override
    public Observable<List<SearchRecord>> getRecords() {
        return Observable.just(mSearchRecordDao.queryBuilder()
                .limit(20)
                .orderDesc(SearchRecordDao.Properties.CreateTime)
                .list());
    }

    @Override
    public void storeRecord(@Nullable SearchRecord searchRecord) {
        if (searchRecord == null) {
            return;
        }

        mSearchRecordDao.queryBuilder()
                .where(SearchRecordDao.Properties.Key.eq(searchRecord.getKey()))
                .buildDelete().executeDeleteWithoutDetachingEntities();//先删除重复的数据
        searchRecord.setCreateTime(Calendar.getInstance().getTime());
        mSearchRecordDao.save(searchRecord);
    }

    @Override
    public void clearRecords() {
        mSearchRecordDao.deleteAll();
    }
}

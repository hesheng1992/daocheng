package com.a1magway.bgg.common.adapter;

import java.util.List;

/**
 * adapter数据操作
 *
 * @author jph
 *
 */
public interface IAdapterData<T> {

	List<T> getList();

	void addList(List<? extends T> listData);

	void setList(List<? extends T> listData);

	void clearList();

	T getItem(int position);

	void addItem(T item);

	void removeItem(T item);

	void removeItem(int position);
}

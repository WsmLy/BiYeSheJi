package org.dao;

import org.model.TableCommodity;

public interface UploadDao {
	public TableCommodity upLoad(TableCommodity tableCommodity);
	public void delete(TableCommodity tableCommodity);
	public void update(TableCommodity tableCommodity);
}

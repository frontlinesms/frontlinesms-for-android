package com.alxndrsn.android.utils.db;

import java.util.List;

public interface DbAccessObject {
	public void update(DbEntity entity);
	public void save(DbEntity entity);
	public void saveOrUpdate(DbEntity entity);
	/** Delete a specific entity */
	public void delete(DbEntity entity);
	/** Delete any entities which match the supplied example */
	public void deleteByExample(DbEntity example);
	/** @return the database entity with the supplied ID */
	public <T extends DbEntity> T get(Class<T> entityClass, long databaseId);
	/** @return all instances of <code>T</code> from the database whose
	 * values match the non-null fields of <code>example</code> */
	public <T extends DbEntity> List<T> get(T example);
	/** @return all instances of <code>T</code> found in the database. */
	public <T extends DbEntity> List<T> get(Class<T> entityClass);
}

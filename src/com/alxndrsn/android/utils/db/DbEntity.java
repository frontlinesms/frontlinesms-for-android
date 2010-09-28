/**
 * 
 */
package com.alxndrsn.android.utils.db;

/**
 * Interface that must be implemented by persisted objects.
 * @author aga
 */
public interface DbEntity {
	/** Name of the ID column of a database entity */
	String COLUMN_ID = "id";
	/** The database ID of this entity, or <code>null</code> if this entity has
	 * not been persisted yet. */
	Long getDbId();
}

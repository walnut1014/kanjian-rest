package name.walnut.support;

import org.hibernate.cfg.DefaultNamingStrategy;

public class GlobalNamingStrategy extends DefaultNamingStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2542790615909148192L;

	@Override
	public String classToTableName(String className) {
		return StringHelper.beforeCapital(className);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return StringHelper.beforeCapital(propertyName);
	}

	@Override
	public String tableName(String tableName) {
		return super.tableName(tableName);
	}

	@Override
	public String columnName(String columnName) {
		return super.columnName(columnName);
	}

	@Override
	public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		return super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity, associatedEntityTable, propertyName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	@Override
	public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		return super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName);
	}

	@Override
	public String logicalColumnName(String columnName, String propertyName) {
		return super.logicalColumnName(columnName, propertyName);
	}

	@Override
	public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable,
			String propertyName) {
		return super.logicalCollectionTableName(tableName, ownerEntityTable, associatedEntityTable, propertyName);
	}

	@Override
	public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
		return super.logicalCollectionColumnName(columnName, propertyName, referencedColumn);
	}
}

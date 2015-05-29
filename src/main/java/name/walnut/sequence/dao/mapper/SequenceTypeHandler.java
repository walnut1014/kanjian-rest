package name.walnut.sequence.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.EnumTypeHandler;

import com.daren.common.sequence.SequenceType;

public class SequenceTypeHandler extends EnumTypeHandler<SequenceType> {

	public SequenceTypeHandler(Class<SequenceType> type) {
		super(type);
	}

	@Override
	public SequenceType getResult(ResultSet rs, String columnName) throws SQLException {
		if(rs.getString(columnName).equals("long"))
			return SequenceType.LONG;
		if(rs.getString(columnName).equals("int"))
			return SequenceType.INT;
		return super.getResult(rs, columnName);
	}
	
	

}

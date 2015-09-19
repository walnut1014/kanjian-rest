package name.walnut.mapper.sequence;

import name.walnut.sequence.entity.SequenceState;
import org.apache.ibatis.annotations.*;

public interface SequenceStateMapper {
	
	@Select("SELECT seq_name,cache_size,init_val,step,current_val"
			+ " FROM st_seq_state WHERE seq_name = #{param1} FOR UPDATE")
	@Results({
		@Result(property="name", column="seq_name"),
		@Result(property="cacheSize", column="cache_size"),
		@Result(property="initValue", column="init_val"),
		@Result(property="step", column="step"),
		@Result(property="currentValue", column="current_val")
	})
	SequenceState selectByName(String name);
	
	@Update("UPDATE st_seq_state SET current_val=#{currentValue} WHERE seq_name = #{name}")
	void update(@Param("name")String name, @Param("currentValue")long currentValue);
}

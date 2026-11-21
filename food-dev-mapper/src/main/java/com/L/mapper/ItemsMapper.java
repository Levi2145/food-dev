package com.L.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.L.pojo.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ItemsMapper extends BaseMapper<Items> {
 @Select("select * from items order by created_time desc") IPage<Items> selectItemsPage(Page<Items> page);
}

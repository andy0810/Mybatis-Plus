package com.haylion.mp.injector;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 自定义全局操作
 */
public class MyInjector extends AutoSqlInjector {
    /**
     *  扩展inject方法实现全局操作
     * 自定义方法，注入点（子类需重写该方法）
     */
    @Override
    public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass,
                       Class<?> modelClass, TableInfo table) {
        // to do nothing
        //定义一个sql语句
        String sql = "delete from "+table.getTableName();
        //定义一个方法名
        String method = "deleteAll";
        //构造SqlSource对象
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);
        //构造一个删除的MappedStatement
        this.addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}

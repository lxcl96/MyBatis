package com.ly.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mybatis.mapper.EmpMapper;
import com.ly.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author : Ly
 * @Date : 2022/6/11
 * @Description :MyBatis分页插件测试
 */
public class PageInterceptorTest {

    /**
     * select * from table limit index,pageSize;
     *         index:当前页起始序号，（实际index+1）  ***注意不是根据id，而是查询到的所有结果的顺序排序的
     *         pageSize：当前页显示的数量
     *         pageNum：表示当前页的页码
     *         index = (pageNum - 1) * pageSize
     * sql分页使用limit关键字
     * @throws IOException 文件流异常
     */

    @Test
    public void testPageInterceptor() throws IOException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        //分页查询 查询前开启分页功能 参数
        /**
         * pageNUm：当前页码 （输入0就不显示）
         * pageSize：每页显示的数据
         */
        //Page<Object> page = PageHelper.startPage(2, 10);
        PageHelper.startPage(2, 10);
        //System.out.println(page);
        List<Emp> emps = empMapper.selectByExample(null);
        /**
         * PageInfo<Emp> empPageInfo = new PageInfo<>(emps,1);
         * 参数1：查询到的分页数据集合
         * 参数2：前端展示导航页数（1页就是十个数据）
         *
         */
        PageInfo<Emp> empPageInfo = new PageInfo<>(emps,5);
        System.out.println(empPageInfo);

        //emps.forEach(emp -> System.out.println(emp));


    }
}

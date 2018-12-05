package cmo.haylion.test;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.haylion.mp.beans.User;
import com.haylion.mp.mapper.EmployeeMapper;
import com.haylion.mp.beans.Employee;
import com.haylion.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class TestMP {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper =
            ioc.getBean("employeeMapper",EmployeeMapper.class);
    private UserMapper userMapper = ioc.getBean("userMapper",UserMapper.class);
   @Test
   public void testCommonInsert(){
       Employee employee = new Employee();
       employee.setAge(10);
       employee.setEmail("1343qq.com");
       employee.setGender(1);
       employee.setLastName("MP1");
       //插入
       Integer result = employeeMapper.insert(employee);
       System.out.println(result);
   }
    @Test
    public void testCommonUpdate(){
       Employee employee = new Employee();
       employee.setId(5);
       employee.setLastName("MybatisPlus");
       employee.setEmail("andy@com");
       employee.setAge(29);
       employee.setGender(0);
       Integer result = employeeMapper.updateAllColumnById(employee);
      // Integer result = employeeMapper.updateById(employee);
       System.out.println("result:::"+result);
    }
    @Test
    public void testCommonSelect(){
        Employee employee = new Employee();
      //1、通过id查询
      // employee = employeeMapper.selectById(4);
      //2、通过多个列进行查询
     //   employee.setId(4);
     //   employee.setLastName("White1");
     //   Employee result = employee = employeeMapper.selectOne(employee);

        //3、通过多个id进行查询
//        List<Integer> idList = new ArrayList<>();
//        idList.add(1);
//        idList.add(4);
//        idList.add(6);
//        idList.add(16);
//        List<Employee> employeeList = employeeMapper.selectBatchIds(idList);
//        System.out.println(employeeList);
        //4、通过Map封装条件进行查询
//        Map<String,Object> columMap = new HashMap<>();
//        columMap.put("last_name","Tom");
//        columMap.put("age",22);
//        List<Employee> employeeList = employeeMapper.selectByMap(columMap);
//        System.out.println(employeeList);

        //5、分页查询
        List<Employee> employeeList = employeeMapper.selectPage(new Page<>(6,2),null);
        System.out.println(employeeList);
   }

   @Test
   public void testCommonDelete(){
       //1、根据id删除
    //   Integer result = employeeMapper.deleteById(1);
    //   System.out.println(result);

       //2、根据条件删除
//       Map<String,Object> columMap = new HashMap<>();
//        columMap.put("last_name","MP");
//        columMap.put("age",10);
//       Integer result = employeeMapper.deleteByMap(columMap);
//        System.out.println(result);
       //3、批量删除
       List<Integer> idList = new ArrayList<>();
        idList.add(8);
        idList.add(4);
        idList.add(6);
        idList.add(16);
        Integer result = employeeMapper.deleteBatchIds(idList);
       System.out.println(result);

   }

    @Test
    public void testEntityWrapperSelect(){
//        List<Employee> employeeList = employeeMapper.selectPage(new Page<Employee>(1,4),
//                new EntityWrapper<Employee>()
//                        .between("age",18,50)
//                        .eq("gender",1)
//                        .eq("last_name","Tom")
//        );
        List<Employee> employeeList = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender",0)
                .like("last_name","Jerry")
             //   .or()    //或者
                .orNew()
                .like("email","tom")
                .between("age",18,25)
        );
        for (Employee e:employeeList
             ) {
            System.out.println(e);
        }
   }
    @Test
    public void testEntityWrapperUpdate(){
       Employee employee = new Employee();
       employee.setLastName("andy");
       employee.setAge(24);
       employee.setEmail("1344@163.com");
       employee.setGender(1);
       Integer result = employeeMapper.update(employee,new EntityWrapper<Employee>()
                            .eq("id",2)
            );
        System.out.println(result);
    }
    @Test
    public void testEntityWrapperDelete(){
       Integer result = employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("last_name","Tom")
                .eq("age",17)
              // .eq("id",14)
       );
        System.out.println(result);
    }

    /**
     * 代码生成器
     */
    @Test
    public void CodeGenerator(){
     //1、全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) //是否支持AR模式
                .setAuthor("andy") //作者
                .setOutputDir("D:\\Maas\\IDEA\\WORK\\GeneratorMP\\src\\main\\java")    //生成路径
                .setFileOverride(true)   //文件覆盖
                .setIdType(IdType.AUTO)   //主键生成策略
                .setServiceName("%sService") //设置生成的service接口的名字的首字母是否为I
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //2、数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  //设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver") //设置数据库驱动
                .setUrl("jdbc:mysql://localhost:3306/mp?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("haylion123");

        //3、策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)  //开启全局大写命名
                .setDbColumnUnderline(true) //设置表明字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略
                .setTablePrefix("tbl_")    //表名前缀
                .setInclude("tbl_employee");  //生成的表

        //4、包名策略配置
        PackageConfig pgConfig = new PackageConfig();
        pgConfig.setParent("com.haylion.mp")
                .setMapper("mapper")
                .setEntity("beans")
                .setService("service")
                .setController("controller")
                .setXml("mapper");

        //5、整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setDataSource(dsConfig)
                .setGlobalConfig(config)
                .setPackageInfo(pgConfig)
                .setStrategy(stConfig);

        //6、执行
        ag.execute();
    }

    /**
     *  测试SQL执行分析插件
     */
    @Test
    public void testPerformance(){
        employeeMapper.selectPage(new Page<Employee>(2,3),null);
    }
    /**
     *  测试SQL执行分析插件
     */
    @Test
    public void testSQLExplain(){
        employeeMapper.delete(null);
    }

    /**
     * 物理分页插件
     */
    @Test
    public void testPage(){
        List<Employee> employeeList = employeeMapper.selectPage(new Page<>(1,1),null);
        System.out.println(employeeList);
    }

    /**
     *  全局自定义操作
     */
    @Test
    public void testMyInjector(){
       Integer result = employeeMapper.deleteAll();
        System.out.println(result);
    }

    @Test
    public void testTime() throws Exception{
      /*  SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        *//*分钟差*//*
        long time1 = 20180301120044L;
        long time2 = 20180312120033L;
        java.util.Date begin = simpleFormat.parse(time1+"");
        java.util.Date end = simpleFormat.parse(time2+"");
        System.out.println("两个时间之间的分钟差为：" + ((begin.getTime()-end.getTime())/1000));*/
        List<Employee> employeeList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Employee e = new Employee();
            e.setId(i);
            e.setAge(20+i);
            e.setLastName("andy"+i);
            if(i==2 || i==3){
                e.setTime(1000L-i);
            }else {
                e.setTime(1000L+i);
            }
            employeeList.add(e);
        }
        System.out.println("排序之前：：：：：");
        for(Employee employee:employeeList){
            System.out.println(employee);
        }
        System.out.println("排序之后：：：：：");
        employeeList = busStateSort(employeeList);
        for(Employee employee:employeeList){
            System.out.println(employee);
        }
    }

    /**
     *  测试逻辑删除
     */
    @Test
    public void testLogicDelete(){
       Integer result = userMapper.deleteById(1);
        System.out.println(result);
    }
    @Test
    public void testDataSource()throws Exception{
        DataSource ds = ioc.getBean("dataSource",DataSource.class);
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }

    public  List<Employee> busStateSort(List<Employee> busStateList){
        Collections.sort(busStateList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
//                if (o1.getTime() - o2.getTime() > 0){
//                    return -1;
//                }else if (o1.getTime() - o2.getTime() == 0){
//                    return 0;
//                }
//                return 1;
                return (int)(o1.getTime() - o2.getTime());
           }

        });
        return busStateList;
}
}

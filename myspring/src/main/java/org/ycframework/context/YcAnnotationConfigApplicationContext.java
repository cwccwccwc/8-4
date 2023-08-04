package org.ycframework.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class YcAnnotationConfigApplicationContext implements YcApplicationContext{
    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);
    //存每个 将托管的Bean的定义信息
    private Map<String,YcBeanDefinition> beanDefinitionMap=new HashMap<>();
    //存每个 实例化后的bean
    private Map<String,Object> beanMap=new ConcurrentHashMap<>();
    //存系统属性 db.properties
    private Properties pros;

    public YcAnnotationConfigApplicationContext(Class... configClasses) {
        //读取系统的属性 存好
        pros=System.getProperties();
        List<String> toScanPackagePath=new ArrayList<>();
        try {
        for (Class cls:configClasses){
            if (!cls.isAnnotationPresent(YcConfiguration.class)){
                continue;
            }
            String[] basePackages=null;
            //扫描配置类上的 @YcComponentScan注解 读取要扫描的包
            if (cls.isAnnotationPresent(YcComponentScan.class)){
                //如果有 则说明 此 配置类上有 @YccomponentScan  则读取 basePackages
                YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                basePackages = ycComponentScan.basePackages();
                if (basePackages==null||basePackages.length<0){
                    basePackages=new String[1];
                    basePackages[0]=cls.getPackage().getName();
                }
                logger.info(cls.getName()+"类上有@YcComponentScan注解，它要扫描的路径："+basePackages[0]);
            }
            //将这些包中的待遇IOC注解的类  加载到一个Map中
            //开始扫描这些basePackages包下的bean，并加载包装成 BeanDefinition 对象，存到beanDefinition
            recursiveLoadBeanDefinition(basePackages);
        }
        //循环beanDefinitionMap ,创建bean(是否为懒加载，是单例),存到beanMap中
            createBean();
        //循环所有托管的beanMap中的bean，看属性和方法上是否有@Autowired @Resource @Value...考虑DI
            doDi();
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /*
    * 开始扫描这些basePackages包下的bean，并加载包装成 BeanDefinition 对象，存到beanDefinition
    * */
    private void doDi() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //循环的是 beanMap 这是托管Bean
        for (Map.Entry<String,Object> entry:beanMap.entrySet()){
            String beanId = entry.getKey();
            Object beanObj = entry.getValue();
            //情况一:属性上有 @YcResource 注解的情况
            //情况二:方法上有@YcResource注解的情况
            //情况三:构造方法上有@YcResource注解的情况
            Field[] fields = beanObj.getClass().getDeclaredFields();
            for (Field field:fields){
                if (field.isAnnotationPresent(YcReSource.class)){
                    YcReSource ycResource = field.getAnnotation(YcReSource.class);
                    String toDiBeanId = ycResource.name();
                    //从 beanMap中找 是否singleton 是否lazy
                    Object obj=getToDiBean(toDiBeanId);
                    //注入
                    field.setAccessible(true);//因为属性是private的，所以要将它 accessible设为ture
                    field.set(beanObj,obj);// userBizImpl.userDao=userDaoImpl
                    System.out.println();
                }
            }
        }
    }

    private Object getToDiBean(String toDiBeanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (beanMap.containsKey(toDiBeanId)){
            return beanMap.get(toDiBeanId);
        }
        //判断 beanMap中没有此bean是否因为lazy
        if (!beanDefinitionMap.containsKey(toDiBeanId)){
            throw new RuntimeException("spring容器中没有加载此class："+toDiBeanId);
        }
        YcBeanDefinition bd=beanDefinitionMap.get(toDiBeanId);
        if (bd.isLazy()){
            //是因为懒 所有没有托管
            //既然没加载，就强制加载起来
            String classpath=bd.getClassInfo();
            Object beanObj = Class.forName(classpath).newInstance();
            beanMap.put(toDiBeanId,beanObj);
            return beanObj;
        }
        return null;
    }

    private void createBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Map.Entry<String,YcBeanDefinition> entry:beanDefinitionMap.entrySet()){
            String beanId=entry.getKey();
            YcBeanDefinition ydb = entry.getValue();
            if (!ydb.isLazy()&&!ydb.getScope().equalsIgnoreCase("prototype")){
                String classInfo = ydb.getClassInfo();
                Object obj = Class.forName(classInfo).newInstance();
                beanMap.put(beanId,obj);
                logger.trace("spring容器托管了："+beanId+"=>"+classInfo);
            }
        }
    }

    private void recursiveLoadBeanDefinition(String[] basePackages){
        for (String basePackage:basePackages){
            //将 包名中的  .  替换成 路径中的  /
            String packagePath = basePackage.replaceAll("\\.", "/");
            //target/classes     /com/yc
            //Enumeration 集合 URL:每个资源的路径
            Enumeration<URL> files=null;
            try{
                files=Thread.currentThread().getContextClassLoader().getResources(packagePath);
                //循环这个files    看是否是我要加载的资源
                while (files.hasMoreElements()){
                    URL url = files.nextElement();
                    logger.trace("当前正在递归加载："+url.getFile());
                    //查找此包下的类
                    findPackageClasses(url.getFile(),basePackage);
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private void findPackageClasses(String packagePath, String basePackage) {
        //路径异常的处理  前面有/ 则去掉它
        if (basePackage.startsWith("/")){
            basePackage=basePackage.substring(1);
        }
        //取这个路径下所有的字节码文件(因为目录下有可能有其他资源)
        File file = new File(packagePath);
        //只取后缀名为 .class的字节码
        File[] classFiles = file.listFiles((pathname) -> {
            if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
                return true;
            }
            return false;
        });
        //循环此classFiles
        if(classFiles==null||classFiles.length<0){
            return;
        }
        for (File cf:classFiles){
            if (cf.isDirectory()){
                //继续递归
                logger.trace("递归："+cf.getAbsolutePath()+"它对应的包名为："+(basePackage+"."+cf.getName()));
                findPackageClasses(cf.getAbsolutePath(),basePackage+"."+cf.getName());
            }else {
                //是class文件 则取出文件 判断此文件对应的class中是否有@Component注解
                URLClassLoader uc = new URLClassLoader(new URL[]{});
                //
                Class cls=null;
                try {
                    cls = uc.loadClass(basePackage + "." + cf.getName().replaceAll(".class", ""));
                    //TODO：可以支持 YcComponent子注解
                    if (cls.isAnnotationPresent(YcComponent.class)
                      ||cls.isAnnotationPresent(YcConfiguration.class)
                      ||cls.isAnnotationPresent(YcController.class)
                      ||cls.isAnnotationPresent(YcRepository.class)
                      ||cls.isAnnotationPresent(YcService.class)){
                        logger.info("找到一个待托管的类："+cls.getName());
                        //TODO:包装成BeanDefinition
                        YcBeanDefinition bd = new YcBeanDefinition();
                        if (cls.isAnnotationPresent(YcLazy.class)){
                            bd.setLazy(true);
                        }
                        if (cls.isAnnotationPresent(YcScope.class)){
                            YcScope ycScope = (YcScope) cls.getAnnotation(YcScope.class);
                            String scope = ycScope.value();
                            bd.setScope(scope);
                        }
                        // classInfo中保存这个待托管的类的包路径  com.yc.ycSpring.UserDaoImpl
                        //  cls.newInstance()
                        bd.setClassInfo(basePackage+"."+cf.getName().replaceAll(".class",""));
                        //存到 beanDefinitionMap中"beanId"->bEANdEFINITION对象
                        String beanId=getBeanId(cls);
                        this.beanDefinitionMap.put(beanId,bd);
                    }
                }catch (ClassNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    /*
    * 获取一个待托管类的 beanId
    * 1.@Compent  直接用类名（首字母小写）
    * 2.@Compent("beanId")
    *
    * 另外 @Configuration用全路径名做 beanId
    * */
    private String getBeanId(Class cls) {
        YcComponent ycComponent= (YcComponent) cls.getAnnotation(YcComponent.class);
        YcController ycController= (YcController) cls.getAnnotation(YcController.class);
        YcService ycService= (YcService) cls.getAnnotation(YcService.class);
        YcRepository ycRepository= (YcRepository) cls.getAnnotation(YcRepository.class);
        YcConfiguration ycConfiguration= (YcConfiguration) cls.getAnnotation(YcConfiguration.class);

        if (ycConfiguration!=null){
            return cls.getName();//全路径名
        }
        String beanId=null;
        if (ycComponent!=null){
            beanId=ycComponent.value();
        }else if (ycController!=null){
            beanId=ycController.value();
        }else if (ycService!=null){
            beanId=ycService.value();
        }else if (ycRepository!=null){
            beanId=ycRepository.value();
        }
        if (beanId==null||"".equalsIgnoreCase(beanId)){
            String simpleName = cls.getSimpleName();
            beanId=simpleName.substring(0,1).toLowerCase()+simpleName.substring(1);
        }
        return beanId;
    }

    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition db = this.beanDefinitionMap.get(beanid);
        if (db==null){
            throw new RuntimeException("容器中没有加载此bean");
        }
        String scope = db.getScope();
        if ("prototype".equalsIgnoreCase(scope)) {
        //原型模式  每次getBean时创建
            Object obj=null;
            try {
                obj = Class.forName(db.getClassInfo()).newInstance();
                return obj;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.beanMap.containsKey(beanid)){
            return this.beanMap.get(beanid);
        }
        if (db.isLazy()){
            //懒加载 则加载完后要put到map中
            Object obj=null;
            try {
                obj = Class.forName(db.getClassInfo()).newInstance();
                this.beanMap.put(beanid,obj);
                return obj;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}

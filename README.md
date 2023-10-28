# alumnicyclenew

# How to Run
1. You need to set up mysql(version 8.0.0 or above), and set up the sql file that we provided to set up the tables and data.
2. Change your absolute path of the file base on your mysql dataset, including username and password, and your image files(img).
3. Load all the dependency in Maven.

# Used Dependency
<parent>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-parent</artifactId>
 <version>2.7.15</version>
 <relativePath/> 
</parent>

<java.version>1.8</java.version>

<groupId>com.github.xiaoymin</groupId>
<artifactId>knife4j-openapi2-spring-boot-starter</artifactId>
<version>4.0.0</version>

<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>

<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web-services</artifactId>

<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>

<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-configuration-processor</artifactId>

<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>

<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>

<groupId>com.baomidou</groupId>
<artifactId>mybatis-plus-boot-starter</artifactId>
<version>3.4.2</version>

<groupId>com.alibaba</groupId>
<artifactId>druid-spring-boot-starter</artifactId>
<version>1.2.16</version>

<groupId>com.alibaba</groupId>
<artifactId>fastjson</artifactId>
<version>2.0.25</version>

<groupId>commons-lang</groupId>
<artifactId>commons-lang</artifactId>
<version>2.6</version>

<groupId>org.mybatis</groupId>
<artifactId>mybatis-spring</artifactId>
<version>2.0.5</version>

<groupId>com.aliyun</groupId>
<artifactId>aliyun-java-sdk-core</artifactId>
<version>3.2.6</version>

<groupId>com.aliyun</groupId>
<artifactId>aliyun-java-sdk-dysmsapi</artifactId>
<version>2.1.0</version>

<groupId>junit</groupId>
<artifactId>junit</artifactId>

# Functionality
1. Website security
2. Present information in pagination
3. Global search 
4. Navigation to useful outside resources 
5. Filter posts based on their categories 
6. Create and Register for LifePost 
7. Present weather data  
8. Interaction and make comments in the AcademicPost
9. Shopping in the UniTrade sector
10. Shopping Cart modification and checkout
11. User page profile settings 
12. User page post modification 
13. User management 
14. Admin management 
15. Content management

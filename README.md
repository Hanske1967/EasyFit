EasyFit
=======

Track what you eat and stay fit

#Setup
On Linux Ubuntu / Mint:

1. Install tomcat8 and Mysql:

    `apt install tomcat8`
    `apt install mysql-server`

    Tomcat8 application directory is:

    `/var/lib/tomcat8`

2. Download mysql jdbc driver from https://dev.mysql.com/downloads/connector/j/5.1.html

    Copy driver on `/usr/share/java`and create a symlink to this jar in in `/usr/share/tomcat8 lib`.

3. Add jdbc connector in `/var/lib/tomcat8/conf/context.xml`


  <Resource name="jdbc/easyfit" auth="Container" type="javax.sql.DataSource"
               maxTotal="20" maxIdle="3" maxWaitMillis="10000"
               username="root" password="Tomytomy" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/easyfit"/>


4. Deploy easyfit.war file in  `/var/lib/tomcat8/webapps`





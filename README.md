# ServletWeb
jsp+jdbc+servlet

![CRUD](https://github.com/20160805xcy/staticSource/blob/master/ServletWeb/image/CRUD.png?raw=true)

使用最最最基础的原始技能造房子.

---

使用一个Controller来完成所有的请求,这个类如果操作过多的话,后期简直就会爆炸.

jdbc使用最基础的获取连接,取得Statement对象,使用Statement执行query,或者更新删除操作.这里简单的封装了一下JdbcUtil工具类


``

    public class JdbcUtil {
        private static String driverClass;
        private static String url;
        private static String user;
        private static String password;
    
        static {
            try {
                ClassLoader cl = JdbcUtil.class.getClassLoader();
                InputStream in = cl.getResourceAsStream("db.properties");
                Properties props = new Properties();
                props.load(in);
                driverClass = props.getProperty("driverClass");
                url = props.getProperty("url");
                user = props.getProperty("user");
                password = props.getProperty("password");
    
                Class.forName(driverClass);
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    
        /**
         * 获取连接
         * @return
         * @throws Exception
         */
        public static Connection getConnection() throws Exception {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
    
        /**
         * 资源释放
         * @param rs
         * @param stmt
         * @param conn
         */
        public static void release(ResultSet rs, Statement stmt, Connection conn) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
    }

``



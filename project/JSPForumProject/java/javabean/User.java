
/**
 * 2016.6.22  注册功能，处理用户信息的JavaBean
 * @author Liu-shuwei
 */
package javabean;

public class User{
   private String username,userpassword, userpasswordtwo,
                  usersex,userbirthday,
                  useraddcpc,useremail,userphone,
                  useraddr,userhobbys;
   
  
    /*
        所有属性的getter和setter方法
   */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpasswordtwo() {
        return userpasswordtwo;
    }

    public void setUserpasswordtwo(String userpasswordtwo) {
        this.userpasswordtwo = userpasswordtwo;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getUseraddcpc() {
        return useraddcpc;
    }

    public void setUseraddcpc(String useraddcpc) {
        this.useraddcpc = useraddcpc;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr;
    }

    public String getUserhobbys() {
        return userhobbys;
    }

    public void setUserhobbys(String userhobbys) {
        this.userhobbys = userhobbys;
    }
   
   
}

package cn.zcbigdata.mybits_demo.entity;

public class Teacher {
    private Integer id;
    private String username;
    private String password;
    private String manjor;
    private String class_num;
    private String info_id;
    private String info_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManjor() {
        return manjor;
    }

    public void setManjor(String manjor) {
        this.manjor = manjor;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getInfo_status() {
        return info_status;
    }

    public void setInfo_status(String info_status) {
        this.info_status = info_status;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", manjor='" + manjor + '\'' +
                ", class_num='" + class_num + '\'' +
                ", info_id='" + info_id + '\'' +
                ", info_status='" + info_status + '\'' +
                '}';
    }
}

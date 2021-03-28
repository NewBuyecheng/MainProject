package cn.zcbigdata.mybits_demo.entity;

public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String class_name;
    private String major;
    private String paper_address;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPaper_address(String paper_address) {
        this.paper_address = paper_address;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getMajor() {
        return major;
    }

    public String getPaper_address() {
        return paper_address;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", major='" + major + '\'' +
                ", paper_address='" + paper_address + '\'' +
                '}';
    }
}

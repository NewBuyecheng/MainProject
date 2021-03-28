package cn.zcbigdata.mybits_demo.entity;

public class Student {
   private int id;
   private String account;
   private String password;
   private String name;
   private int paperId;
    private int teacherId;
    private String open;
    private boolean openpass;
    private String opencomment;
    private String mid;
    private boolean midpass;
    private String midcomment;
    private String last;
    private boolean lastpass;
    private String lastcomment;
    private int grade;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", paperId=" + paperId +
                ", teacherId=" + teacherId +
                ", open='" + open + '\'' +
                ", openpass=" + openpass +
                ", opencomment='" + opencomment + '\'' +
                ", mid='" + mid + '\'' +
                ", midpass=" + midpass +
                ", midcomment='" + midcomment + '\'' +
                ", last='" + last + '\'' +
                ", lastpass=" + lastpass +
                ", lastcomment='" + lastcomment + '\'' +
                ", grade=" + grade +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public boolean getOpenpass() {
        return openpass;
    }

    public void setOpenpass(boolean openpass) {
        this.openpass = openpass;
    }

    public String getOpencomment() {
        return opencomment;
    }

    public void setOpencomment(String opencomment) {
        this.opencomment = opencomment;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public boolean getMidpass() {
        return midpass;
    }

    public void setMidpass(boolean midpass) {
        this.midpass = midpass;
    }

    public String getMidcomment() {
        return midcomment;
    }

    public void setMidcomment(String midcomment) {
        this.midcomment = midcomment;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public boolean getLastpass() {
        return lastpass;
    }

    public void setLastpass(boolean lastpass) {
        this.lastpass = lastpass;
    }

    public String getLastcomment() {
        return lastcomment;
    }

    public void setLastcomment(String lastcomment) {
        this.lastcomment = lastcomment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}

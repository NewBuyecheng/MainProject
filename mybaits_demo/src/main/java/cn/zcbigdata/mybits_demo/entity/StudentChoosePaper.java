package cn.zcbigdata.mybits_demo.entity;

public class StudentChoosePaper {
    private int id;
    private String subject;
    private int teacherId;

    @Override
    public String toString() {
        return "StudentChoosePaper{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

}

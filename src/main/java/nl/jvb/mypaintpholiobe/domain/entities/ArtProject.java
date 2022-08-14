package nl.jvb.mypaintpholiobe.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ArtProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String mediumType;
    private Date dateStart;
    private Date dateEnd;
    private String inspiration;
    private int height;
    private int width;
    private String description;
    private String subject;
    private Boolean isFinished = false;
    private Long studentId;

    @OneToMany(mappedBy = "artProject", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "art_project_id")
    Set<FileUploadResponse> fileUploadResponses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student")
    private Student student;
    //student not null??

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMediumType() {
        return mediumType;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getInspiration() {
        return inspiration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Student getStudent() {
        return student;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Set<FileUploadResponse> getFileUploadResponses() {
        return fileUploadResponses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setFileUploadResponses(Set<FileUploadResponse> fileUploadResponses) {
        this.fileUploadResponses = fileUploadResponses;
    }
}

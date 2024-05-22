package co.edu.uptc.model;

import java.time.LocalDate;

public class Report {
    private String reportId;
    private User user;
    private LocalDate reportDate;
    private String content;

    public Report(String reportId, User user, LocalDate reportDate, String content) {
        this.reportId = reportId;
        this.user = user;
        this.reportDate = reportDate;
        this.content = content;
    }

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", user=" + user +
                ", reportDate=" + reportDate +
                ", content='" + content + '\'' +
                '}';
    }

    // Additional functionality
    public void appendContent(String additionalContent) {
        this.content += additionalContent;
    }

    public void clearContent() {
        this.content = "";
    }
}

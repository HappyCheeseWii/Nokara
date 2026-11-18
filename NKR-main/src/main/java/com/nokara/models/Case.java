
package com.nokara.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "caseItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DocumentInfo> documents = new ArrayList<>();

    public Case() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<DocumentInfo> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentInfo> documents) {
        this.documents = documents;
    }

    @Entity
    @Table(name = "documents")
    public static class DocumentInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "case_id", nullable = false)
        private Case caseItem;

        @Column(name = "original_file_name", nullable = false, length = 255)
        private String originalFileName;

        @Column(name = "custom_title", nullable = false, length = 150)
        private String customTitle;

        @Column(name = "file_path", nullable = false, length = 255)
        private String filePath;

        @Column(name = "file_description", columnDefinition = "TEXT")
        private String fileDescription;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at", insertable = false, updatable = false)
        private Date createdAt;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_at", insertable = false, updatable = false)
        private Date updatedAt;

        public DocumentInfo() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Case getCaseItem() {
            return caseItem;
        }

        public void setCaseItem(Case caseItem) {
            this.caseItem = caseItem;
        }

        public String getOriginalFileName() {
            return originalFileName;
        }

        public void setOriginalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
        }

        public String getCustomTitle() {
            return customTitle;
        }

        public void setCustomTitle(String customTitle) {
            this.customTitle = customTitle;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileDescription() {
            return fileDescription;
        }

        public void setFileDescription(String fileDescription) {
            this.fileDescription = fileDescription;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }
    }
}

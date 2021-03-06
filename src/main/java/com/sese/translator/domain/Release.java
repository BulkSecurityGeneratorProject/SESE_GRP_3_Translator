package com.sese.translator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Release.
 */
@Entity
@Table(name = "release")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited
public class Release extends AbstractAuditingEntity implements Serializable {

    public static final String DEFAULT_TAG = "no release";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "version_tag", nullable = false)
    private String versionTag;

    @Column(name = "due_date")
    private ZonedDateTime dueDate;

    @OneToMany(mappedBy = "release")
    @JsonIgnore
  //  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Cascade(CascadeType.DELETE)
    @NotAudited
    private Set<Definition> definitions = new HashSet<>();

    @NotNull
    @ManyToOne
    @NotAudited
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Release description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersionTag() {
        return versionTag;
    }

    public Release versionTag(String versionTag) {
        this.versionTag = versionTag;
        return this;
    }

    public void setVersionTag(String versionTag) {
        this.versionTag = versionTag;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public Release dueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Set<Definition> getDefinitions() {
        return definitions;
    }

    public Release definitions(Set<Definition> definitions) {
        this.definitions = definitions;
        return this;
    }

    public Release addDefinitions(Definition definition) {
        definitions.add(definition);
        definition.setRelease(this);
        return this;
    }

    public Release removeDefinitions(Definition definition) {
        definitions.remove(definition);
        definition.setRelease(null);
        return this;
    }

    public void setDefinitions(Set<Definition> definitions) {
        this.definitions = definitions;
    }

    public Project getProject() {
        return project;
    }

    public Release project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Release release = (Release) o;
        if (release.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, release.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Release{" +
            "id=" + id +
            ", description='" + description + "'" +
            ", versionTag='" + versionTag + "'" +
            ", dueDate='" + dueDate + "'" +
            '}';
    }
}

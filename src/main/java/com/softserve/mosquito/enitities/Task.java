package com.softserve.mosquito.enitities;

import java.util.List;

public class Task {
	private Long id;
    private Long parentId;
    private Long ownerId;
    private Long workerId;
    private String name;
    private Status status = Status.TODO;
    private Priority priority = Priority.MIDDLE;
    private Estimation estimation;
    private List<Comment> comments;
    
    public Task() {}

    /** Just short constructor */
    public Task(Long id, String name) {
    	super();
    	this.id = id;
    	this.name = name;
    }

	/** DB Insert constructor */
	public Task(Long parentId, Long ownerId, Long workerId, String name, Status status, Priority priority,
			Estimation estimation) {
		super();
		this.parentId = parentId;
		this.ownerId = ownerId;
		this.workerId = workerId;
		this.name = name;
		this.status = status;
		this.priority = priority;
		this.estimation = estimation;
	}

	public Task(Long id, Long parentId, Long ownerId, Long workerId, String name, Status status, Priority priority,
			Estimation estimation, List<Comment> comments) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.ownerId = ownerId;
		this.workerId = workerId;
		this.name = name;
		this.status = status;
		this.priority = priority;
		this.estimation = estimation;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Estimation getEstimation() {
		return estimation;
	}

	public void setEstimation(Estimation estimation) {
		this.estimation = estimation;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", parentId=" + parentId + ", ownerId=" + ownerId + ", workerId=" + workerId
				+ ", name=" + name + ", status=" + status + ", priority=" + priority + ", estimation=" + estimation
				+ ", comments=" + comments + "]";
	}
    
    
}

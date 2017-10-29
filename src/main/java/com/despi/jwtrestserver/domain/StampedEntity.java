package com.despi.jwtrestserver.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@MappedSuperclass
abstract public class StampedEntity {
	private ZonedDateTime createdDate;
	private ZonedDateTime modifiedDate;

	@PrePersist
	public void autoCreatedTracking() {
		createdDate = modifiedDate = ZonedDateTime.now();
	}

	@PreUpdate
	public void autoModifiedTracking() {
		modifiedDate = ZonedDateTime.now();
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ZonedDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(ZonedDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}

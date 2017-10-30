package com.despi.jwtrestserver.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@MappedSuperclass
abstract public class StampedEntity {
	public static final ZoneOffset DEFAULT_ZONE_ID = ZoneOffset.UTC;

	// @CreationTimestamp and @UpdateTimestamp are VM timezone dependent, prefer this agnostic approach
	protected Timestamp createdDate;
	protected Timestamp modifiedDate;

	@PrePersist
	public void autoCreatedTracking() {
		createdDate = modifiedDate = Timestamp.valueOf(LocalDateTime.now(DEFAULT_ZONE_ID));
	}

	@PreUpdate
	public void autoModifiedTracking() {
		modifiedDate = Timestamp.valueOf(LocalDateTime.now(DEFAULT_ZONE_ID));
	}

	public ZonedDateTime getCreatedTS() {
		return createdDate != null ? ZonedDateTime.of(createdDate.toLocalDateTime(), DEFAULT_ZONE_ID) : null;
	}

	public ZonedDateTime getModifiedTS() {
		return modifiedDate != null ? ZonedDateTime.of(modifiedDate.toLocalDateTime(), DEFAULT_ZONE_ID) : null;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}

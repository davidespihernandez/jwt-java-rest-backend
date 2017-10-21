package com.despi.jwtrestserver.domain;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class UserInfo {

	public static final String DEFAULT_USER_TIMEZONE = "CET";

	public enum TITLE {
		MR("Mr"),
		MRS("Mrs"),
		MS("Ms");

		private String description;

		public String getDescription() {
			return description;
		}

		TITLE(String description) {
			this.description = description;
		}
	}

	public UserInfo() {
	}

	private String fullAddress;

	private String timezone;

	private TITLE title;

	private String phone;
	private String mobile;

	//getters and setters

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public TITLE getTitle() {
		return title;
	}

	public void setTitle(TITLE title) {
		this.title = title;
	}

	public String getTimezone() {
		if (this.timezone == null) {
			return DEFAULT_USER_TIMEZONE;
		}
		return timezone;
	}

	public void setTimezone(String timezone) {
		List<String> allZones = Arrays.asList(TimeZone.getAvailableIDs());
		if (!allZones.contains(timezone)) {
			throw new IllegalArgumentException("Unknown timezone: " + timezone);
		}
		this.timezone = timezone;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserInfo)) return false;

		UserInfo that = (UserInfo) o;

		if (getFullAddress() != null ? !getFullAddress().equals(that.getFullAddress()) : that.getFullAddress() != null)
			return false;
		if (getTimezone() != null ? !getTimezone().equals(that.getTimezone()) : that.getTimezone() != null)
			return false;
		if (getTitle() != that.getTitle()) return false;
		if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) return false;
		return getMobile() != null ? getMobile().equals(that.getMobile()) : that.getMobile() == null;
	}

	@Override
	public int hashCode() {
		int result = getFullAddress() != null ? getFullAddress().hashCode() : 0;
		result = 31 * result + (getTimezone() != null ? getTimezone().hashCode() : 0);
		result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
		result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
		result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"fullAddress='" + fullAddress + '\'' +
				", timezone='" + timezone + '\'' +
				", title=" + title +
				", phone='" + phone + '\'' +
				", mobile='" + mobile + '\'' +
				'}';
	}
}

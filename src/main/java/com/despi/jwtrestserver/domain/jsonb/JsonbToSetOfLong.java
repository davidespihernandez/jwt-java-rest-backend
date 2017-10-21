package com.despi.jwtrestserver.domain.jsonb;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JsonbToSetOfLong implements UserType {
	@Override
	public int[] sqlTypes() {
		return new int[] {Types.OTHER};
	}

	@Override
	public Class returnedClass() {
		return (new HashSet<Long>()).getClass();
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return false;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return 0;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		String json = rs.getString(names[0]);
		if (json == null || json.trim().length() == 0) {
			return null;
		} else {
			Long[] numbers = JacksonUtil.fromString(json, (new Long[0]).getClass());
			return new HashSet<Long>(Arrays.asList(numbers));
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else {
			st.setBytes(index, JacksonUtil.toString(value).getBytes());
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		} else {
			return new HashSet((Set<Long>)value);
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return null;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return null;
	}
}

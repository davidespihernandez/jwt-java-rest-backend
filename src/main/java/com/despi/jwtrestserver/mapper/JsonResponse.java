package com.despi.jwtrestserver.mapper;

import com.despi.jwtrestserver.dto.Dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonResponse {
	private HashMap<String, List<Dto>> contents = new HashMap<>();

	public static HashMap<String, Object> toJsonMap(Dto dto) {
		return new JsonResponse(dto).getProcessedContents();
	}

	public static HashMap<String, Object> toJsonMap(Dto... dtoList) {
		return new JsonResponse(dtoList).getProcessedContents();
	}

	public static HashMap<String, Object> toJsonMap(List<Dto> dtoList) {
		return new JsonResponse(dtoList).getProcessedContents();
	}

	public JsonResponse() {
	}

	public JsonResponse(Dto dto) {
		addDto(dto);
	}

	public JsonResponse(Dto... dtoList) {
		addDtos(dtoList);
	}

	public JsonResponse(Collection<Dto> dtoList) {
		addDtos(dtoList);
	}

	public void addDto(Dto dto) {
		List<Dto> dtoList;
		if (contents.get(dto.getDtoObjectName()) != null) {
			dtoList = contents.get(dto.getDtoObjectName());
		} else {
			dtoList = new ArrayList<>();
		}
		dtoList.add(dto);
		contents.put(dto.getDtoObjectName(), dtoList);
	}

	public void addDtos(Dto... dtoList) {
		for (Dto dto : dtoList) {
			addDto(dto);
		}
	}

	public void addDtos(Collection<Dto> dtoList) {
		for (Dto dto : dtoList) {
			addDto(dto);
		}
	}

	public HashMap<String, List<Dto>> getContents() {
		return contents;
	}

	public void setContents(HashMap<String, List<Dto>> contents) {
		this.contents = contents;
	}

	public HashMap<String, Object> getProcessedContents() {
		//if only one element on each list, then return the element, not a list
		HashMap<String, Object> processedContents = new HashMap<>();
		for (Map.Entry<String, List<Dto>> entry : contents.entrySet()) {
			if (entry.getValue().size() == 1) {
				processedContents.put(entry.getKey(), entry.getValue().get(0));
			} else {
				processedContents.put(entry.getKey(), entry.getValue());
			}
		}
		return processedContents;
	}
}

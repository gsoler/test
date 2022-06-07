package com.king.constants;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.king.dto.KingData;

public class KingDataComparator {

	private static Comparator<KingData> idComparator = new Comparator<KingData>() {
		@Override
		public int compare(KingData data1, KingData data2) {
			int compareResult;
			if (data1.getId() == null && data2.getId() == null) {
				compareResult = 0;
			} else if (data1.getId() == null) {
				compareResult = -1;
			} else if (data2.getId() == null) {
				compareResult = 1;
			} else {
				compareResult = data1.getId().compareTo(data2.getId());
			}
			return compareResult;
		}
	};

	private static Comparator<KingData> nameComparator = new Comparator<KingData>() {
		@Override
		public int compare(KingData data1, KingData data2) {
			int compareResult;
			if (data1.getName() == null && data2.getName() == null) {
				compareResult = 0;
			} else if (data1.getName() == null) {
				compareResult = -1;
			} else if (data2.getName() == null) {
				compareResult = 1;
			} else {
				compareResult = data1.getName().compareTo(data2.getName());
			}
			return compareResult;
		}
	};

	private static Comparator<KingData> createdOnComparator = new Comparator<KingData>() {
		@Override
		public int compare(KingData data1, KingData data2) {
			int compareResult;
			if (data1.getCreatedOn() == null && data2.getCreatedOn() == null) {
				compareResult = 0;
			} else if (data1.getCreatedOn() == null) {
				compareResult = -1;
			} else if (data2.getCreatedOn() == null) {
				compareResult = 1;
			} else {
				compareResult = data1.getCreatedOn().compareTo(data2.getCreatedOn());
			}
			return compareResult;
		}
	};

	public static Map<String, Comparator<KingData>> FIELD_COMPARATOR = new LinkedHashMap<String, Comparator<KingData>>() {
		private static final long serialVersionUID = 1L;
		{
			put("id", idComparator);
			put("name", nameComparator);
			put("createdOn", createdOnComparator);
		}
	};

}

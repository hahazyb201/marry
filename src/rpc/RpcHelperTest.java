package rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import entity.Item;
import entity.Item.ItemBuilder;

public class RpcHelperTest {

	@Test
	public void testGetJSONArray() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		ItemBuilder oneb = new ItemBuilder();
		oneb.setItemId("one");
		oneb.setRating(5.0);
		oneb.setCategories(category);
		Item one=oneb.build();
		ItemBuilder twob = new ItemBuilder();
		twob.setItemId("two");
		twob.setRating(5.0);
		twob.setCategories(category);
		Item two=twob.build();
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(one);
		listItem.add(two);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(one.toJsonObject());
		jsonArray.put(two.toJsonObject());
		
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}
	@Test
	public void testGetJSONArrayCornerCases() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		
		List<Item> listItem = new ArrayList<Item>();
		JSONArray jsonArray = new JSONArray();
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);

		ItemBuilder oneb = new ItemBuilder();
		oneb.setItemId("one");
		oneb.setRating(5.0);
		oneb.setCategories(category);
		Item one=oneb.build();
		ItemBuilder twob = new ItemBuilder();
		twob.setItemId("two");
		twob.setRating(5.0);
		twob.setCategories(category);
		Item two=twob.build();
		listItem.add(one);
		listItem.add(two);
		
		jsonArray.put(one.toJsonObject());
		jsonArray.put(two.toJsonObject());	
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
		
		Item empty = new ItemBuilder().build();
		listItem.add(empty);
		jsonArray.put(empty.toJsonObject());
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}

}


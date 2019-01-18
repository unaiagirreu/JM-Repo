package springmvc.beans;

import java.util.List;

import edu.mondragon.springmvc.entity.Item;

public class ItemList {
	List<Item> itemList;
	
	public ItemList(List<Item> itemList) {
		this.itemList=itemList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}


	

}

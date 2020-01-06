package com.syntax;

import com.syntax.dto.Item;
import com.syntax.util.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails {

    public static List<Item> getItems() {
        String itemsConfig = ConfigProperties.INSTANCE.getItemsConfig();

        List itemList = new ArrayList<Item>();
        Item item;

        if (itemsConfig.length() > 0) {
            String[] items = itemsConfig.split("~");

            int count = 0;
            for (String i : items) {
                item = new Item();
                ++count;
                item.setItemName(i);
                item.setItemPosition(count);
                itemList.add(item);
            }
        }

        return itemList;
    }
}

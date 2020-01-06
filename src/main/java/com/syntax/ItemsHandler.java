/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntax;

import com.syntax.util.ConfigProperties;
import com.google.gson.JsonObject;
import java.util.List;
import com.syntax.dto.Item;

/**
 *
 * @author Olugbenga.Falodun
 */
public class ItemsHandler {

    public static void main(String[] args) {
        System.out.println("App running ... :: ");
        JsonObject json = new JsonObject();

        json.addProperty("pageSize", 4);
        json.addProperty("pageNumber", 1);

        ItemsHandler handler = new ItemsHandler();
        System.out.println(handler.navigate(json));
    }

    public String navigate(JsonObject req) {
        int pageSize = getPageSize(req);
        int pageNumber = getPageNumber(req);

        List list = ItemDetails.getItems();

        if (pageNumber == 0) {
            pageNumber = 1;
        }
        int pageLength = pageSize * pageNumber;
        if (pageLength > list.size()) {
            int pageCount = list.size() / pageSize;
            int overflow = list.size() % pageSize;
            pageNumber = overflow > 0 ? pageCount + 1 : pageCount;
        }

        String response = createMenu(pageNumber, pageSize);

//        java.util.logging.Logger.getLogger(ItemsHandler.class.getName()).log(Level.INFO, response, "Page Navigation");

        return response;
    }

    private int getPageSize(JsonObject req) {
        int pageSize = req.get("pageSize") == null ? 0 : Integer.parseInt(req.get("pageSize").getAsString());
        pageSize = pageSize == 0 ? ConfigProperties.INSTANCE.getPageSize() : pageSize;

        return pageSize;
    }

    private int getPageNumber(JsonObject req) {
        int pageNumber = req.get("pageNumber") == null ? 0 : Integer.parseInt(req.get("pageNumber").getAsString());

        return pageNumber;
    }

    public static String createMenu(int page, int size) {
        List list = ItemDetails.getItems();

        int start = page <= 0 ? 1 : page * size - size;
        int stop = page * size > list.size() ? list.size() : page * size;
        StringBuilder items = new StringBuilder();

        int count = 0;
        for (int i = start; i < stop; ++i) {
            count += 1;
            Item item = (Item) list.get(i);
            items.append(count).append(". ").append(item.getItemName()).append(" ~");
        }


        String itemsStr = items.toString();
        itemsStr = itemsStr.substring(0,itemsStr.length()-1); // remove trailing last string i.e ~

        String response = itemsStr;
        response += canShowNext(page, size) ? " ~" + (++count) + ". Next " : "";
        response += canShowPrevious(page, size) ? " ~" + (++count) + ". Previous " : "";

        return response;
    }

    public static boolean canShowNext(int page, int size) {
        List list = ItemDetails.getItems();
        int stop = page * size > list.size() ? list.size() : page * size;
        return list.size() - stop > 0;
    }

    public static boolean canShowPrevious(int page, int size) {
        List list = ItemDetails.getItems();
        int stop = page * size > list.size() ? list.size() : page * size;
        return stop > size;
    }

}

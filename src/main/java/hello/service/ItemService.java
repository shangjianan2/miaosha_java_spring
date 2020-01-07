package hello.service;

import hello.error.BussinessException;
import hello.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    ItemModel createItem(ItemModel itemModel) throws BussinessException;
    List<ItemModel> getItemList();
    ItemModel getItemInfoById(Integer id);
}

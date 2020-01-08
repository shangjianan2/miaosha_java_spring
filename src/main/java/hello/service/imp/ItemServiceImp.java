package hello.service.imp;

import hello.dao.ItemInfoMapper;
import hello.dao.ItemStockMapper;
import hello.dataObject.ItemInfo;
import hello.dataObject.ItemStock;
import hello.error.BussinessException;
import hello.error.EmBussinessError;
import hello.service.ItemService;
import hello.service.model.ItemModel;
import hello.validate.ValidationResult;
import hello.validate.ValidatorImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    ValidatorImp validatorImp;
    @Autowired
    ItemInfoMapper itemInfoMapper;
    @Autowired
    ItemStockMapper itemStockMapper;
    @Transactional
    @Override
    public ItemModel createItem(ItemModel itemModel) throws BussinessException {
        //validate
        ValidationResult validationResult = validatorImp.validate(itemModel);
        if(validationResult.isError()){
            throw new BussinessException(EmBussinessError.ILLEGAL_PARAMETER);
        }

        //mysql
        ItemInfo itemInfo = new ItemInfo();
        BeanUtils.copyProperties(itemModel, itemInfo);
        itemInfoMapper.insertSelective(itemInfo);
        itemModel.setId(itemInfo.getId());

        ItemStock itemStock = new ItemStock();
        itemStock.setStock(itemModel.getStock());
        itemStock.setItemId(itemModel.getId());
        itemStockMapper.insertSelective(itemStock);

        //return ItemModel
        ItemModel itemModelReturn = this.getItemInfoById(itemModel.getId());
        return itemModelReturn;
    }

    @Override
    public List<ItemModel> getItemList() {
        List<ItemInfo> itemInfoList = itemInfoMapper.selectAll();
        List<ItemModel> itemModelList = itemInfoList.stream().map(itemInfo -> {
            ItemStock itemStock = itemStockMapper.selectByItemId(itemInfo.getId());
            ItemModel itemModel = this.convertItemModel(itemInfo, itemStock);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemInfoById(Integer id) {
        ItemInfo itemInfo = itemInfoMapper.selectByPrimaryKey(id);
        if(itemInfo == null){
            return null;
        }
        ItemStock itemStock = itemStockMapper.selectByItemId(id);
        return this.convertItemModel(itemInfo, itemStock);
    }

    public ItemModel convertItemModel(ItemInfo itemInfo, ItemStock itemStock){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemInfo, itemModel);
        itemModel.setStock(itemStock.getStock());
        return itemModel;
    }
}

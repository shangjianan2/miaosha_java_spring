package hello.controller;

import hello.error.BussinessException;
import hello.response.CommonReturnType;
import hello.service.ItemService;
import hello.service.model.ItemModel;
import hello.service.model.ItemModelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller("wjl2")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {
    @Autowired
    ItemService itemService;

    public ItemModelVO convertItemModeVOFromItemModel(ItemModel itemModel){
        ItemModelVO itemModelVO = new ItemModelVO();
        BeanUtils.copyProperties(itemModel, itemModelVO);
        return itemModelVO;
    }

    @RequestMapping(path = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType CreateNewItem(@RequestParam("title") String title,
                                          @RequestParam("price") BigDecimal price,
                                          @RequestParam("stock") Integer stock,
                                          @RequestParam("descriptions") String descriptions,
                                          @RequestParam("sales") Integer sales,
                                          @RequestParam("imgUrl") String imgUrl) throws BussinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescriptions(descriptions);
        itemModel.setSales(sales);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelReturn = itemService.createItem(itemModel);
        ItemModelVO itemModelVO = this.convertItemModeVOFromItemModel(itemModelReturn);
        return CommonReturnType.create(itemModelVO);
    }

    @RequestMapping(path = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listAllItem(){
        List<ItemModel> itemModelList = itemService.getItemList();
        return CommonReturnType.create(itemModelList);
    }

    @RequestMapping(path = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItemDetails(@RequestParam("id") Integer id){
        ItemModel itemModel = itemService.getItemInfoById(id);
        return CommonReturnType.create(itemModel);
    }
}

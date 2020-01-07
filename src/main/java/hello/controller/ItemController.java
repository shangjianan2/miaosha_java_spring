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
}

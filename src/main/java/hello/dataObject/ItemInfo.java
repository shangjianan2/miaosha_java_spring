package hello.dataObject;

import java.math.BigDecimal;

public class ItemInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.id
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.title
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.price
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.descriptions
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private String descriptions;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.sales
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.img_url
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    private String imgUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.id
     *
     * @return the value of item_info.id
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.id
     *
     * @param id the value for item_info.id
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.title
     *
     * @return the value of item_info.title
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.title
     *
     * @param title the value for item_info.title
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.price
     *
     * @return the value of item_info.price
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.price
     *
     * @param price the value for item_info.price
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.descriptions
     *
     * @return the value of item_info.descriptions
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public String getDescriptions() {
        return descriptions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.descriptions
     *
     * @param descriptions the value for item_info.descriptions
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions == null ? null : descriptions.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.sales
     *
     * @return the value of item_info.sales
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.sales
     *
     * @param sales the value for item_info.sales
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.img_url
     *
     * @return the value of item_info.img_url
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.img_url
     *
     * @param imgUrl the value for item_info.img_url
     *
     * @mbg.generated Tue Jan 07 16:25:28 CST 2020
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}
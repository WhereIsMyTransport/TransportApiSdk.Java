package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;



public class Fare {

    private String description;
    private FareProduct fareProduct;
    private Cost cost;
    private List<String> messages = new ArrayList<String>();

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The fareProduct
     */
    public FareProduct getFareProduct() {
        return fareProduct;
    }

    /**
     * 
     * @param fareProduct
     *     The fareProduct
     */
    public void setFareProduct(FareProduct fareProduct) {
        this.fareProduct = fareProduct;
    }

    /**
     * 
     * @return
     *     The cost
     */
    public Cost getCost() {
        return cost;
    }

    /**
     * 
     * @param cost
     *     The cost
     */
    public void setCost(Cost cost) {
        this.cost = cost;
    }

    /**
     * 
     * @return
     *     The messages
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}

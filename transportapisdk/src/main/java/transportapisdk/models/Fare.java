package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;



public class Fare {

    private String fareDescription;
    private FareProduct fareProduct;
    private Cost cost;
    private List<Object> messages = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The fareDescription
     */
    public String getFareDescription() {
        return fareDescription;
    }

    /**
     * 
     * @param fareDescription
     *     The fareDescription
     */
    public void setFareDescription(String fareDescription) {
        this.fareDescription = fareDescription;
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
    public List<Object> getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

}

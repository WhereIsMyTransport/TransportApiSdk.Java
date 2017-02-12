package transportapisdk.models;

public class Cost {

    private float amount;
    private String currencyCode;

    /**
     * 
     * @return
     *     The amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}

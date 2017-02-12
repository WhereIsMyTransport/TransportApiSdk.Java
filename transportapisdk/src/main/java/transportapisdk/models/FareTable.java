package transportapisdk.models;

import java.util.ArrayList;
import java.util.List;
public class FareTable {

    private String id;
    private String href;
    private String description;
    private List<String> messages = new ArrayList<String>();
    private List<FareTableEntry> fareTableEntries = new ArrayList<FareTableEntry>();

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The href
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     * The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The messages
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     *
     * @param messages
     * The messages
     */
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    /**
     *
     * @return
     * The fareTableEntries
     */
    public List<FareTableEntry> getFareTableEntries() {
        return fareTableEntries;
    }

    /**
     *
     * @param fareTableEntries
     * The fareTableEntries
     */
    public void setFareTableEntries(List<FareTableEntry> fareTableEntries) {
        this.fareTableEntries = fareTableEntries;
    }

}

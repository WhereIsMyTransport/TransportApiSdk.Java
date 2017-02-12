package transportapisdk.models;

import java.util.Date;

/**
 * Created by James on 2015-02-02.
 */
public class TripAnnouncementModel {

    public String announcementType;
    public String description;
    public Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnouncementType() {
        return announcementType;
    }

    public void setAnnouncementType(String announcementType) {
        this.announcementType = announcementType;
    }
}

package com.lv.rxdemo.model;

import com.lv.rxdemo.config.Constant;
import java.io.Serializable;

/**
 * Created by Lv on 2016/7/13.
 */
public class VRModel implements Serializable {

    /**
     * againstPeople : 0
     * apartmentLayout : 3室4厅1卫2厨
     * apartmentType : 0
     * constructionArea : 168
     * desid : 3FO4KHEDR1IO
     * designerPic : modelhomedesign/3329/1476/25769806476/25769804381.jpg
     * lastUpdateTimestamp : 1466565696
     * modelHomeApartmentDesignId : 25769806476
     * modelHomeApartmentDesignName : 上海_祥和星宇花园_2016-06-21-1723
     * modelHomeApartmentId : 0
     * modelHomeApartmentName :
     * modelHomeApartmentPicAddress : modelhomedesign/3329/1476/25769806476/25769804381.jpg
     * modelHomeCityId : 0
     * modelHomeCityName : 上海
     * modelHomeCommunityId : 0
     * modelHomeCommunityName :
     * modelHomeDesignVrPicAddress : http://yun.kujiale.com/design/3FO4JUXQ4J81/show
     * onlineStatus : 2
     * pingmianPic : http://qhyxpic.oss.kujiale.com/fpimgnew/2016/06/22/V2n1wQp11MsuZA4_800x800.jpg
     * status : 4
     * submitAuditTimestamp : 1466739261
     */

    private int againstPeople;
    private String apartmentLayout;
    private int apartmentType;
    private int constructionArea;
    private String desid;
    private String designerPic;
    private int lastUpdateTimestamp;
    private long modelHomeApartmentDesignId;
    private String modelHomeApartmentDesignName;
    private int modelHomeApartmentId;
    private String modelHomeApartmentName;
    private String modelHomeApartmentPicAddress;
    private int modelHomeCityId;
    private String modelHomeCityName;
    private int modelHomeCommunityId;
    private String modelHomeCommunityName;
    private String modelHomeDesignVrPicAddress;
    private int onlineStatus;
    private String pingmianPic;
    private int status;
    private long budget;//造价

    public int getAgainstPeople() {
        return againstPeople;
    }

    public void setAgainstPeople(int againstPeople) {
        this.againstPeople = againstPeople;
    }

    public String getApartmentLayout() {
        return apartmentLayout;
    }

    public void setApartmentLayout(String apartmentLayout) {
        this.apartmentLayout = apartmentLayout;
    }

    public int getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(int apartmentType) {
        this.apartmentType = apartmentType;
    }

    public int getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(int constructionArea) {
        this.constructionArea = constructionArea;
    }

    public String getDesid() {
        return desid;
    }

    public void setDesid(String desid) {
        this.desid = desid;
    }

    public String getDesignerPic() {
        return Constant.GETIMAGE_URL + designerPic;
    }

    public void setDesignerPic(String designerPic) {
        this.designerPic = designerPic;
    }

    public int getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(int lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public long getModelHomeApartmentDesignId() {
        return modelHomeApartmentDesignId;
    }

    public void setModelHomeApartmentDesignId(long modelHomeApartmentDesignId) {
        this.modelHomeApartmentDesignId = modelHomeApartmentDesignId;
    }

    public String getModelHomeApartmentDesignName() {
        return modelHomeApartmentDesignName;
    }

    public void setModelHomeApartmentDesignName(String modelHomeApartmentDesignName) {
        this.modelHomeApartmentDesignName = modelHomeApartmentDesignName;
    }

    public int getModelHomeApartmentId() {
        return modelHomeApartmentId;
    }

    public void setModelHomeApartmentId(int modelHomeApartmentId) {
        this.modelHomeApartmentId = modelHomeApartmentId;
    }

    public String getModelHomeApartmentName() {
        return modelHomeApartmentName;
    }

    public void setModelHomeApartmentName(String modelHomeApartmentName) {
        this.modelHomeApartmentName = modelHomeApartmentName;
    }

    public String getModelHomeApartmentPicAddress() {
        return Constant.GETIMAGE_URL + modelHomeApartmentPicAddress;
    }

    public void setModelHomeApartmentPicAddress(String modelHomeApartmentPicAddress) {
        this.modelHomeApartmentPicAddress = modelHomeApartmentPicAddress;
    }

    public int getModelHomeCityId() {
        return modelHomeCityId;
    }

    public void setModelHomeCityId(int modelHomeCityId) {
        this.modelHomeCityId = modelHomeCityId;
    }

    public String getModelHomeCityName() {
        return modelHomeCityName;
    }

    public void setModelHomeCityName(String modelHomeCityName) {
        this.modelHomeCityName = modelHomeCityName;
    }

    public int getModelHomeCommunityId() {
        return modelHomeCommunityId;
    }

    public void setModelHomeCommunityId(int modelHomeCommunityId) {
        this.modelHomeCommunityId = modelHomeCommunityId;
    }

    public String getModelHomeCommunityName() {
        return modelHomeCommunityName;
    }

    public void setModelHomeCommunityName(String modelHomeCommunityName) {
        this.modelHomeCommunityName = modelHomeCommunityName;
    }

    public String getModelHomeDesignVrPicAddress() {
        return modelHomeDesignVrPicAddress;
    }

    public void setModelHomeDesignVrPicAddress(String modelHomeDesignVrPicAddress) {
        this.modelHomeDesignVrPicAddress = modelHomeDesignVrPicAddress;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getPingmianPic() {
        return pingmianPic;
    }

    public void setPingmianPic(String pingmianPic) {
        this.pingmianPic = pingmianPic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getBudget() {
        return budget / 100;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
}

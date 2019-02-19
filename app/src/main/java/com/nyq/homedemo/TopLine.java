package com.nyq.homedemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @desc:(头条)
 * @company:中国电信甘肃万维
 * @projectName:jkgs
 * @author:liufx
 * @CreateTime:2017/4/17 14:51
 */

public class TopLine implements Serializable {

    @Expose
    @SerializedName("TOP_LINE_ID")
    private String topLineId;
    @Expose
    @SerializedName("AREA_CODE")
    private String areaCode;
    @Expose
    @SerializedName("TOP_LINE_URL")
    private String topLineUrl;
    @Expose
    @SerializedName("TOP_LINE_NAME")
    private String topLineName;
    @Expose
    @SerializedName("SHARE_WORDS")
    private String shareWords;
    @Expose
    @SerializedName("SHARE_PIC")
    private String sharePic;
    @Expose
    @SerializedName("IS_SHARE")
    private String isShare;
    @Expose
    @SerializedName("BUSSINESS_ID")
    private String bussinessId;
    @Expose
    @SerializedName("TOP_LINE_TYPE")
    private String topLineType;
    @Expose
    @SerializedName("TOP_LINE_CONTENT")
    private String topLineContent;
    @Expose
    @SerializedName("TOP_LINE_IMG")
    private String topLineImg;
    @Expose
    @SerializedName("CREATE_TIME")
    private String createTime;
    @Expose
    @SerializedName("STATUS")
    private String status;
    @Expose
    @SerializedName("H5_URL")
    private String h5Url;
    @Expose
    @SerializedName("SOURCE")
    private String source;
    @Expose
    @SerializedName("HAS_IMAGE")
    private String hasImg;

    @Expose
    @SerializedName("IMAGE_SIZE")
    private String imageSize;


    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getTopLineId() {
        return topLineId;
    }

    public void setTopLineId(String topLineId) {
        this.topLineId = topLineId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTopLineUrl() {
        return topLineUrl;
    }

    public void setTopLineUrl(String topLineUrl) {
        this.topLineUrl = topLineUrl;
    }

    public String getTopLineName() {
        return topLineName;
    }

    public void setTopLineName(String topLineName) {
        this.topLineName = topLineName;
    }

    public String getShareWords() {
        return shareWords;
    }

    public void setShareWords(String shareWords) {
        this.shareWords = shareWords;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

    public String getTopLineType() {
        return topLineType;
    }

    public void setTopLineType(String topLineType) {
        this.topLineType = topLineType;
    }

    public String getTopLineContent() {
        return topLineContent;
    }

    public void setTopLineContent(String topLineContent) {
        this.topLineContent = topLineContent;
    }

    public String getTopLineImg() {
        return topLineImg;
    }

    public void setTopLineImg(String topLineImg) {
        this.topLineImg = topLineImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasImg() {
        return hasImg;
    }

    public void setHasImg(String hasImg) {
        this.hasImg = hasImg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

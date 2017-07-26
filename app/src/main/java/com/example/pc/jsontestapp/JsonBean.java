package com.example.pc.jsontestapp;

import java.util.List;

/**
 * Created by pc on 2017-07-11.
 */

public class JsonBean {

    private String result;
    private List<AdDeal> adDealList;
    private String resultMsg;

    @Override
    public String toString() {
        return "result:"+result+"\nadDealList:"+adDealList+"\nresultMsg:"+resultMsg;
    }

    class AdDeal{
        private String dealName;
        private String dealId;
        private String price;
        private String thumbnailPath;
        private List<PriceSection>dealPriceSectionList;

        class PriceSection{
            private String token;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

        }//end class PriceSection

        public String getDealName() {
            return dealName;
        }

        public void setDealName(String dealName) {
            this.dealName = dealName;
        }

        public String getDealId() {
            return dealId;
        }

        public void setDealId(String dealId) {
            this.dealId = dealId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }

        public List<PriceSection> getDealPriceSectionList() {
            return dealPriceSectionList;
        }

        public void setDealPriceSectionList(List<PriceSection> dealPriceSectionList) {
            this.dealPriceSectionList = dealPriceSectionList;
        }
    }//end classAdDeal

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<AdDeal> getAdDealList() {
        return adDealList;
    }

    public void setAdDealList(List<AdDeal> adDealList) {
        this.adDealList = adDealList;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}//end classJson

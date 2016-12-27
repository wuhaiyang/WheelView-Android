/*
 * Copyright (c) 2016. danlu.com Co.Ltd. All rights reserved.
 */

package com.danlu.android.danlupickerview;

import com.danlu.lib.listener.IPickerViewData;

import java.util.List;

/**
 * author: wuhaiyang(<a href="mailto:wuhaiyang@danlu.com">wuhaiyang@danlu.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2016-12-26 下午2:36<br/>
 * <p>
 * <p>
 * 地区数据模型
 * </p>
 */
public class AreaModel implements IPickerViewData {

    private String areaId;
    private String areaName;
    private List<SecondBean> second;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<SecondBean> getSecond() {
        return second;
    }

    public void setSecond(List<SecondBean> second) {
        this.second = second;
    }

    @Override
    public String getPickerViewText() {
        return areaName;
    }

    @Override
    public boolean equals(Object obj) {
        return null != obj && obj instanceof AreaModel && ((AreaModel) obj).areaId.equals(this.areaId);
    }

    public static class SecondBean implements IPickerViewData {
        private String areaId;
        private String areaName;
        private List<ThreeBean> three;

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<ThreeBean> getThree() {
            return three;
        }

        public void setThree(List<ThreeBean> three) {
            this.three = three;
        }

        @Override
        public String getPickerViewText() {
            return areaName;
        }

        @Override
        public boolean equals(Object obj) {
            return null != obj && obj instanceof SecondBean && ((SecondBean) obj).areaId.equals(areaId);
        }

        public static class ThreeBean implements IPickerViewData {
            /**
             * areaId : CHNP001C001D0001
             * areaName : 东城区
             */

            private String areaId;
            private String areaName;

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            @Override
            public String getPickerViewText() {
                return areaName;
            }

            @Override
            public boolean equals(Object obj) {
                return null != obj && obj instanceof ThreeBean && ((ThreeBean) obj).areaId.equals(areaId);
            }
        }
    }
}


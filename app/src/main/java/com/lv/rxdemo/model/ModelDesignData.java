package com.lv.rxdemo.model;

import java.io.Serializable;

/**
 * Created by Lv on 2016/7/13.
 */
public class ModelDesignData implements Serializable{

    private HomeDesign modelHomeDesigns;

    public HomeDesign getModelHomeDesigns() {
        return modelHomeDesigns;
    }

    public void setModelHomeDesigns(HomeDesign modelHomeDesigns) {
        this.modelHomeDesigns = modelHomeDesigns;
    }
}

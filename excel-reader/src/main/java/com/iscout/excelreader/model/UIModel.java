package com.iscout.excelreader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UIModel {
    private String category;
    private String pBarText;
    private String pCaption;
    private String pClass;
    private int pComp;
    private int pCost;
    private String pDepend;
    private String pEnd;
    private int pGroup;
    private int pID;
    private String pLink;
    private int pMile;
    private String pName;
    private String pNotes;
    private int pOpen;
    private int pParent;
    private String pPlanEnd;
    private String pPlanStart;
    private String pRes;
    private String pStart;
    private String sector;
}

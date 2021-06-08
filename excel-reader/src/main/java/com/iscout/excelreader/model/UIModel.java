package com.iscout.excelreader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getpBarText() {
        return pBarText;
    }

    public void setpBarText(String pBarText) {
        this.pBarText = pBarText;
    }

    public String getpCaption() {
        return pCaption;
    }

    public void setpCaption(String pCaption) {
        this.pCaption = pCaption;
    }

    public String getpClass() {
        return pClass;
    }

    public void setpClass(String pClass) {
        this.pClass = pClass;
    }

    public int getpComp() {
        return pComp;
    }

    public void setpComp(int pComp) {
        this.pComp = pComp;
    }

    public int getpCost() {
        return pCost;
    }

    public void setpCost(int pCost) {
        this.pCost = pCost;
    }

    public String getpDepend() {
        return pDepend;
    }

    public void setpDepend(String pDepend) {
        this.pDepend = pDepend;
    }

    public String getpEnd() {
        return pEnd;
    }

    public void setpEnd(String pEnd) {
        this.pEnd = pEnd;
    }

    public int getpGroup() {
        return pGroup;
    }

    public void setpGroup(int pGroup) {
        this.pGroup = pGroup;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getpLink() {
        return pLink;
    }

    public void setpLink(String pLink) {
        this.pLink = pLink;
    }

    public int getpMile() {
        return pMile;
    }

    public void setpMile(int pMile) {
        this.pMile = pMile;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpNotes() {
        return pNotes;
    }

    public void setpNotes(String pNotes) {
        this.pNotes = pNotes;
    }

    public int getpOpen() {
        return pOpen;
    }

    public void setpOpen(int pOpen) {
        this.pOpen = pOpen;
    }

    public int getpParent() {
        return pParent;
    }

    public void setpParent(int pParent) {
        this.pParent = pParent;
    }

    public String getpPlanEnd() {
        return pPlanEnd;
    }

    public void setpPlanEnd(String pPlanEnd) {
        this.pPlanEnd = pPlanEnd;
    }

    public String getpPlanStart() {
        return pPlanStart;
    }

    public void setpPlanStart(String pPlanStart) {
        this.pPlanStart = pPlanStart;
    }

    public String getpRes() {
        return pRes;
    }

    public void setpRes(String pRes) {
        this.pRes = pRes;
    }

    public String getpStart() {
        return pStart;
    }

    public void setpStart(String pStart) {
        this.pStart = pStart;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}

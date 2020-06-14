package com.example.calorify.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TotalNutrients implements Serializable {
    @SerializedName("ENERC_KCAL")
    private ENERCKCAL eNERCKCAL;
    @SerializedName("FAT")
    private FAT fAT;
    @SerializedName("FASAT")
    private FASAT fASAT;
    @SerializedName("FATRN")
    private FATRN fATRN;
    @SerializedName("FAMS")
    private FAMS fAMS;
    @SerializedName("FAPU")
    private FAPU fAPU;
    @SerializedName("CHOCDF")
    private CHOCDF cHOCDF;
    @SerializedName("FIBTG")
    private FIBTG fIBTG;
    @SerializedName("SUGAR")
    private SUGAR sUGAR;
    @SerializedName("PROCNT")
    private PROCNT pROCNT;
    @SerializedName("CHOLE")
    private CHOLE cHOLE;
    @SerializedName("NA")
    private NA nA;
    @SerializedName("CA")
    private CA cA;
    @SerializedName("MG")
    private MG mG;
    @SerializedName("K")
    private K k;
    @SerializedName("FE")
    private FE fE;
    @SerializedName("ZN")
    private ZN zN;
    @SerializedName("P")
    private P p;
    @SerializedName("VITAR_AE")
    private VITARAE vITARAE;
    @SerializedName("VITC")
    private VITC vITC;
    @SerializedName("THIA")
    private THIA tHIA;
    @SerializedName("RIBF")
    private RIBF rIBF;
    @SerializedName("NIA")
    private NIA nIA;
    @SerializedName("VITB6A")
    private VITB6A vITB6A;
    @SerializedName("FOLDFE")
    private FOLDFE fOLDFE;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ENERCKCAL getENERCKCAL() {
        return eNERCKCAL;
    }

    public void setENERCKCAL(ENERCKCAL eNERCKCAL) {
        this.eNERCKCAL = eNERCKCAL;
    }

    public FAT getFAT() {
        return fAT;
    }

    public void setFAT(FAT fAT) {
        this.fAT = fAT;
    }

    public FASAT getFASAT() {
        return fASAT;
    }

    public void setFASAT(FASAT fASAT) {
        this.fASAT = fASAT;
    }

    public FATRN getFATRN() {
        return fATRN;
    }

    public void setFATRN(FATRN fATRN) {
        this.fATRN = fATRN;
    }

    public FAMS getFAMS() {
        return fAMS;
    }

    public void setFAMS(FAMS fAMS) {
        this.fAMS = fAMS;
    }

    public FAPU getFAPU() {
        return fAPU;
    }

    public void setFAPU(FAPU fAPU) {
        this.fAPU = fAPU;
    }

    public CHOCDF getCHOCDF() {
        return cHOCDF;
    }

    public void setCHOCDF(CHOCDF cHOCDF) {
        this.cHOCDF = cHOCDF;
    }

    public FIBTG getFIBTG() {
        return fIBTG;
    }

    public void setFIBTG(FIBTG fIBTG) {
        this.fIBTG = fIBTG;
    }

    public SUGAR getSUGAR() {
        return sUGAR;
    }

    public void setSUGAR(SUGAR sUGAR) {
        this.sUGAR = sUGAR;
    }

    public PROCNT getPROCNT() {
        return pROCNT;
    }

    public void setPROCNT(PROCNT pROCNT) {
        this.pROCNT = pROCNT;
    }

    public CHOLE getCHOLE() {
        return cHOLE;
    }

    public void setCHOLE(CHOLE cHOLE) {
        this.cHOLE = cHOLE;
    }

    public NA getNA() {
        return nA;
    }

    public void setNA(NA nA) {
        this.nA = nA;
    }

    public CA getCA() {
        return cA;
    }

    public void setCA(CA cA) {
        this.cA = cA;
    }

    public MG getMG() {
        return mG;
    }

    public void setMG(MG mG) {
        this.mG = mG;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public FE getFE() {
        return fE;
    }

    public void setFE(FE fE) {
        this.fE = fE;
    }

    public ZN getZN() {
        return zN;
    }

    public void setZN(ZN zN) {
        this.zN = zN;
    }

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public VITARAE getVITARAE() {
        return vITARAE;
    }

    public void setVITARAE(VITARAE vITARAE) {
        this.vITARAE = vITARAE;
    }

    public VITC getVITC() {
        return vITC;
    }

    public void setVITC(VITC vITC) {
        this.vITC = vITC;
    }

    public THIA getTHIA() {
        return tHIA;
    }

    public void setTHIA(THIA tHIA) {
        this.tHIA = tHIA;
    }

    public RIBF getRIBF() {
        return rIBF;
    }

    public void setRIBF(RIBF rIBF) {
        this.rIBF = rIBF;
    }

    public NIA getNIA() {
        return nIA;
    }

    public void setNIA(NIA nIA) {
        this.nIA = nIA;
    }

    public VITB6A getVITB6A() {
        return vITB6A;
    }

    public void setVITB6A(VITB6A vITB6A) {
        this.vITB6A = vITB6A;
    }

    public FOLDFE getFOLDFE() {
        return fOLDFE;
    }

    public void setFOLDFE(FOLDFE fOLDFE) {
        this.fOLDFE = fOLDFE;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}

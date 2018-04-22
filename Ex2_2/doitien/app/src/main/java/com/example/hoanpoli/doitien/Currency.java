package com.example.hoanpoli.doitien;

public class Currency {
    private String m_sCurrencyCode;
    private String m_sCurrencyName;
    private Double m_dBuy;
    private Double m_dTransfer;
    private Double m_dSell;


    public Currency(String m_sCurrencyCode, String m_sCurrencyName, Double m_dBuy, Double m_dTransfer, Double m_dSell) {
        this.m_sCurrencyCode = m_sCurrencyCode;
        this.m_sCurrencyName = m_sCurrencyName;
        this.m_dBuy = m_dBuy;
        this.m_dTransfer = m_dTransfer;
        this.m_dSell = m_dSell;
    }

    public String GetCurrencyCode(){
        return m_sCurrencyCode;
    }

    public String GetCurrencyName(){
        return m_sCurrencyName;
    }

    public Double GetBuy(){
        return m_dBuy;
    }

    public Double GetTransfer(){
        return m_dTransfer;
    }

    public Double GetSell(){
        return m_dSell;
    }
}


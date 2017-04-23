package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 26/9/16.
 */
public enum Currency
{
    INR(1),
    USD(2),
    GBP(3),
    NZD(4),
    AUD(5),
    CAD(6);

    private int value;
    private Currency(int value)
    {
        this.value = value;
    }

    public int getIntValue()
    {
        return this.value;
    }

    public static String getStringValue(int value)
    {
        switch (value)
        {
            case 1:
                return INR.toString();
            case 2:
                return USD.toString();
            case 3:
                return GBP.toString();
            case 4:
                return NZD.toString();
            case 5:
                return AUD.toString();
            case 6:
                return CAD.toString();
        }

        return null;
    }
}

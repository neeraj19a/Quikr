package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 26/9/16.
 */
public enum DurationUnit
{
    HOUR(1),

    DAY(2),

    WEEK(3),

    MONTH(4),

    YEAR(5);

    private int value;
    private DurationUnit(int value)
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
                return HOUR.toString();
            case 2:
                return DAY.toString();
            case 3:
                return WEEK.toString();
            case 4:
                return MONTH.toString();
            case 5:
                return YEAR.toString();
        }

        return null;
    }
}

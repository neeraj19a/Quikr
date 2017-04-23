package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 26/9/16.
 */
public enum Discipline
{
    ACCOUNTING_N_FINANCE("Accounting & Finance", 1),

    ANIMATION("Animation", 2),

    ARCHITECTURE("Architecture", 3),

    ARTS_N_COMMERCE("Arts & Commerce", 4),

    BANKING_N_FINANCE("Banking & Finance", 5),

    BEAUTY_N_FITNESS("Beauty & Fitness", 6),

    BUSINESS_MANAGEMENT("Business Management", 7),

    COMPUTER("Computer", 8),

    DESIGN("Design", 9),

    ENGINEERING_N_TECHNOLOGY("Engineering & Technology", 10),

    EXAM_COACHING("Exam Coaching", 11),

    FILMS_N_ENTERTAINMENT("Films & Entertainment", 12),

    HOSPITALITY_N_AVIATION("Hospitality & Aviation", 13),

    LANGUAGE_TRAINING("Language Training", 14),

    LAW("Law", 15),

    MASS_COMM_N_MEDIA("Mass Comm & Media", 16),

    MEDICINE_N_HEALTHCARE("Medicine & Healthcare", 17),

    RETAIL("Retail", 18),

    SCIENCE("Science", 19),

    TEACHING("Teaching", 20);

    private String realName;
    private int value;
    private Discipline(String realName, int value)
    {
        this.realName = realName;
        this.value = value;
    }

    public String toString()
    {
        return this.realName;
    }

    public int getIntValue()
    {
        return value;
    }

    public static String getStringValue(int value)
    {
        switch (value)
        {
            case 1:
                return ACCOUNTING_N_FINANCE.toString();
            case 2:
                return ANIMATION.toString();
            case 3:
                return ARCHITECTURE.toString();
            case 4:
                return ARTS_N_COMMERCE.toString();
            case 5:
                return BANKING_N_FINANCE.toString();
            case 6:
                return BEAUTY_N_FITNESS.toString();
            case 7:
                return BUSINESS_MANAGEMENT.toString();
            case 8:
                return COMPUTER.toString();
            case 9:
                return DESIGN.toString();
            case 10:
                return ENGINEERING_N_TECHNOLOGY.toString();
            case 11:
                return EXAM_COACHING.toString();
            case 12:
                return FILMS_N_ENTERTAINMENT.toString();
            case 13:
                return HOSPITALITY_N_AVIATION.toString();
            case 14:
                return LANGUAGE_TRAINING.toString();
            case 15:
                return LAW.toString();
            case 16:
                return MASS_COMM_N_MEDIA.toString();
            case 17:
                return MEDICINE_N_HEALTHCARE.toString();
            case 18:
                return RETAIL.toString();
            case 19:
                return SCIENCE.toString();
            case 20:
                return TEACHING.toString();
        }

        return null;
    }
}

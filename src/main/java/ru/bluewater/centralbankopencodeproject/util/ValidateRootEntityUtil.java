package ru.bluewater.centralbankopencodeproject.util;

public class ValidateRootEntityUtil {
    public static String ED_AUTHOR_INVALID =
            "EDAuthor should be in 999999999 to 9999999999";
    public static String ED_NO_INVALID =
            "number of electronic message (EDNo) should be in 0 to 999999999 range";
    public static String CREATION_REASON_INVALID =
            "creation reason code (CreationReason) length should be 4";
    public static String INFO_TYPE_CODE_INVALID =
            "type of information presentation code (InfoTypeCode) length should be 4";
    public static String DIRECTORY_VERSION_INVALID =
            "directory BIC Version (DirectoryVersion) should be in range 0 to 99";
    public static IllegalArgumentException EDAuthorException =
            new IllegalArgumentException(ED_AUTHOR_INVALID);
    public static IllegalArgumentException EDNoException =
            new IllegalArgumentException(ED_NO_INVALID);
    public static IllegalArgumentException creationReasonException =
            new IllegalArgumentException(CREATION_REASON_INVALID);
    public static IllegalArgumentException infoTypeCodeException =
            new IllegalArgumentException(INFO_TYPE_CODE_INVALID);
    public static IllegalArgumentException directoryVersionException =
            new IllegalArgumentException(DIRECTORY_VERSION_INVALID);
    public static boolean isEDNumberType(int value){
        return value >= 0 && value <= 999999999;
    }
    public static boolean isErIDType(Long value){
        return value > 999999999 && value <= 9999999999L;
    }
    public static boolean isReasonCodeType(String value){
        return value.length() == 4;
    }
    public static boolean isRequestCodeType(String value){
        return value.length() == 4;
    }
    public static boolean isMax2NumberType(int value){
        return value >= 0 && value <= 99;
    }
}

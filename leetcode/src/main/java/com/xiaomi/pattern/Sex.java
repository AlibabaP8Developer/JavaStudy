package com.xiaomi.pattern;

public enum Sex {
    MALE,
    FEMALE;
}
//public final class Sex extends Enum<Sex> {
//    public static final Sex MALE;
//    public static final Sex FEMALE;
//
//    private Sex(String name, int ordinal) {
//        super(name, ordinal);
//    }
//
//    private static final Sex[] $VALUES;
//
//    static {
//        MALE = new Sex("MALE", 0);
//        FEMALE = new Sex("FEMALE", 1);
//        $VALUES = values();
//    }
//
//    private static Sex[] values() {
//        return $VALUES.clone();
//    }
//
//    private static Sex[] $values() {
//        return new Sex[]{MALE, FEMALE};
//    }
//
//
//
//}

package com.multidatasourceaop.enums;

public enum DBType {
    MASTER("master"),

    SLAVE("salve");

    private String desc;

    DBType(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return this.desc;
    }

    public static DBType getEnum(String desc) {
        for (DBType dbType : DBType.values()) {
            if (desc.equals(dbType.desc)) {
                return dbType;
            }
        }

        return null;
    }
}

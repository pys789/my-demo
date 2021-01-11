package cn.pys.entity;

public enum FieldType {
    SYS_FIELD(0, "SYS", "系统字段", "s", "$"),
    BIZ_FIELD(1, "BIZ", "业务字段", "b", ""),
    ;
    private int id;
    private String code;
    private String description;
    private String index;
    private String sign;

    FieldType(int id, String code, String description, String index, String sign) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.index = index;
        this.sign = sign;
    }



    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

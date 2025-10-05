package com.gec.req;

public class RoleReq {

    private String roleid;

    private String powerid;

    private String rolename;

    private String power;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getPowerid() {
        return powerid;
    }

    public void setPowerid(String powerid) {
        this.powerid = powerid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "RoleReq{" +
                "roleid='" + roleid + '\'' +
                ", powerid='" + powerid + '\'' +
                ", rolename='" + rolename + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}

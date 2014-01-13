package ru.terra.universal.shared.entity;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.Serializable;

public class PlayerInfo extends AbstractEntity implements Serializable {
    private String name = "";
    private String world = "";
    private Integer hp = 0, mp = 0, xp = 0;
    private Integer level = 1;

    public PlayerInfo(String name, String world) {
        this.name = name;
        this.world = world;
    }

    public PlayerInfo() {
        this.name = "";
        this.world = "";
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void writePlayerInfo(ChannelBuffer buffer) {
        buffer.writeInt(hp);
        buffer.writeInt(mp);
        buffer.writeInt(xp);
        buffer.writeInt(level);
    }

    public void readPlayerInfo(ChannelBuffer buffer) {
        hp = buffer.readInt();
        mp = buffer.readInt();
        xp = buffer.readInt();
        level = buffer.readInt();
    }
}

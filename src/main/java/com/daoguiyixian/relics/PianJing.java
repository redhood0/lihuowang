package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

public class PianJing extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("PianJing");
    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/pianjing.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/pianjing.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    public int out_num = 0;

    public PianJing() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);
        this.counter = 1;
//        tips.clear();
//        tips.add(new PowerTip(name, description));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    //效果不好就换三回合



    public void onSpendGold() {
        if (!this.usedUp) {
            this.flash();
            this.setCounter(-2);
        }

    }

    public void setCounter(int setCounter) {
        this.counter = setCounter;
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }

    }
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            this.flash();
            if(this.counter == -2){
                out_num = -2;
            }
        } else {

        }
    }


    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }


    public AbstractRelic makeCopy() {
        return new PianJing();
    }
}

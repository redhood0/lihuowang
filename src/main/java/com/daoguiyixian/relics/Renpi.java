package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Renpi extends CustomRelic {

    // 遗物ID
    public static final String ID = ModHelper.MakePath("Renpi");


    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/renpi.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/renpi.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Renpi() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);
//        this.counter = 1;

//        tips.clear();
//        tips.add(new PowerTip(name, description));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onTrigger() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        int healAmt = AbstractDungeon.player.maxHealth / 2;
        if (healAmt < 1) {
            healAmt = 1;
        }
        AbstractDungeon.player.heal(healAmt, true);
        this.setCounter(-2);
    }


    public void recharge(){
        this.counter = -1;
        unusedUp();

    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();

            this.counter = -2;
        }
    }

    @Override
    public void onEquip() {

    }



    public AbstractRelic makeCopy() {
        return new Renpi();
    }

    public void unusedUp() {
        this.grayscale = false;
        this.usedUp = false;
        this.description = this.getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
}

package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.characters.Lihuowang_Character;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Dengjie extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("Dengjie");
    public static int DAQIANLU_LEVEL = 1;
    public static final int MAX_LEVEL = 10;

    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/dengjie.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/dengjie.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Dengjie() {

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
//    @Override
//    public void onCardDraw(AbstractCard card) {
//        //super.onCardDraw(card);//不确定要不要删
//        if (card.cardID.equals("Doubt")) {
//            ++this.counter;
//            if (this.counter == 3) {
//                this.counter = 0;
//                this.flash();
//                LihuowangMod.logger.error("======>3张疑虑，触发疯狂====>");
//                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CrazyPower(AbstractDungeon.player, 1), 1, true));
////                this.pulse = false;
//            } else if (this.counter == 9) {
////                this.beginPulse();
////                this.pulse = true;
////                AbstractDungeon.player.hand.refreshHandLayout();
////                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
////                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PenNibPower(AbstractDungeon.player, 1), 1, true));
//            }
//        }
//    }


    @Override
    public void atBattleStart() {
        this.flash();
//        this.counter = 1;
        if (this.counter == 1) {

            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 8), 8));
        } else if (this.counter == 2) {

            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 12), 12));
        } else if (this.counter == 3) {

            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 14), 14));

        } else if (this.counter == 4) {

            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 15), 15));

        } else {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 11+this.counter), 11+this.counter));

        }

        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }


    @Override
    public void onEquip() {
        this.counter = 1;
    }



    public AbstractRelic makeCopy() {
        return new Dengjie();
    }

    @Override
    public void onLoseHp(int damageAmount) {
            // 伤害增加
        super.onLoseHp(damageAmount);
    }
}

package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.helpers.TextureLoader;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Xinsu extends CustomRelic  {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("Xinsu");
    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/Xinsu.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/Xinsu.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Xinsu() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);

//        tips.clear();
//        tips.add(new PowerTip(name, description));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    //效果不好就换三回合
    @Override
    public void onCardDraw(AbstractCard card) {
        //super.onCardDraw(card);//不确定要不要删
        if (card.cardID.equals("Doubt")) {
            ++this.counter;
            if (this.counter == 3) {
                this.counter = 0;
                this.flash();
                LihuowangMod.logger.error("======>3张疑虑，触发疯狂====>");
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CrazyPower(AbstractDungeon.player, 1), 1, true));
//                this.pulse = false;
            } else if (this.counter == 9) {
//                this.beginPulse();
//                this.pulse = true;
//                AbstractDungeon.player.hand.refreshHandLayout();
//                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PenNibPower(AbstractDungeon.player, 1), 1, true));
            }
        }
    }



    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.counter=0;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new Doubt(), 1, false));
        ++this.counter;
        if (this.counter == 3) {
            this.counter = 0;
            this.flash();
            LihuowangMod.logger.error("======>3张疑虑，触发疯狂====>");
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CrazyPower(AbstractDungeon.player, 1), 1, true));
//                this.pulse = false;
        }
    }

//    public void atBattleStartPreDraw() {
//        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new MakeTempCardInHandAction(new Doubt(), 1, false));
//        ++this.counter;
//    }

    public AbstractRelic makeCopy() {
        return new Xinsu();
    }


}

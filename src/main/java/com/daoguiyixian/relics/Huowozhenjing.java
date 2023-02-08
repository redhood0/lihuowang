package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MedicalKit;

public class Huowozhenjing extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("Huowozhenjing");

    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/huowozhenjing.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/huowozhenjing.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;

    public Huowozhenjing() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == AbstractCard.CardType.STATUS) {
//            AbstractDungeon.player.getRelic(ModHelper.MakePath("Huowozhenjing")).flash();
//            card.exhaust = true;
//            action.exhaustCard = true;
//        }
//
//    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction action) {

        LihuowangMod.logger.error("===================onUseCard================>");
        LihuowangMod.logger.error("===================onUseCard================>"+targetCard.cardID);
        LihuowangMod.logger.error("================onUseCard===================>");
        if (targetCard.type == AbstractCard.CardType.STATUS) {
            AbstractDungeon.player.getRelic(ModHelper.MakePath("Huowozhenjing")).flash();
            targetCard.exhaust = true;
            action.exhaustCard = true;
            AbstractDungeon.player.heal(3);
        }
    }

//    @Override
//    public void onCardDraw(AbstractCard drawnCard) {
//        super.onCardDraw(drawnCard);
//        LihuowangMod.logger.error("===================onCardDraw================>");
//        LihuowangMod.logger.error("===================onCardDraw================>"+drawnCard.cardID);
//        LihuowangMod.logger.error("================onCardDraw===================>");
//
//    }

    //    public void onUseCard(AbstractCard card, UseCardAction action) {
//
//        //&& card.cardID.equals("Burn")
//        if (card.type == AbstractCard.CardType.STATUS ) {
//            AbstractDungeon.player.getRelic(ModHelper.MakePath("Huowozhenjing")).flash();
//            card.exhaust = true;
//            action.exhaustCard = true;
////            AbstractDungeon.player.heal(3);
//        }
////        super.onUseCard(card,action);
//    }

    public AbstractRelic makeCopy() {
        return new Huowozhenjing();
    }
}

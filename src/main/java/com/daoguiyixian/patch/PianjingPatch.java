package com.daoguiyixian.patch;

import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.EatMeatPower;
import com.daoguiyixian.relics.PianJing;
import com.daoguiyixian.relics.XuanyangYupei;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.shop.ShopScreen;

import java.util.ArrayList;

public class PianjingPatch {

    public PianjingPatch() {
    }

    @SpirePatch(clz = ShopScreen.class, method = "initCards")
    public static class Pianjing_purgeCard {
        @SpireInsertPatch(rloc = 11, localvars = {"tmpPrice","c"})
        public static SpireReturn<Void> Insert(ShopScreen _inst,@ByRef float[] temPrice,AbstractCard card) {
            if (AbstractDungeon.player.hasRelic(PianJing.ID)) {
                LihuowangMod.logger.error("=============AbstractDungeon.player.hasRelic(PianJing.ID)2=============>");
//                temPrice[0] = 2;
                if (AbstractDungeon.player.getRelic(PianJing.ID).counter != -2) {
                    temPrice[0]  = 1;
                    LihuowangMod.logger.error("=============setPrice=3============>");

                }
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(clz = ShopScreen.class, method = "setPrice")
    public static class Pianjing_setPrice {
        @SpireInsertPatch(rloc = 2, localvars = {"tmpPrice"})
        public static SpireReturn<Void> Insert(ShopScreen _inst, AbstractCard card,@ByRef float[] temPrice) {

            LihuowangMod.logger.error("=============setPrice1=============>");

            if (AbstractDungeon.player.hasRelic(PianJing.ID)) {
                LihuowangMod.logger.error("=============AbstractDungeon.player.hasRelic(PianJing.ID)2=============>");
//                temPrice[0] = 1;
                PianJing pk = (PianJing) AbstractDungeon.player.getRelic(PianJing.ID);
                if (pk.out_num != -2 ) {
                    temPrice[0]  = 2;
                    LihuowangMod.logger.error("=============setPrice=3============>");
                }
            }
            return SpireReturn.Continue();

        }
    }

//    @SpirePatch(clz = ShopScreen.class, method = "init", paramtypez = {ArrayList.class, ArrayList.class})
//    public static class getNewPrice {
//        @SpirePostfixPatch
//        public static void getNewPrice(ShopScreen _inst,ArrayList<AbstractCard> coloredCards, ArrayList<AbstractCard> colorlessCards) {
//
////            if (AbstractDungeon.player.hasRelic("Smiling Mask")) {
////                actualPurgeCost = 50;
////            }
//
//            if (AbstractDungeon.player.hasRelic(PianJing.ID)) {
//                _inst.actualPurgeCost = 1;
//                LihuowangMod.logger.error("=============getNewPrice=============>");
//            }
//        }
//    }
}

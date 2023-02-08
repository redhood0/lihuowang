package com.daoguiyixian.patch;

import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.power.HuoWoPower.HUOWO_COUNT;


public class HuowoPatch {
    @SpirePatch(clz = AbstractCard.class, method = "canUse",
            paramtypez = {AbstractPlayer.class, AbstractMonster.class})
    public static class HuowoPrefixPatch{
        @SpirePrefixPatch
        public static SpireReturn<Boolean> HuowoPatchMethod(AbstractCard _inst, AbstractPlayer p, AbstractMonster m) {
//        if (_inst.type == AbstractCard.CardType.STATUS && _inst.costForTurn < -1 && !AbstractDungeon.player.hasPower(ModHelper.MakePath("HuoWoPower"))) {
            if (_inst.type == AbstractCard.CardType.STATUS && _inst.cardID.equals("Burn")  && AbstractDungeon.player.hasPower(ModHelper.MakePath("HuoWoPower"))) {

                int amount =   AbstractDungeon.player.getPower(ModHelper.MakePath("HuoWoPower")).amount * 2;
                LihuowangMod.logger.error("================amount============>"+amount);
                LihuowangMod.logger.error("================HUOWO_COUNT============>"+HUOWO_COUNT);

                if(HUOWO_COUNT < amount ){
                    LihuowangMod.logger.error("================HUOWO_COUNT < amount*2========true====>"+HUOWO_COUNT);
                    LihuowangMod.logger.error("================HUOWO_COUNT < amount*2========true====>"+amount);
                    return SpireReturn.Return(true);
                }else {
                    LihuowangMod.logger.error("================HUOWO_COUNT < amount*2========false====>"+HUOWO_COUNT);
                    LihuowangMod.logger.error("================HUOWO_COUNT < amount*2========false====>"+amount);

                    return SpireReturn.Return(false);
                }

            } else if (_inst.type == AbstractCard.CardType.STATUS && _inst.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                return SpireReturn.Return(false);
            } else if (_inst.type == AbstractCard.CardType.CURSE && _inst.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                return SpireReturn.Return(false);
            } else {
                return SpireReturn.Return(_inst.cardPlayable(m) && _inst.hasEnoughEnergy());
            }
//            System.out.println(_inst.name+"================================>>>>>>>>>>>>>>");
//            return SpireReturn.Return(false);
        }
    }


}

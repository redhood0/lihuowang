package com.daoguiyixian.patch;


import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.EatMeatPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Ironclad;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.FairyPotion;

import static com.daoguiyixian.power.HuoWoPower.HUOWO_COUNT;

public class FairyPotionPatch {
    public FairyPotionPatch() {
    }

    @SpirePatch(clz = FairyPotion.class, method = "use", paramtypez = {AbstractCreature.class})
    public static class FairyPotion4EatMeatPower {
        @SpirePrefixPatch
        public static void FairyPotion4EatMeatPowerMethod(AbstractPotion _inst, AbstractCreature p) {

                    if(AbstractDungeon.player.hasPower(EatMeatPower.POWER_ID)){
                        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, EatMeatPower.POWER_ID, 1));
//                        _inst.addToBot();
                    }
        }


    }
}

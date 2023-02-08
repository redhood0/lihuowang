package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.cards.MoneySword;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.daoguiyixian.power.HuoWoPower.HUOWO_COUNT;

public class EatMeatPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath("EatMeatPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private int energyGainAmount = 1;

    public EatMeatPower(AbstractCreature owner,int num) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        this.description = DESCRIPTIONS[0];
        this.energyGainAmount = -1;

        String path128 = "lihuowangResources/img/power/danyangzi84.png";
        String path48 = "lihuowangResources/img/power/danyangzi32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
//        ++this.energyGainAmount;
    }
    public void onEnergyRecharge() {
        this.flash();

        int MAX_E =  AbstractDungeon.player.energy.energyMaster * (-1);
//        int MAX_E2 =  AbstractDungeon.player.energy.energy;
//        LihuowangMod.logger.error("================HUOWO_COUNT============>"+MAX_E);
//        LihuowangMod.logger.error("================HUOWO_COUNT============>"+MAX_E2);



        AbstractDungeon.player.gainEnergy(this.energyGainAmount);
        if(this.energyGainAmount > MAX_E){
            this.energyGainAmount -= 1;
        }

        this.updateDescription();
    }


    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

    }


//    @Override
//    public void onDeath() {
//        this.addToBot(new ReducePowerAction(this.owner, this.owner, EatMeatPower.POWER_ID, 1));
//        LihuowangMod.logger.error("=============onDeath()============>");
//    }
//
//    @Override
//    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
//        LihuowangMod.logger.error("=============atDamageFinalReceive()============>"+damage);
//        return super.atDamageFinalReceive(damage, type);
//    }
//
//    @Override
//    public int onLoseHp(int damageAmount) {
//        LihuowangMod.logger.error("=============atDamageFinalReceive()============>"+damageAmount);
//        return super.onLoseHp(damageAmount);
//    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

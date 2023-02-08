package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.cards.MoneySword;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MoneyPower extends AbstractPower {

    public static final String POWER_ID = ModHelper.MakePath("MoneyPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public MoneyPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = 1;
        this.description = DESCRIPTIONS[0];
        String path128 = "lihuowangResources/img/power/tongqian84.png";
        String path48 = "lihuowangResources/img/power/tongqian32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

//    public void atEndOfTurn(boolean isPlayer) {
//        if (isPlayer) {
//            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "No Draw"));
//        }
//
//    }


    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if(card.cardID.equals(ModHelper.MakePath("MoneySword"))){
//            this.amount++;
//        }
    }



    @Override
    public void onVictory() {
        //数值归0
        MoneySword.usetime = 0;
    }

    @Override
    public void onDeath() {
        //数值归0
        MoneySword.usetime = 0;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

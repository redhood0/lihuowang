package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.cards.MoneySword;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

public class ShaQiPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath("ShaQiPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public ShaQiPower(AbstractCreature owner,int magicnum) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = magicnum;
        this.description = DESCRIPTIONS[0];
        String path128 = "lihuowangResources/img/power/shaqi84.png";
        String path48 = "lihuowangResources/img/power/shaqi32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void atEndOfTurn(boolean isPlayer) {
        AbstractPlayer p = AbstractDungeon.player;
        if (isPlayer) {
            if (this.amount >= 5) {
                //对自己造成伤害；
                this.addToBot(new ApplyPowerAction(p, p, new NoBlockPower(p, 99, false), 99));
            }
        }
    }

//    @Override
//    public void onPlayCard(AbstractCard card, AbstractMonster m) {
//        Iterator<AbstractMonster>  var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        AbstractPlayer p = AbstractDungeon.player;
//        AbstractMonster mo;
//        while(var3.hasNext()) {
//            mo = (AbstractMonster)var3.next();
//            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
//        }
//    }

    @Override
    public void atStartOfTurn() {

        if (this.amount >= 5) {
            Iterator<AbstractMonster>  var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            AbstractPlayer p = AbstractDungeon.player;
            AbstractMonster mo;
            while(var3.hasNext()) {
                mo = (AbstractMonster)var3.next();
                this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if(card.cardID.equals(ModHelper.MakePath("MoneySword"))){
//            this.amount++;
//        }
    }

//    @Override
//    public void onVictory() {
//        //数值归0
//        MoneySword.usetime = 0;
//    }
//
//    @Override
//    public void onDeath() {
//        //数值归0
//        MoneySword.usetime = 0;
//    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

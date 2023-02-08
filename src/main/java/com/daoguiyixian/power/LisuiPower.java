package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.action.BlackTaiSuiAction;
import com.daoguiyixian.cards.BlackTaiSui;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.daoguiyixian.tag.CustomTags.BLACKTAISUI;

public class LisuiPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath("LisuiPower");


    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int damage = 0;
    public int addnum = 0;


    public LisuiPower(AbstractCreature owner, int drawAmount,int damage, int addnum) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
//        this.amount = drawAmount;
        this.type = PowerType.BUFF;
        this.amount = drawAmount;

        this.damage = damage;
        this.addnum = addnum;

//        this.loadRegion("tools");1
        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/lisui84.png";
        String path48 = "lihuowangResources/img/power/lisui32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
        this.priority = 24;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.damage  + DESCRIPTIONS[1] + this.addnum + DESCRIPTIONS[2];
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
//        }

    }

    public void atStartOfTurnPostDraw() {

//        this.addToBot(new DrawCardAction(this.owner, 1));
//        DamageInfo.DamageType.NORMAL

    }

    @Override
    public void atStartOfTurn() {
//        super.atStartOfTurn();
        this.flash();
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.damage , true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(card.tags.contains(BLACKTAISUI)){
//            card.baseDamage += this.addnum;
            return damage+this.addnum;
        }

        return super.atDamageFinalGive(damage, type, card);
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        if(card.tags.contains(BLACKTAISUI)){
//            card.baseDamage += this.addnum;
            return blockAmount+this.addnum;
        }
        return super.modifyBlock(blockAmount, card);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
//        System.out.println("+=============onPlayCard==========================>"+card);
//        if(card.tags.contains(BLACKTAISUI)){
//            card.baseDamage += this.addnum;
//            System.out.println(card+"+=============onPlayCard==========================>"+card.damage);
//        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        super.onCardDraw(card);
        System.out.println("+=============onCardDraw==========================>"+card);
    }


}

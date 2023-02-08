package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.daoguiyixian.action.XiesuiwangAction;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import java.util.ArrayList;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//new DamageInfo(p, damage, this.damageTypeForTurn)
public class Xiesuiwang extends CustomCard {
    public static final String ID = ModHelper.MakePath(Xiesuiwang.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/xiesuiwang.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    public Xiesuiwang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 0;
        this.cost = 2;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
//        this.isMultiDamage = true;
//        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
//        this.tags.add(AbstractCard.CardTags.STRIKE);
//
//        this.selfRetain = true;
    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
//            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
            this.upgradeMagicNumber(2);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }


    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        if (this.upgraded) {
            this.addToBot(new ExhaustAction(1, false));
        } else {
            this.addToBot(new ExhaustAction(1, true, false, false));
        }

        ArrayList<AbstractCard> group =abstractPlayer.exhaustPile.group;
        int curseNum = 0;

        for(AbstractCard c : group){
            LihuowangMod.logger.error("-------------c-------------->"+c.name);

            if(c.type == AbstractCard.CardType.CURSE){
                curseNum++;
            }
        }
        LihuowangMod.logger.error("-------------curseNum-------------->"+curseNum);



       this.baseDamage = curseNum * this.magicNumber;

        this.calculateCardDamage((AbstractMonster)null);

        LihuowangMod.logger.error("-------------this.baseDamage-------------->"+this.baseDamage);
        LihuowangMod.logger.error("-------------this.damage-------------->"+this.damage);
//        LihuowangMod.logger.error("-------------this.multiDamage-------------->"+this.multiDamage);

        if (abstractMonster != null) {
            this.addToBot(new VFXAction(new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
        }
//        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        this.addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
//        this.addToBot(new DamageAllEnemiesAction(abstractPlayer, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));

//        this.addToBot(new XiesuiwangAction(abstractMonster,new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn)));

    }



//    public void onMoveToDiscard() {
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.initializeDescription();
//    }


//    public void calculateCardDamage(AbstractMonster mo) {
//        super.calculateCardDamage(mo);
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
//        this.initializeDescription();
//    }
    public AbstractCard makeCopy() {
        return new Xiesuiwang();
    }
}

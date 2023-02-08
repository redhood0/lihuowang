package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

public class Bite2 extends CustomCard {
    public static final String ID = ModHelper.MakePath(Bite2.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/Bite2.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    public Bite2() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 4;
//        this.baseMagicNumber = 2;
//        this.magicNumber = this.baseMagicNumber;

//        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);

        this.selfRetain = true;
    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }


    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null) {
            this.addToBot(new VFXAction(new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
        }
//        if (abstractMonster != null) {
//            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
//        }

//        this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, magicNumber));
//
//
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
//                )
//        );

//        this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, this.magicNumber));
        if(abstractPlayer.hasPower(CrazyPower.POWER_ID)){
            this.addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage * 2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }else {
            this.addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }

    }

    public AbstractCard makeCopy() {
        return new Bite2();
    }
}

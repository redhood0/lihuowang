package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

public class MaJiang extends CustomCard {
    public static final String ID = ModHelper.MakePath(MaJiang.class.getSimpleName());
    ;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MaJiang.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

//    private static final CardStrings cardStrings;
//
//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("lihuowang:BackWindAttack");
//    }


    public MaJiang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.magicNumber=this.baseMagicNumber=2;

        this.tags.add(AbstractCard.CardTags.STRIKE);
//        this.selfRetain = true;
    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // ????????????????????????????????????+????????????????????????????????????????????????????????????
//            this.upgradeDamage(1); // ???????????????????????????3??????
//            this.updateCost(0);
//            this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
            // ??????????????????????????????UPGRADE_DESCRIPTION??????????????????????????????
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.addToBot(new DamageAction(m, new DamageInfo(p, 1, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAction(m, new DamageInfo(p, 2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAction(m, new DamageInfo(p, 3, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DrawCardAction(p, this.magicNumber));

//        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

    public AbstractCard makeCopy() {
        return new MaJiang();
    }
}

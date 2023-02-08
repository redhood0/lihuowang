package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class FireDefense extends CustomCard {
    public static final String ID = ModHelper.MakePath(FireDefense.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/FireDefense.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public FireDefense() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 20;
        this.magicNumber = this.baseMagicNumber = 1;


//        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

//        this.addToBot(new LoseHPAction(p, p, 4));
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new MakeTempCardInDiscardAction(new Burn(), 2));

//        this.addToBot(new GainEnergyAction(this.magicNumber));

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(6);
//            this.upgradeMagicNumber(1);
        }
    }


    public AbstractCard makeCopy() {
        return new FireDefense();
    }
}

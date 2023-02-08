package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class MoneyBack extends CustomCard {
    public static final String ID = ModHelper.MakePath(MoneyBack.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MoneyBack.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public MoneyBack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
//        this.baseBlock = 14;
        this.magicNumber = this.baseMagicNumber = 16;
//        this.exhaust = true;

        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(ModHelper.MakePath("MoneyPower"))) {
            int times = p.getPower(ModHelper.MakePath("MoneyPower")).amount;
            this.addToBot(new GainBlockAction(p, p, this.magicNumber * times));
            this.addToBot(new GainGoldAction(this.magicNumber * times));
            this.addToBot(new RemoveSpecificPowerAction(p,p,ModHelper.MakePath("MoneyPower")));
            MoneySword.usetime = 0;
        }
//        this.addToBot(new LoseHPAction(p, p, 8));

//        this.addToBot(new GainEnergyAction(this.magicNumber));

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeBlock(4);
            this.upgradeMagicNumber(4);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(ModHelper.MakePath("MoneyPower"))) {
            return true;
        }
        return false;
    }

    public AbstractCard makeCopy() {
        return new MoneyBack();
    }


}
